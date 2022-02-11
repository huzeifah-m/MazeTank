package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.SystemException;

import Networking.NetGameEngine;

public class NetGameEngineTests {

	@Test
	public void getPlayer1ScoreTest() {
		NetGameEngine test = new NetGameEngine();
		assertEquals(null, test.getPlayer1Score());
	}
	@Test
	public void getPlayer2ScoreTest() {
		NetGameEngine test = new NetGameEngine();
		assertEquals(null, test.getPlayer2Score());
	}
	@Test
	public void setPlayer1ScoreValueTest() {
		NetGameEngine test = new NetGameEngine();
		test.setPlayer1Value(10);
		assertEquals(10, test.getPlayer1ScoreValue());
	}
	
	@Test
	public void setPlayer2ScoreTest() {
		NetGameEngine test = new NetGameEngine();
		test.setPlayer2Value(10);
		assertEquals(10, test.getPlayer2ScoreValue());
	}

}
