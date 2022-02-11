package Networking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Networking.KeyInput;
import Networking.NetGame;
import Networking.NetworkInterface;
import Networking.Server;

/**
 * @author Tom Byrne
 */
public class NetworkInterface {

	private static Server netServer;
	private static final int DEFAULT_PORT = 1111;
	private static String host = "localhost";
	private static int port;

	public static void networkOptions() {

		// First, construct a panel that will be placed into a JOptionPane
		// confirm dialog.

		JLabel message = new JLabel("MazeTank! Select your network options:", JLabel.CENTER);
		message.setFont(new Font("Serif", Font.BOLD, 16));

		final JTextField listeningPortInput = new JTextField("" + DEFAULT_PORT, 5);
		final JTextField hostInput = new JTextField(host);
		final JTextField connectPortInput = new JTextField("" + DEFAULT_PORT, 5);

		final JRadioButton selectServerMode = new JRadioButton("Start a new game");
		final JRadioButton selectClientMode = new JRadioButton("Connect to existing game");

		ButtonGroup group = new ButtonGroup();
		group.add(selectServerMode);
		group.add(selectClientMode);
		ActionListener radioListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == selectServerMode) {
					listeningPortInput.setEnabled(true);
					hostInput.setEnabled(false);
					connectPortInput.setEnabled(false);
					listeningPortInput.setEditable(true);
					hostInput.setEditable(false);
					connectPortInput.setEditable(false);
				} else {
					listeningPortInput.setEnabled(false);
					hostInput.setEnabled(true);
					connectPortInput.setEnabled(true);
					listeningPortInput.setEditable(false);
					hostInput.setEditable(true);
					connectPortInput.setEditable(true);
				}
			}
		};
		selectServerMode.addActionListener(radioListener);
		selectClientMode.addActionListener(radioListener);
		selectServerMode.setSelected(true);
		hostInput.setEnabled(false);
		connectPortInput.setEnabled(false);
		hostInput.setEditable(false);
		connectPortInput.setEditable(false);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0, 1, 5, 5));
		inputPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				BorderFactory.createEmptyBorder(6, 6, 6, 6)));

		inputPanel.add(message);

		JPanel row;

		inputPanel.add(selectServerMode);

		row = new JPanel();
		row.setLayout(new FlowLayout(FlowLayout.LEFT));
		row.add(Box.createHorizontalStrut(40));
		row.add(new JLabel("Listen on port: "));
		row.add(listeningPortInput);
		inputPanel.add(row);

		inputPanel.add(selectClientMode);

		row = new JPanel();
		row.setLayout(new FlowLayout(FlowLayout.LEFT));
		row.add(Box.createHorizontalStrut(40));
		row.add(new JLabel("Host Name: "));
		row.add(hostInput);
		inputPanel.add(row);

		row = new JPanel();
		row.setLayout(new FlowLayout(FlowLayout.LEFT));
		row.add(Box.createHorizontalStrut(40));
		row.add(new JLabel("Port Number: "));
		row.add(connectPortInput);
		inputPanel.add(row);

		while (true) { // Repeats until a game is started or the user cancels.

			int action = JOptionPane.showConfirmDialog(null, inputPanel, "MazeTank - Networking Options",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (action != JOptionPane.OK_OPTION)
				return;

			if (selectServerMode.isSelected()) {

				try {
					port = Integer.parseInt(listeningPortInput.getText().trim());
					if (port <= 0)
						throw new Exception();
				} catch (Exception e) {
					message.setText("Illegal port number!");
					listeningPortInput.selectAll();
					listeningPortInput.requestFocus();
					continue;
				}
				try {
					System.out.println("selectserver");
					Thread thread1 = new Thread() {
						public void run() {
							// Create a new Server using the entered port.
							netServer = new Server(port);
							System.out.println("started server");
						}
					};
					thread1.start();

				} catch (Exception e) {
					message.setText("Error: Can't listen on port " + port);
					listeningPortInput.selectAll();
					listeningPortInput.requestFocus();
					continue;
				}
				break;
			} else {

				host = hostInput.getText().trim();
				if (host.length() == 0) {
					message.setText("Please enter a computer name!");
					hostInput.requestFocus();
					continue;
				}
				try {
					port = Integer.parseInt(connectPortInput.getText().trim());
					if (port <= 0)
						throw new Exception();
				} catch (Exception e) {
					message.setText("Error: Illegal port number!");
					connectPortInput.selectAll();
					connectPortInput.requestFocus();
					continue;

				}

				break;
			}
		}

	}

	public static String getHost() {
		return host;
	}

	public static int getPort() {
		return port;
	}
}
