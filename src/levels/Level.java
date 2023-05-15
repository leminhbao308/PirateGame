package levels;

import entities.Crabby;
import main.Game;
import object.Cannon;
import object.GameContainer;
import object.Potion;
import object.Spike;
import utilz.HelpMethods;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.HelpMethods.*;

public class Level {

    private BufferedImage img;
    private int[][] lvlData;
    private ArrayList<Crabby> crabbies;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<Cannon> cannons;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point playerSpawn;

    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createEnemies();
        createPotions();
        createContainers();
        createSpikes();
        createCannons();
        calcLvlOffset();
        calcPlayerSpawn();
    }

    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    private void createEnemies() {
        crabbies = GetCrabs(img);
    }

    private void createPotions() {
        potions = HelpMethods.GetPotions(img);
    }

    private void createContainers() {
        containers = HelpMethods.GetContainers(img);
    }

    private void createSpikes() {
        spikes = HelpMethods.GetSpikes(img);
    }

    private void createCannons() {
        cannons = HelpMethods.GetCannons(img);
    }


    private void calcLvlOffset() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public ArrayList<Crabby> getCrabs() {
        return crabbies;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

}
