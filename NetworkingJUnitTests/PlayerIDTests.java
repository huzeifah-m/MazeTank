package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.PlayerID;

public class PlayerIDTests {

	@Test
	public void incTest() {
		PlayerID id = new PlayerID();
		PlayerID id2 = new PlayerID();
		id.increment();
		assertNotEquals(id, id2);
	}
	@Test
	public void decTest() {
		PlayerID id = new PlayerID();
		PlayerID id2 = new PlayerID();
		id.decrement();
		assertNotEquals(id, id2);
	}
}
