package metamutator;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import configuration.Config;
import extension.ClasseBTest;


public class MutantSearchSpaceExplorator {

	public static void runMetaProgramWith(Class<?> TEST_CLASS) throws Exception {

		boolean debug = false;

		JUnitCore core = new JUnitCore();
		
		//output folder
		File fail = new File("fail");
		fail.mkdirs();
		File sucess = new File("sucess");
		sucess.mkdirs();

		// we first run the test suite once to load all classes and their static
		// fields
		// this registers only the executed ifs, so indirectly
		// it collects a kind of trace
		Result result = core.run(TEST_CLASS);

		if (debug) { core.addListener(new TextListener(System.out)); }

		List<Selector> selectors = Selector.getAllSelectors();

		// if (selectors.isEmpty())
		// // There's no hot spot in program. Add one to run it at least once
		// selectors = ImmutableList.of(Selector.of(0, "n/a"));

		List<String> successes = Lists.newArrayList();
		List<String> failures = Lists.newArrayList();
		Multimap<Integer, String> failures2 = Multimaps.newListMultimap(
				Maps.newHashMap(), Lists::newArrayList);

		String[] strOptions = new String[selectors.size()];
		// Execute the test for each hot spot permutation
		// for (int[] options :
		// permutations(selectors.stream().map(Selector::getOptionCount).collect(Collectors.toList())))
		// {
		
		int nattempts=0;
		
		for (int sel = 0; sel < selectors.size(); sel++) {
			
			//int k=0;
			System.out.println(selectors.get(sel).getOptionCount());
			for (int k = 0; k < selectors.get(sel).getOptionCount(); k++) 
			{
				Config conf = Config.getInitInstance();

			
				int[] options = new int[selectors.size()];
				// System.out.println(Arrays.toString(options));
				for (int i = options.length - 1; i >= 0; i--) {
					selectors.get(i).choose(0);
					selectors.get(i).setStopTime(
							System.currentTimeMillis() + 300000);
					strOptions[i] = selectors.get(i)
							.getChosenOptionDescription();
					for(int o = 0; o < selectors.get(i).getOptionCount();o++ ){
						
					boolean value =(o == 0)?true:false;
					
					conf.write(selectors.get(i).getLocationClass().getName()+":"+selectors.get(i).getId()+":"+selectors.get(i).getOption()[o]+":"+value);
					
					}
				}
				selectors.get(sel).choose(k);

				if (debug)
					System.out.println("Checking options: "
							+ Arrays.toString(options));

				result = core.run(TEST_CLASS);

				if (result.wasSuccessful()) {
					successes.add("   Worked !!!  -> "
							+ Arrays.toString(options) + " / "
							+ Arrays.toString(strOptions));
					
			                // On essaye avec renameTo
					File dest = new File("sucess/mutant"+sel+""+k+".txt");
			                new File("config.txt").renameTo(dest);
				} else {
					String txt = String
							.format("%s / %s -> It has %s failures out of %s runs in %s ms",
									Arrays.toString(options),
									Arrays.toString(strOptions),
									result.getFailureCount(),
									result.getRunCount(), result.getRunTime());
					String txt_trace = String.format("%s",
							Arrays.toString(strOptions));

					failures.add(txt);
					failures2.put(result.getFailureCount(), txt);
					System.out.println(result.getFailures().get(0).getException());
					File dest = new File("fail/mutant"+sel+""+k+".txt");
	                new File("config.txt").renameTo(dest);
				}
			}

		}

		System.out.println("killed "+failures.size());
		System.out.println("alive "+successes.size());
		// Show result summary
		// Sets.newHashSet(failures2.keys()).forEach(k -> {
		// System.out.println(String.format("\n-- Cases with %s", k));
		// Collection<String> texts = failures2.get(k);
		// if (k <= 2 || texts.size() < 10)
		// texts.forEach(System.out::println);
		// else
		// System.out.println("There are " + texts.size());
		// });

		// failures.forEach(System.out::println);

		// System.out.println();
		//
		// if (successes.isEmpty())
		// System.out.println("Oops, sorry, we could find a successful option");
		// else
		// successes.forEach(System.out::println);

	}

	/**
	 * Computes an iterable though all the permutations or the values in the
	 * ranges provided
	 * 
	 * @param sizes
	 *            the number of elements in each range (from 0 to size - 1)
	 * @return an Iterable
	 */
	private static Iterable<int[]> permutations(List<Integer> sizes) {
		int limits[] = new int[sizes.size()];

		int last = sizes.size() - 1;
		for (int i = last; i >= 0; i--)
			limits[i] = sizes.get(i) - 1;

		return () -> new Iterator<int[]>() {
			int current[] = new int[last + 1];

			{
				current[last]--; // Force the first element
			}

			@Override
			public boolean hasNext() {
				return !Arrays.equals(limits, current);
			}

			@Override
			public int[] next() {
				for (int i = last; i >= 0; i--) {
					if (current[i] < limits[i]) {
						current[i]++;

						return current.clone();
					}
					current[i] = 0;
				}

				return new int[0];
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public static void main(String[] args) throws Exception {
		runMetaProgramWith(ClasseBTest.class);
	}
}
