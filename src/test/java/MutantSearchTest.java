import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import footest.FooTest;
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
		
		
		File f = new File("results/fail/footest.FooTest");
		File s = new File("results/success/footest.FooTest");

		MutantSearchSpaceExplorator.runMetaProgramWith(FooTest.class);

		//Then we check the if the wanted file are created.
		
		assertTrue(f.exists());
		assertTrue(s.exists());
		
		File fm01 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot1_Op1.txt");
		File fm02 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot1_Op2.txt");

		File fm03 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot2_Op1.txt");
		File fm04 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot2_Op2.txt");
		File fm05 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot2_Op3.txt");
		File fm06 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot2_Op4.txt");
		File fm07 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot2_Op5.txt");
		File fm08 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot2_Op6.txt");
		
		File fm09 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
		File fm10 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
		
		File fm11 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot4_Op1.txt");
		File fm12 = new File("results/fail/footest.FooTest/mutant_binaryLogicalOperatorHotSpot4_Op2.txt");
		
		File fm13 = new File("results/fail/footest.FooTest/mutant_variableNullHotSpot1_Op1.txt");
		File fm14 = new File("results/fail/footest.FooTest/mutant_variableNullHotSpot1_Op2.txt");
				

		assertEquals(14,f.listFiles().length);
		assertEquals(0,s.listFiles().length);

		assertTrue(fm01.exists());
		assertTrue(fm02.exists());
		assertTrue(fm03.exists());
		assertTrue(fm04.exists());
		assertTrue(fm05.exists());
		assertTrue(fm06.exists());
		assertTrue(fm07.exists());
		assertTrue(fm08.exists());
		assertTrue(fm09.exists());
		assertTrue(fm10.exists());
		assertTrue(fm11.exists());
		assertTrue(fm12.exists());
		assertTrue(fm13.exists());
		assertTrue(fm14.exists());
		
	}
	
	
	@Test
	public void TestMutantSearchDir () throws Exception
	{
		
		
   
		File f = new File("results/fail/search_replay_test.SearchReplayTestClass");
		File s = new File("results/success/search_replay_test.SearchReplayTestClass");
		File fb = new File("results/fail/search_replay_test.SearchReplayTestClassBis");
		File sb = new File("results/success/search_replay_test.SearchReplayTestClassBis");
		

		
		MutantSearchSpaceExplorator.runMetaProgramIn("target/test-classes/search_replay_test");
		

		assertTrue(f.exists());
		assertTrue(s.exists());
		assertTrue(fb.exists());
		assertTrue(sb.exists());
		

		File fm01 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
		File fm11 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op3.txt");
		File fm12 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op4.txt");
		File fm13 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op2.txt");
		
		File fm00 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
		File fm10 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op5.txt");
		File fm14 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op6.txt");
		File fm15 = new File("results/fail/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op1.txt");
		
		File sm01 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op2.txt");
		File sm11 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op3.txt");
		File sm12 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op4.txt");
		File sm13 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op2.txt");


		
		File sm00 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op1.txt");
		File sm10 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op5.txt");
		File sm14 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_binaryLogicalOperatorHotSpot3_Op6.txt");
		File sm15 = new File("results/success/search_replay_test.SearchReplayTestClass/mutant_variableNullHotSpot3_Op1.txt");

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
		
		File fm31 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op2.txt");
		File fm41 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op3.txt");
		File fm42 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op4.txt");
		File fm43 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op2.txt");
		
		File fm30 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op1.txt");
		File fm40 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op5.txt");
		File fm44 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op6.txt");
		File fm45 = new File("results/fail/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op1.txt");
		
		File sm31 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op2.txt");
		File sm41 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op3.txt");
		File sm42 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op4.txt");
		File sm43 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op2.txt");


		
		File sm30 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op1.txt");
		File sm40 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op5.txt");
		File sm44 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_binaryLogicalOperatorHotSpot4_Op6.txt");
		File sm45 = new File("results/success/search_replay_test.SearchReplayTestClassBis/mutant_variableNullHotSpot4_Op1.txt");

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

		
		assertTrue(sm30.exists());
		assertTrue(sm40.exists());
		assertTrue(sm44.exists());
		assertTrue(sm45.exists());


	}
}
