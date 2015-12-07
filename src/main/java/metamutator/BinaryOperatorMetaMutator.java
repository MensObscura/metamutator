package metamutator;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import configuration.Config;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtAnonymousExecutable;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;

/**
 * inserts a mutation hotspot for each binary operator
 */
public class BinaryOperatorMetaMutator extends
AbstractProcessor<CtLiteral> {

	public static final String SELECTOR_CLASS = Selector.class.getName();

	//the place where we'll wrote the configuration.
	public static Config conf = Config.getInstance();

	private static int index = 0;

	public enum STATE {
		NULL, 
		NOTNULL
	}

	private Set<CtElement> hostSpots = Sets.newHashSet();

	private String className ="";

	@Override
	public boolean isToBeProcessed(CtLiteral element) {

		return (!element.getType().isPrimitive()) && (element.getParent(CtAnonymousExecutable.class) == null);
	}

	public void process(CtLiteral literalAccess) {

		//on sauvegarde le nom de la class, et s'il change on le note dans les configs.
		String currentClass = literalAccess.getParent(CtClass.class).getSimpleName();
		if(!(this.className.equals(currentClass))){
			this.className =  currentClass;
			conf.write(this.className);
		}


		int thisIndex = ++index;

		String type =  literalAccess.getType().getSimpleName();

		String originalKind = (String) literalAccess.getValue();

		System.out.println(type + " " +originalKind);
		String newExpression = String.format("(_lit%s.is(\"%s\") && (%s)) || %s )",index,"null",originalKind);

		CtCodeSnippetExpression<Boolean> codeSnippet = getFactory().Core()
				.createCodeSnippetExpression();
		codeSnippet.setValue('(' + newExpression + ')');

		literalAccess.replace(codeSnippet);
		literalAccess.replace(literalAccess);
		addVariableToClass(literalAccess, originalKind, thisIndex);

		hostSpots.add(literalAccess);


	}






	private void addVariableToClass(CtLiteral element,
			String originalKind, int index) {

		long hashCode = (element.getPosition().toString() + element.getParent()
		.toString()).hashCode();

		CtTypeReference<Object> fieldType = getFactory().Type()
				.createTypeParameterReference(SELECTOR_CLASS);
		String selectorId = "_lit" + index;


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
		/*for (BinaryOperatorKind kind : operators) {
			if (kind.toString().equals(originalKind)) {
				continue;
			}
			sb.append(',').append('"').append(kind).append('"');
			conf.write(this.className+":"+selectorId+":"+kind+":false");
		}*/

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