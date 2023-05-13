package main;

import input.KeyboardInput;
import input.MouseInput;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private MouseInput mouseInput;
	private Game game;

	public GamePanel(Game game) {

		mouseInput = new MouseInput(this);
		this.game = game;

		addKeyListener(new KeyboardInput(this));
		setPanelSize();
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);

	}

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}

	public void updateGame() {
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}

}
