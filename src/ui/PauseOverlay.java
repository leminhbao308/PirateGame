package ui;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;

public class PauseOverlay {

    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH; // W = Width, H = Height
    private SoundButton musicButton, sfxButton;

    public PauseOverlay() {

        loadBackground();
        createSoundButtons();
    }

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        //Background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        //Sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseRelease(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseMove(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
