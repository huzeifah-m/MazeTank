package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.ConcreteGameObject;
import GameMain.GameObject;
import GameMain.ID;

public class GameObjectTests {

	@Test
	public void getXTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		int expectedX = 0;
		int actualX = (int) test.getX();
		assertEquals(actualX, expectedX);
	}
	@Test
	public void getYTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		int expectedY = 0;
		int actualY = (int) test.getY();
		assertEquals(actualY, expectedY);
	}
	@Test
	public void setXTest() {
		ConcreteGameObject test = new ConcreteGameObject(100, 100, null);
		test.setX(50);
		int expectedX = 50;
		int actualX = (int) test.getX();
		assertEquals(actualX, expectedX);
	}
	@Test
	public void setYTest() {
		ConcreteGameObject test = new ConcreteGameObject(100, 100, null);
		test.setY(50);
		int expectedY = 50;
		int actualY = (int) test.getY();
		assertEquals(actualY, expectedY);
	}
	@Test
	public void getBounceTest() {
		ConcreteGameObject test = new ConcreteGameObject(10, 10, null);
		test.setBounces(10);
		assertEquals(10, test.getBounces());
	}
	@Test
	public void setBounceTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		test.setBounces(2);
		assertEquals(2, test.getBounces());
	}
	@Test
	public void getIDTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		assertEquals(null, test.getId());
	}
	@Test
	public void getVelXTest() {
		ConcreteGameObject test = new ConcreteGameObject(2, 2, null);
		test.setVelX(2);
		assertEquals(2, test.getVelX());
	}
	@Test
	public void setVelXTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		test.setVelX(10);
		assertEquals(10, test.getVelX());
	}
	@Test
	public void getVelYTest() {
		ConcreteGameObject test = new ConcreteGameObject(2, 2, null);
		test.setVelY(2);
		int actualOutcome = (int) test.getVelY();
		assertEquals(2, actualOutcome);
	}
	@Test
	public void setVelYTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		test.setVelY(10);
		int actualOutcome = (int) test.getVelY();
		assertEquals(10, actualOutcome);
	}
	@Test
	public void getVelAngleTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		int actualOutcome = (int) test.getVelAngle();
		assertEquals(0, actualOutcome);
	}
	@Test
	public void setVelAngleTest() {
		ConcreteGameObject test = new ConcreteGameObject(0, 0, null);
		test.setVelAngle(2);
		int actualOutcome = (int) test.getVelAngle();
		assertEquals(2, actualOutcome);
	}
}
