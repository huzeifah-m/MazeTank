package NetworkingJUnitTests;

import static org.junit.Assert.*;
import org.junit.Test;

import Networking.Projectile;
import Networking.Handler;


public class ProjectileTests {

	@Test
	public void test() {
		Handler handler = new Handler();
		Projectile bullet = new Projectile(0, 0, 0, 0, 0, handler);
		Projectile bullet2 = new Projectile(0, 0, 0, 0, 0, handler);
		Projectile bullet3 = new Projectile(15,15,10,0,0, handler);
		assertEquals(bullet.getBounds(), bullet2.getBounds());
		assertNotEquals(bullet.getBounds(), bullet3.getBounds());
	}
}
