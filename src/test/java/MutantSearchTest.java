import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import metamutator.BinaryOperatorMetaMutator;
import metamutator.MutantSearchSpaceExplorator;
import metamutator.VariabletoNullMetaMutator;
import spoon.Launcher;

public class MutantSearchTest {


	/**
	 * We launch the search of selector and try them
	 * @throws Exception
	 */
	@Test
	public void TestMutantSearchFile () throws Exception
	{
		// we spoon the files first
		Launcher l = new Launcher();
        l.addInputResource("src/test/java/search_replay_src");
        l.setSourceOutputDirectory("src/test/java/search_replay_spoon");
        l.addProcessor(new BinaryOperatorMetaMutator());
        l.addProcessor(new VariabletoNullMetaMutator());
        l.run();
		
		File f = new File("results/fail/search_replay_test.SearchReplayTestClass");
		File s = new File("results/success/search_replay_test.SearchReplayTestClass");

		MutantSearchSpaceExplorator.runMetaProgramWith(search_replay_test.SearchReplayTestClass.class);

		//Then we check the if the wanted file are created.
		
		assertTrue(f.exists());
		assertTrue(s.exists());
		
		File fm01 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant01.txt");
		File fm11 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant11.txt");
		File fm12 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant12.txt");
		File fm13 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant13.txt");
		
		File fm00 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant00.txt");
		File fm10 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant10.txt");
		File fm14 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant14.txt");
		File fm15 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant15.txt");
		
		File sm01 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant01.txt");
		File sm11 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant11.txt");
		File sm12 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant12.txt");
		File sm13 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant13.txt");


		
		File sm00 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant00.txt");
		File sm10 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant10.txt");
		File sm14 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant14.txt");
		File sm15 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant15.txt");

		assertTrue(fm01.exists());
		assertTrue(fm11.exists());
		assertTrue(fm12.exists());
		assertTrue(fm13.exists());
		
		assertFalse(fm00.exists());
		assertFalse(fm10.exists());
		assertFalse(fm14.exists());
		assertFalse(fm15.exists());

		
		
		assertFalse(sm01.exists());
		assertFalse(sm11.exists());
		assertFalse(sm12.exists());
		assertFalse(sm13.exists());

		
		assertTrue(sm00.exists());
		assertTrue(sm10.exists());
		assertTrue(sm14.exists());
		assertTrue(sm15.exists());



	}
	
	
	@Test
	public void TestMutantSearchDir () throws Exception
	{
		
		Launcher l = new Launcher();
        l.addInputResource("src/test/java/search_replay_src");
        l.setSourceOutputDirectory("src/test/java/search_replay_spoon");
        l.addProcessor(new BinaryOperatorMetaMutator());
        l.addProcessor(new VariabletoNullMetaMutator());
        l.run();
		
		File f = new File("results/fail/search_replay_test.SearchReplayTestClass");
		File s = new File("results/success/search_replay_test.SearchReplayTestClass");
		File fb = new File("results/fail/search_replay_test.SearchReplayTestClassBis");
		File sb = new File("results/success/search_replay_test.SearchReplayTestClassBis");
		

		
		MutantSearchSpaceExplorator.runMetaProgramIn("src/test/java/search_replay_test");
		

		assertTrue(f.exists());
		assertTrue(s.exists());
		assertTrue(fb.exists());
		assertTrue(sb.exists());
		

		File fom = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant01.txt");
		File som = new File("results/sucess/search_replay_test.SearchReplayTestClass/mutant02.txt");
		File fvm = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant11.txt");
		File svm = new File("results/sucess/search_replay_test.SearchReplayTestClass/mutant12.txt");

		File fbom = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant03.txt");
		File sbom = new File("results/sucess/search_replay_test.SearchReplayTestClassBis/mutant04.txt");
		File fbvm = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant13.txt");
		File sbvm = new File("results/sucess/search_replay_test.SearchReplayTestClassBis/mutant14.txt");
		
		assertTrue(fom.exists());
		assertTrue(som.exists());
		assertTrue(fvm.exists());
		assertTrue(svm.exists());
		
		assertTrue(fbom.exists());
		assertTrue(sbom.exists());
		assertTrue(fbvm.exists());
		assertTrue(sbvm.exists());

	}
}
