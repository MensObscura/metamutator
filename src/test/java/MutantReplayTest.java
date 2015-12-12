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
		
			
			MutantReplay.replayMetaProgramWith(search_replay_test.SearchReplayTestClass.class);
			
			//Then we check the if the wanted file are created.
			f = new File("results/fail.replay/search_replay_test.SearchReplayTestClass");
			s = new File("results/success.replay/search_replay_test.SearchReplayTestClass");
			assertTrue(f.exists());
			assertTrue(s.exists());
			File fm01 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
			File fm11 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op3.txt");
			File fm12 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op4.txt");
			File fm13 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op2.txt");
			
			File fm00 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
			File fm10 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op5.txt");
			File fm14 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op6.txt");
			File fm15 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op1.txt");
			
			File sm01 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
			File sm11 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op3.txt");
			File sm12 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op4.txt");
			File sm13 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op2.txt");


			
			File sm00 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
			File sm10 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op5.txt");
			File sm14 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op6.txt");
			File sm15 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op1.txt");

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
			

		
			MutantReplay.replayMetaProgramIn("target/test-classes/search_replay_test");
			f = new File("results/fail.replay/search_replay_test.SearchReplayTestClass");
			s = new File("results/success.replay/search_replay_test.SearchReplayTestClass");
			fb = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis");
			sb = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis");
			assertTrue(f.exists());
			assertTrue(s.exists());
			assertTrue(fb.exists());
			assertTrue(sb.exists());
			
			File fm01 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
			File fm11 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op3.txt");
			File fm12 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op4.txt");
			File fm13 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op2.txt");
			
			File fm00 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
			File fm10 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op5.txt");
			File fm14 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op6.txt");
			File fm15 = new File("results/fail.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op1.txt");
			
			File sm01 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
			File sm11 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op3.txt");
			File sm12 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op4.txt");
			File sm13 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op2.txt");


			
			File sm00 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
			File sm10 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op5.txt");
			File sm14 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op6.txt");
			File sm15 = new File("results/success.replay/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op1.txt");

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
			
			File fm31 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op2.txt");
			File fm41 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op3.txt");
			File fm42 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op4.txt");
			File fm43 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op2.txt");
			
			File fm30 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op1.txt");
			File fm40 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op5.txt");
			File fm44 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op6.txt");
			File fm45 = new File("results/fail.replay/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op1.txt");
			
			File sm31 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op2.txt");
			File sm41 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op3.txt");
			File sm42 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op4.txt");
			File sm43 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op2.txt");


			
			File sm30 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op1.txt");
			File sm40 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op5.txt");
			File sm44 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op6.txt");
			File sm45 = new File("results/success.replay/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op1.txt");

			assertTrue(fm31.exists());
			assertTrue(fm41.exists());
			assertTrue(fm42.exists());
			assertTrue(fm43.exists());
			
			assertFalse(fm30.exists());
			assertFalse(fm40.exists());
			assertFalse(fm44.exists());
			assertFalse(fm45.exists());

			
			
			assertFalse(sm31.exists());
			assertFalse(sm41.exists());
			assertFalse(sm42.exists());
			assertFalse(sm43.exists());

			
			assertFalse(sm30.exists());
			assertFalse(sm40.exists());
			assertFalse(sm44.exists());
			assertFalse(sm45.exists());




	  }


}
