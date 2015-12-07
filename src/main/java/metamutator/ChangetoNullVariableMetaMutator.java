package metamutator;

import java.util.EnumSet;
import java.util.Set;

import com.google.common.collect.Sets;

import configuration.Config;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtAnonymousExecutable;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;

/**
 * inserts a mutation hotspot for each binary operator
 */
public class ChangetoNullVariableMetaMutator extends AbstractProcessor<CtLiteral> {

	public static final String SELECTOR_CLASS = Selector.class.getName();

	//the place where we'll wrote the configuration.
	public static Config conf = Config.getInstance();

	private static int index = 0;
	
	private enum STATE {
		NULL, NOTNULL
	};

	private static final EnumSet<STATE> REDUCED_COMPARISON_OPERATORS = EnumSet
			.of(STATE.NOTNULL, STATE.NULL);

	private Set<CtElement> hostSpots = Sets.newHashSet();

	private String className ="";

	@Override
	public boolean isToBeProcessed(CtLiteral element) {
		// if (element.getParent(CtAnonymousExecutable.class)!=null) {
		// System.out.println(element.getParent(CtAnonymousExecutable.class));
		// }
		try {
			getTopLevelClass(element);
		} catch (NullPointerException e) {
			return false;
		}

		// not in constructors because we use static fields
		if (element.getParent(CtConstructor.class) != null) {
			return false;
		}

		return !element.getType().isPrimitive() 
				&& (element.getParent(CtAnonymousExecutable.class) == null);
	}

	public void process(CtLiteral binaryOperator) {
		
		//on sauvegarde le nom de la class, et s'il change on le note dans les configs.
		String currentClass =binaryOperator.getParent(CtClass.class).getSimpleName();
		if(!(this.className.equals(currentClass))){
			this.className =  currentClass;
			conf.write(this.className);
		}
		
		mutateOperator(binaryOperator, REDUCED_COMPARISON_OPERATORS);

	}


	/**
	 * Converts "a op b" bean op one of "<", "<=", "==", ">=", "!=" to:
	 *    (  (op(1, 0, "<")  && (a < b))
	 *    || (op(1, 1, "<=") && (a <= b))
	 *    || (op(1, 2, "==") && (a == b))
	 *    || (op(1, 3, ">=") && (a >= b))
	 *    || (op(1, 4, ">")  && (a > b))
	 *    )
	 *
	 * com.medallia.codefixer
	 * @param expression
	 * @param operators
	 */
	private void mutateOperator(final CtLiteral expression,
			EnumSet<STATE> operators) {

		if (alreadyInHotsSpot(expression)
				|| expression.toString().contains(".is(\"")) {
			System.out
			.println(String
					.format("Expression '%s' ignored because it is included in previous hot spot",
							expression));
			return;
		}

		int thisIndex = ++index;
		
		String actualExpression = expression.toString();
		String newExpression = String.format("(_li%s.is(\"NOTNULL\"))?"+actualExpression+":null",thisIndex);

		CtCodeSnippetExpression<Boolean> codeSnippet = getFactory().Core()
				.createCodeSnippetExpression();
		codeSnippet.setValue('(' + newExpression + ')');

		expression.replace(codeSnippet);
		expression.replace(expression);
		addVariableToClass(expression, "NOTNULL", thisIndex, operators);

		hostSpots.add(expression);

	}

	/**
	 * Check if this sub expression was already inside an uppermost expression
	 * that was processed has a hot spot. This version does not allowed
	 * conflicting hot spots
	 * 
	 * @param element
	 *            the current expression to test
	 * @return true if this expression is descendant of an already processed
	 *         expression
	 */
	private boolean alreadyInHotsSpot(CtElement element) {
		CtElement parent = element.getParent();
		while (!isTopLevel(parent) && parent != null) {
			if (hostSpots.contains(parent))
				return true;

			parent = parent.getParent();
		}

		return false;
	}

	private boolean isTopLevel(CtElement parent) {
		return parent instanceof CtClass && ((CtClass) parent).isTopLevel();
	}

	private void addVariableToClass(CtLiteral element,
			String originalKind, int index,
			EnumSet<STATE> operators) {

		long hashCode = (element.getPosition().toString() + element.getParent()
		.toString()).hashCode();

		CtTypeReference<Object> fieldType = getFactory().Type()
				.createTypeParameterReference(SELECTOR_CLASS);
		String selectorId = "_li" + index;


		//we add the new selector in the config file
		conf.write(this.className+":"+selectorId);


		CtCodeSnippetExpression<Object> codeSnippet = getFactory().Core()
				.createCodeSnippetExpression();

		StringBuilder sb = new StringBuilder(SELECTOR_CLASS + ".of(")
				.append(index);

		sb.append(',');

		// now the options
		sb.append("new String[]{");

		// the original operator, always the first one
		sb.append('"').append(originalKind).append('"');
		conf.write(this.className+":"+selectorId+":"+originalKind+":true");
		// the other alternatives
		for (STATE kind : operators) {
			if (kind.toString().equals(originalKind)) {
				continue;
			}
			sb.append(',').append('"').append(kind).append('"');
			conf.write(this.className+":"+selectorId+":"+kind+":false");
		}

		sb.append("})");

		// adding location
		if (element.getParent(CtType.class).isTopLevel()) {
			sb.append(".in("
					+ element.getParent(CtType.class).getQualifiedName()
					+ ".class)");
		}

		// adding identifier
		sb.append(".id(\"" + selectorId + "\")");

		codeSnippet.setValue(sb.toString());

		CtClass<?> type = getTopLevelClass(element);

		CtField<Object> field = getFactory().Field().create(
				type,
				EnumSet.of(ModifierKind.FINAL, ModifierKind.PRIVATE,
						ModifierKind.STATIC), fieldType, selectorId,
				codeSnippet);

		type.addField(field);
	}

	private CtClass<?> getTopLevelClass(CtElement element) {
		CtClass parent = element.getParent(CtClass.class);
		while (!parent.isTopLevel()) {
			parent = parent.getParent(CtClass.class);
		}
		return parent;
	}
}