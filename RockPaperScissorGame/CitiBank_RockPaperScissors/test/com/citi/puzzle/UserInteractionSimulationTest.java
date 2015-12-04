package com.citi.puzzle;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;



/*
 * Flavio Marchi (c) November 2015
 */
public class UserInteractionSimulationTest {
	
	
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	
	@Test
	public void testExit() {
		exit.expectSystemExitWithStatus(0);
		systemInMock.provideLines("Frank","6");
		RockPaperScissorConsole.main(new String[]{});
	}

	@Test
	@SuppressWarnings("deprecation")
	public void userFrankCreateFirstGameAndPlayRockThenExit() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
	    		assertThat(systemOutRule.getLog().split("\r\n")[19],
	    				isOneOf(
		    				"Game firstgame played 1 with score 0/1 with last outcome as LOST",
		    				"Game firstgame played 1 with score 1/0 with last outcome as WIN"));
	        }
		});
		systemInMock.provideLines("Frank","1","firstgame","4","rock","6");
		RockPaperScissorConsole.main(new String[]{});
	}
	
	@Test
	public void userFrankCreateFirstGameAndPlayScissorThenPlayRockThenExit() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
	        public void checkAssertion() {
	        	assertThat(systemOutRule.getLog().split("\r\n")[27],
	        			matchesPattern("Game firstgame played 2 with score [0-9]/[0-9] with last outcome as (?:WIN|LOST)"));
	        }
		});
		systemInMock.provideLines("Frank","1","firstgame","4","scissor","4","rock","6");
		RockPaperScissorConsole.main(new String[]{});
	}
	
	@Test
	public void userFrankCreateFirstGameAndPlayScissorThenPlayRockThenPlayPaperThenExit() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
	        public void checkAssertion() {
	        	assertThat(systemOutRule.getLog().split("\r\n")[35],
	        			matchesPattern("Game firstgame played 3 with score [0-9]/[0-9] with last outcome as (?:WIN|LOST)"));
	        }
		});
		systemInMock.provideLines("Frank","1","firstgame","4","scissor","4","rock","4","paper","6");
		RockPaperScissorConsole.main(new String[]{});
	}
	
	@Test
	public void userFrankCreateComputerVsComputerGameAndPlayOneTime() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
	        public void checkAssertion() {
	        	assertThat(systemOutRule.getLog().split("\r\n")[19],
	        			matchesPattern("Game aiVSai_game played 1 with score [0-9]/[0-9] with last outcome as (?:WIN|LOST)"));
	        }
		});
		systemInMock.provideLines("Frank","2","aiVSai_game","5","\n","6");
		RockPaperScissorConsole.main(new String[]{});
	}
	
	@Test
	public void userFrankCreateComputerVsComputerGameAndPlayTenTimes() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
	        public void checkAssertion() {
	        	assertThat(systemOutRule.getLog().split("\r\n")[91],
	        			matchesPattern("Game aiVSai_game played 10 with score [0-9]/[0-9] with last outcome as (?:WIN|LOST)"));
	        }
		});
		systemInMock.provideLines("Frank","2","aiVSai_game","5","\n","5","\n","5","\n","5","\n","5","\n","5","\n","5","\n","5","\n","5","\n","5","\n","6");
		RockPaperScissorConsole.main(new String[]{});
	}
	
	@Test
	public void userJackCreateComputerVsComputerGameAndPlayOneTimeThenCreateAnoterGamePlayerVsComputerAndPlayThreeTimes() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
	        public void checkAssertion() {
	        	assertThat(systemOutRule.getLog().split("\r\n")[51],
	        			matchesPattern("Game anothergame played 3 with score [0-9]/[0-9] with last outcome as (?:WIN|LOST)"));
	        }
		});
		systemInMock.provideLines("Jack","2","aiVSai_game","5","\n","1","anothergame","4","paper","4","scissor","4","paper","6");
		RockPaperScissorConsole.main(new String[]{});
	}

}
