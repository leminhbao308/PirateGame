package input;

import gamestate.Gamestate;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

	private GamePanel gamePanel;

	public MouseInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().mouseMove(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseMove(e);
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (Gamestate.state) {
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseClicked(e);
				break;
			default:
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().mousePressed(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mousePressed(e);
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().mouseRelease(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseRelease(e);
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
