package utilz;

import main.Game;

public class Constants {

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_2 = 7;
        public static final int ATTACK_3 = 8;
        public static final int ATTACK_AIR_1 = 9;
        public static final int ATTACK_AIR_2 = 10;
        public static final int THROW_SWORD = 11;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {

                case IDLE:
                    return 5;
                case RUNNING:
                    return 6;
                case JUMP:
                    return 3;
                case FALLING:
                    return 1;
                case GROUND:
                    return 2;
                case HIT:
                    return 4;
                case ATTACK_1:
                    return 3;
                case ATTACK_2:
                    return 3;
                case ATTACK_3:
                    return 3;
                case ATTACK_AIR_1:
                    return 3;
                case ATTACK_AIR_2:
                    return 3;
                case THROW_SWORD:
                    return 3;
                default:
                    return 0;
            }
        }
    }
}
