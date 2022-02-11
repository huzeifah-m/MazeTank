package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.NetworkInterface;

public class NetworkInterfaceTests {

	@Test
	public void getHostTest() {
		NetworkInterface netint = new NetworkInterface();
		assertEquals(netint.getHost(), "localhost");
	}
	@Test
	public void getPortTest() {
		NetworkInterface netint = new NetworkInterface();
		System.out.println(netint.getPort());
		assertEquals(netint.getPort(), 0);
	}

}
