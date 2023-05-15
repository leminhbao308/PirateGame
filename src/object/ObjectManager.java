package object;

import entities.Player;
import gamestate.Playing;
import levels.Level;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.ObjectConstants.*;
import static utilz.HelpMethods.CanCannonSeePLayer;

public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private BufferedImage[] cannonImgs;
    private BufferedImage spikeImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<Cannon> cannons;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
    }

    public void checkSpikesTouched(Player player) {
        for (Spike spike : spikes) {
            if (spike.getHitbox().intersects(player.getHitbox())) {
                player.kill();
            }
        }
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (Potion potion : potions) {
            if (potion.isActive()) {
                if (hitbox.intersects(potion.getHitbox())) {
                    potion.setActive(false);
                    applyEffectToPlayer(potion);
                }
            }
        }
    }

    public void applyEffectToPlayer(Potion potion) {
        if (potion.getObjType() == RED_POTION)
            playing.getPlayer().changeHealth(RED_POTION_VALUE);
        else
            playing.getPlayer().changePower(BLUE_POTION_VALUE);
    }

    public void checkObjectHit(Rectangle2D.Float attackBox) {
        for (GameContainer container : containers) {
            if (container.isActive() && !container.doAnimation) {
                if (container.getHitbox().intersects(attackBox)) {
                    container.setAnimation(true);

                    int type = 0;

                    if (container.getObjType() == BARREL)
                        type = 1;
                    potions.add(new Potion((int) (container.getHitbox().x + container.getHitbox().width / 2),
                            (int) (container.getHitbox().y - container.getHitbox().height / 2),
                            type));
                    return;
                }
            }
        }
    }

    public void loadObjects(Level newLevel) {
        potions = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
        spikes = newLevel.getSpikes();
        cannons = newLevel.getCannons();
    }

    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];
        for (int i = 0; i < potionImgs.length; i++) {
            for (int j = 0; j < potionImgs[i].length; j++) {
                potionImgs[i][j] = potionSprite.getSubimage(j * 12, i * 16, 12, 16);
            }
        }
        BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];
        for (int i = 0; i < containerImgs.length; i++) {
            for (int j = 0; j < containerImgs[i].length; j++) {
                containerImgs[i][j] = containerSprite.getSubimage(j * 40, i * 30, 40, 30);
            }
        }
        spikeImg = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);

        cannonImgs = new BufferedImage[7];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CANNON_ATLAS);
        for (int i = 0; i < cannonImgs.length; i++) {
            cannonImgs[i] = temp.getSubimage(i * 40, 0, 40, 26);
        }
    }

    public void update(int[][] lvlData, Player player) {
        for (Potion potion : potions) {
            if (potion.isActive())
                potion.update();
        }
        for (GameContainer container : containers) {
            if (container.isActive())
                container.update();
        }
        updateCannons(lvlData, player);
    }

    private boolean isPlayerInRange(Cannon cannon, Player player) {
        int absValue = (int) Math.abs(player.getHitbox().x - cannon.getHitbox().x);
        return absValue <= Game.TILES_SIZE * 5;
    }

    private boolean isPLayerInfontOfCannon(Cannon cannon, Player player) {
        if (cannon.getObjType() == CANNON_LEFT)
            if (cannon.getHitbox().x > player.getHitbox().x)
                return true;
            else if (cannon.getHitbox().x < player.getHitbox().x)
                return true;
        return false;
    }

    private void updateCannons(int[][] lvlData, Player player) {
        for (Cannon cannon : cannons) {
//            if (cannon.doAnimation)
            if (cannon.getTileY() == player.getTileY())
                if (isPlayerInRange(cannon, player))
                    if (isPLayerInfontOfCannon(cannon, player))
                        if (CanCannonSeePLayer(lvlData, player.getHitbox(), cannon.getHitbox(), cannon.getTileY()))
                            shootCannon(cannon);
            cannon.update();
        }
    }

    private void shootCannon(Cannon cannon) {
        cannon.setAnimation(true);
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawPotions(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
        drawSpikes(g, xLvlOffset);
        drawCannons(g, xLvlOffset);
    }

    private void drawPotions(Graphics g, int xLvlOffset) {
        for (Potion potion : potions) {
            if (potion.isActive()) {
                int type = 0;
                if (potion.getObjType() == RED_POTION)
                    type = 1;
                g.drawImage(potionImgs[type][potion.getAnimationIndex()],
                        (int) (potion.getHitbox().x - potion.getXDrawOffset() - xLvlOffset),
                        (int) (potion.getHitbox().y - potion.getyDrawOffset()),
                        POTION_WIDTH, POTION_HEIGHT, null);
            }
        }
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for (GameContainer container : containers) {
            if (container.isActive()) {
                int type = 0;
                if (container.getObjType() == BARREL)
                    type = 1;

                g.drawImage(containerImgs[type][container.getAnimationIndex()],
                        (int) (container.getHitbox().x - container.getXDrawOffset() - xLvlOffset),
                        (int) (container.getHitbox().y - container.getyDrawOffset()),
                        CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
            }
        }
    }

    private void drawSpikes(Graphics g, int xLvlOffset) {
        for (Spike spike : spikes) {
            g.drawImage(spikeImg,
                    (int) (spike.getHitbox().x - xLvlOffset),
                    (int) (spike.getHitbox().y - spike.getyDrawOffset()),
                    SPIKE_WIDTH, SPIKE_HEIGHT, null);

        }
    }

    private void drawCannons(Graphics g, int xLvlOffset) {
        for (Cannon cannon : cannons) {
            int x = (int) (cannon.getHitbox().x - xLvlOffset);
            int width = CANNON_WIDTH;
            if (cannon.getObjType() == CANNON_RIGHT) {
                x += CANNON_WIDTH;
                width *= -1;
            }
            g.drawImage(cannonImgs[cannon.getAnimationIndex()],
                    x,
                    (int) (cannon.getHitbox().y),
                    width, CANNON_HEIGHT, null);
        }
    }

    public void resetAllObjects() {
        loadObjects(playing.getLevelManager().getCurrentLevel());

        for (Potion potion : potions) {
            potion.reset();
        }
        for (GameContainer container : containers) {
            container.reset();
        }
        for (Cannon cannon : cannons) {
            cannon.reset();
        }
    }
}
