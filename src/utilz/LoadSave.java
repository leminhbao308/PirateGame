package utilz;

import entities.Crabby;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.CRABBY;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Character_Main.png";
    public static final String LEVEL_ATLAS = "Outside_Terrain.png";
    //    public static final String LEVEL_ONE_DATA = "Level_one_data.png";
    public static final String LEVEL_ONE_DATA = "Level_one_data_long.png";
    public static final String MENU_BUTTONS = "Menu_button.png";
    public static final String MENU_BACKGROUND = "Menu_background.png";
    public static final String PAUSE_BACKGROUND = "Pause_menu.png";
    public static final String SOUND_BUTTONS = "Sound_button.png";
    public static final String URM_BUTTONS = "Urm_buttons.png";
    public static final String VOLUME_BUTTONS = "Volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "Background_menu.png";
    public static final String PLAYING_BACKGROUND_IMG = "Playing_bg_img.png";
    public static final String BIG_CLOUDS = "Big_clouds.png";
    public static final String SMALL_CLOUDS = "Small_clouds.png";
    public static final String CRABBY_SPRITE = "Crabby_sprite.png";
    public static final String STATUS_BAR = "Health_power_bar.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {

        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static ArrayList<Crabby> GetCrabs() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list = new ArrayList<>();

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getGreen();
                if (value == CRABBY)
                    list.add(new Crabby(j * Game.TILES_SIZE, i * Game.TILES_SIZE));
            }
        }
        return list;
    }

    public static int[][] GetLevelData() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[i][j] = value;
            }
        }
        return lvlData;
    }
}
