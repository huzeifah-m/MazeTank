package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.Player;
import Networking.ServerPlayer;

public class ServerPlayerTests {


	@Test
	public void setGetXTest() {
		ServerPlayer player = new ServerPlayer(10, 10, 10);
		ServerPlayer player2 = new ServerPlayer(0, 10, 10);
		player2.setX(10);
		assertEquals(player.getX(), player2.getX());
	}
	@Test
	public void setGetYTest() {
		ServerPlayer player = new ServerPlayer(10, 10, 10);
		ServerPlayer player2 = new ServerPlayer(10, 0, 10);
		player2.setY(10);
		assertEquals(player.getY(), player2.getY());
	}
	
	@Test
	public void setGetVelXTest() {
		ServerPlayer player = new ServerPlayer(10, 10, 10);
		ServerPlayer player2 = new ServerPlayer(10, 10, 10);
		player.setVelX(5);
		assertNotEquals(player.getVelX(), player2.getVelX());
		player2.setVelX(5);
		assertEquals(player.getVelX(), player2.getVelX());
	}
	@Test
	public void setGetVelYTest() {
		ServerPlayer player = new ServerPlayer(10, 10, 10);
		ServerPlayer player2 = new ServerPlayer(10, 10, 10);
		player.setVelY(5);
		assertNotEquals(player.getVelY(), player2.getVelY());
		player2.setVelY(5);
		assertEquals(player.getVelY(), player2.getVelY());
	}
	@Test
	public void setGetIDTest() {
		ServerPlayer player = new ServerPlayer(10, 10, 10);
		player.setID(5);
		assertEquals(player.getID(), 5);
	}
	
}
