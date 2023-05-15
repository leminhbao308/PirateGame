package object;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.ProjectTiles.*;

public class ProjectTile {

    private Rectangle2D.Float hitbox;
    private int direction;
    private boolean active = true;

    public ProjectTile(int x, int y, int direction) {
        int xOffset = (int) (-3 * Game.SCALE);
        int yOffset = (int) (5 * Game.SCALE);

        if (direction == 1)
            xOffset = (int) (29 * Game.SCALE);
        this.hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT);
        this.direction = direction;
    }

    public void updatePosition() {
        hitbox.x += direction * SPEED;
    }

    public void setPosition(int x, int y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
