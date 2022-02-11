package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.ConcreteNetGameObject;

public class GameObjectTests {

	@Test
	public void getXTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		int expectedX = 0;
		int actualX = (int) test.getX();
		assertEquals(actualX, expectedX);
	}
	@Test
	public void getYTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		int expectedY = 0;
		int actualY = (int) test.getY();
		assertEquals(actualY, expectedY);
	}
	@Test
	public void setXTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(100, 100, 1);
		test.setX(50);
		int expectedX = 50;
		int actualX = (int) test.getX();
		assertEquals(actualX, expectedX);
	}
	@Test
	public void setYTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(100, 100, 1);
		test.setY(50);
		int expectedY = 50;
		int actualY = (int) test.getY();
		assertEquals(actualY, expectedY);
	}
	@Test
	public void getBounceTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(10, 10, 1);
		test.setBounces(10);
		assertEquals(10, test.getBounces());
	}
	@Test
	public void setBounceTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		test.setBounces(2);
		assertEquals(2, test.getBounces());
	}
	@Test
	public void getIDTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		assertEquals(1, test.getId());
	}
	@Test
	public void getVelXTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(2, 2, 1);
		test.setVelX(2);
		assertEquals(2, test.getVelX());
	}
	@Test
	public void setVelXTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		test.setVelX(10);
		assertEquals(10, test.getVelX());
	}
	@Test
	public void getVelYTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(2, 2, 1);
		test.setVelY(2);
		int actualOutcome = (int) test.getVelY();
		assertEquals(2, actualOutcome);
	}
	@Test
	public void setVelYTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		test.setVelY(10);
		int actualOutcome = (int) test.getVelY();
		assertEquals(10, actualOutcome);
	}
	@Test
	public void getVelAngleTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		int actualOutcome = (int) test.getVelAngle();
		assertEquals(0, actualOutcome);
	}
	@Test
	public void setVelAngleTest() {
		ConcreteNetGameObject test = new ConcreteNetGameObject(0, 0, 1);
		test.setVelAngle(2);
		int actualOutcome = (int) test.getVelAngle();
		assertEquals(2, actualOutcome);
	}
}
