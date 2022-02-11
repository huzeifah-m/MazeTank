package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.SystemException;

import GameMain.GameEngine;

public class GameEngineTests {

	@Test
	public void getPlayer1ScoreTest() {
		GameEngine test = new GameEngine();
		assertEquals(null, test.getPlayer1Score());
	}
	@Test
	public void getPlayer2ScoreTest() {
		GameEngine test = new GameEngine();
		assertEquals(null, test.getPlayer2Score());
	}
	@Test
	public void setPlayer1ScoreValueTest() {
		GameEngine test = new GameEngine();
		test.setPlayer1Value(10);
		assertEquals(10, test.getPlayer1ScoreValue());
	}
	
	@Test
	public void setPlayer2ScoreTest() {
		GameEngine test = new GameEngine();
		test.setPlayer2Value(10);
		assertEquals(10, test.getPlayer2ScoreValue());
	}

}
