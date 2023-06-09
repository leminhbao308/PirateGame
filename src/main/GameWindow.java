package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private JFrame frame;

	public GameWindow(GamePanel gamePanel) {
		frame = new JFrame();
		frame.setTitle("First Game");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame.add(gamePanel);
		frame.setResizable(false);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
