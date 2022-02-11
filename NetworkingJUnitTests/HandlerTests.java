package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.GameObject;
import Networking.Handler;
import Networking.Projectile;

public class HandlerTests {

	@Test
	public void addObjectTest() {
		Projectile proj = new Projectile(0, 0, 0, 0, 0, null);
		Handler usedHandler = new Handler();
		Handler unusedHandler = new Handler();
		usedHandler.addObject(proj);
		assertNotEquals(usedHandler, unusedHandler);
	}
	@Test
	public void removeObjectTest() {
		Projectile proj = new Projectile(0, 0, 0, 0, 0, null);
		Handler handler = new Handler();
		Handler handler2 = new Handler();
		handler.addObject(proj);
		handler.removeObject(proj);
		assertNotEquals(handler, handler2);
	}
	@Test
	public void removeAllTest() {
		Projectile proj = new Projectile(0, 0, 0, 0, 0, null);
		Handler handler = new Handler();
		Handler handler2 = new Handler();
		handler.addObject(proj);
		handler.removeAll();
		assertNotEquals(handler, handler2);
	}

}
