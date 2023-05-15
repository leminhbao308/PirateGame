package object;

import entities.Player;
import gamestate.Playing;
import levels.Level;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.ObjectConstants.*;

public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private BufferedImage spikeImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;

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
                    apllyEffectToPlayer(potion);
                }
            }
        }
    }

    public void apllyEffectToPlayer(Potion potion) {
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
    }

    public void update() {
        for (Potion potion : potions) {
            potion.update();
        }
        for (GameContainer container : containers) {
            container.update();
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawPotions(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
        drawSpikes(g, xLvlOffset);
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

    public void resetAllObjects() {
        loadObjects(playing.getLevelManager().getCurrentLevel());

        for (Potion potion : potions) {
            potion.reset();
        }
        for (GameContainer container : containers) {
            container.reset();
        }

    }
}
