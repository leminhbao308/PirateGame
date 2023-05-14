package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

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
    public static final String COMPLETED_IMG = "Completed_sprite.png";

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

    public static BufferedImage[] GetAllLevels() {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];
        for (int i = 0; i < filesSorted.length; i++) {
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals("" + (i + 1) + ".png"))
                    filesSorted[i] = files[j];
            }
        }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];
        for (int i = 0; i < imgs.length; i++)
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return imgs;
    }


}
