import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import metamutator.BinaryOperatorMetaMutator;
import metamutator.MutantReplay;
import metamutator.VariabletoNullMetaMutator;
import spoon.Launcher;

public class MutantReplayTest {
	
	/**
	 * Here we test one class in the replay mutant and check if the result is good
	 * @throws Exception
	 */
	  @Test
	  public void TestMutantReplayFile() throws Exception{
			File f;
			File s ;
		
			// we spoon the files first
			Launcher l = new Launcher();
	        l.addInputResource("src/test/java/search_replay_src");
	        l.setSourceOutputDirectory("src/test/java/search_replay_spoon");
	        l.addProcessor(new BinaryOperatorMetaMutator());
	        l.addProcessor(new VariabletoNullMetaMutator());
	        l.run();
			
			MutantReplay.replayMetaProgramWith(search_replay_test.SearchReplayTestClass.class);
			
			//Then we check the if the wanted file are created.
			f = new File("results/fail.replay/search_replay_test.SearchReplayTestClass");
			s = new File("results/success.replay/search_replay_test.SearchReplayTestClass");
			assertTrue(f.exists());
			assertTrue(s.exists());
			File fm01 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant01.txt");
			File fm11 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant11.txt");
			File fm12 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant12.txt");
			File fm13 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant13.txt");
			
			File fm00 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant00.txt");
			File fm10 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant10.txt");
			File fm14 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant14.txt");
			File fm15 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant15.txt");
			
			File sm01 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant01.txt");
			File sm11 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant11.txt");
			File sm12 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant12.txt");
			File sm13 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant13.txt");


			
			File sm00 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant00.txt");
			File sm10 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant10.txt");
			File sm14 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant14.txt");
			File sm15 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant15.txt");

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

			
			assertFalse(sm00.exists());
			assertFalse(sm10.exists());
			assertFalse(sm14.exists());
			assertFalse(sm15.exists());
			

	  }
	  
	  @Test
	  public void TestMutantReplayDir() throws Exception{
			File f;
			File s ;
			File fb;
			File sb ;
			
			Launcher l = new Launcher();
	        l.addInputResource("src/test/java/search_replay_src");
	        l.setSourceOutputDirectory("src/test/java/search_replay_spoon");
	        l.addProcessor(new BinaryOperatorMetaMutator());
	        l.addProcessor(new VariabletoNullMetaMutator());
	        l.run();
		
			MutantReplay.replayMetaProgramIn("src/test/java/search_replay_test");
			f = new File("results/fail.replay/search_replay_test.SearchReplayTestClass");
			s = new File("results/success.replay/search_replay_test.SearchReplayTestClass");
			fb = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis");
			sb = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis");
			assertTrue(f.exists());
			assertTrue(s.exists());
			assertTrue(fb.exists());
			assertTrue(sb.exists());
			
			File fom = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant01");
			File som = new File("results/sucess.replay/search_replay_test.SearchReplayTestClassBis/mutant02");
			File fvm = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant11");
			File svm = new File("results/sucess.replay/search_replay_test.SearchReplayTestClassBis/mutant12");
			
			File fbom = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant03");
			File sbom = new File("results/sucess.replay/search_replay_test.SearchReplayTestClassBis/mutant04");
			File fbvm = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant13");
			File sbvm = new File("results/sucess.replay/search_replay_test.SearchReplayTestClassBis/mutant14");
			
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
