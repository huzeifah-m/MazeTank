package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.SystemException;

import GameMain.GameEngineAi;

public class GameEngineAiTests {

	@Test
	public void getPlayerAiScoreTest() {
		GameEngineAi test = new GameEngineAi();
		assertEquals(null, test.getPlayerAiScore());
	}
	@Test
	public void setPlayerAiScoreValueTest() {
		GameEngineAi test = new GameEngineAi();
		test.setPlayerAiValue(10);
		assertEquals(10, test.getPlayerAiScoreValue());
	}
	
	@Test
	public void getPlayer1ScoreTest() {
		GameEngineAi test = new GameEngineAi();
		assertEquals(null, test.getPlayer1Score());
	}
	@Test
	public void setPlayer1ScoreValueTest() {
		GameEngineAi test = new GameEngineAi();
		test.setPlayer1Value(10);
		assertEquals(10, test.getPlayer1ScoreValue());
	}

}
