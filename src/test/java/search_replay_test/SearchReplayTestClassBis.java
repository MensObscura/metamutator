
package search_replay_test;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import search_replay_spoon.search_replay_src.SearchReplayClassBis;

public class SearchReplayTestClassBis {
	
	static SearchReplayClassBis sRCB = new SearchReplayClassBis();
	
	  @Test
	  public void op(){
	
		  assertTrue(sRCB.op());
	  }

}