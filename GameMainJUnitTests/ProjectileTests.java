package GameMainJUnitTests;

import static org.junit.Assert.*;
import org.junit.Test;

import GameMain.Projectile;
import GameMain.Handler;
import GameMain.ID;

public class ProjectileTests {

	@Test
	public void test() {
		Handler handler = new Handler();
		Projectile bullet = new Projectile(5,5,0, handler, null);
		Projectile bullet2 = new Projectile(5,5,0, handler, null);
		Projectile bullet3 = new Projectile(15,15,10, handler, null);
		assertEquals(bullet.getBounds(), bullet2.getBounds());
		assertNotEquals(bullet.getBounds(), bullet3.getBounds());
	}
}
