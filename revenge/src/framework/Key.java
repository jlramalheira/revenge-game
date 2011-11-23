package framework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Marcos
 */
public class Key {
	public static int gameKey  = 0; // teclas pressionadas, obtido pelo GameCanvas
    public static int canvasKey= 0;// teclas normais, obtidas pelo Canvas

    public static boolean LEFT_PRESSED = false;
    public static boolean RIGHT_PRESSED= false;
    public static boolean DOWN_PRESSED = false;
    public static boolean UP_PRESSED   = false;
    public static boolean FIRE_PRESSED = false;
    public static boolean POUND_PRESSED= false;
    public static boolean STAR_PRESSED = false;
    public static boolean ACTION_LEFT_PRESSED = false;
    public static boolean ACTION_RIGHT_PRESSED= false;

    public static boolean LEFT = false;
    public static boolean RIGHT= false;
    public static boolean DOWN = false;
    public static boolean UP   = false;
    public static boolean FIRE = false;
    public static boolean POUND= false;
    public static boolean STAR = false;
    public static boolean ACTION_LEFT = false;
    public static boolean ACTION_RIGHT= false;

    public static void updateKeys() {
        //      Nokia e Sony            LG
        if ((canvasKey == -7) || (gameKey == 1024)) {
            if (ACTION_RIGHT_PRESSED == true) {
                ACTION_RIGHT = false;
            } else {
                ACTION_RIGHT_PRESSED = true;
                ACTION_RIGHT = true;
            }
        } else {
            ACTION_RIGHT_PRESSED = false;
            ACTION_RIGHT = false;
        }

        //      Nokia e Sony            LG
        if ((canvasKey == -6) || (gameKey == 512)){
            if (ACTION_LEFT_PRESSED == true) {
                ACTION_LEFT = false;
            } else {
                ACTION_LEFT_PRESSED = true;
                ACTION_LEFT = true;
            }
        } else {
            ACTION_LEFT_PRESSED = false;
            ACTION_LEFT = false;
        }

        if (canvasKey == GameCanvas.KEY_STAR) {
            if (STAR_PRESSED == true) {
                STAR = false;
            } else {
                STAR_PRESSED = true;
                STAR = true;
            }
        } else {
            STAR_PRESSED = false;
            STAR = false;
        }
        
        if (canvasKey == GameCanvas.KEY_POUND) {
            if (POUND_PRESSED == true) {
                POUND = false;
            } else {
                POUND_PRESSED = true;
                POUND = true;
            }
        } else {
            POUND_PRESSED = false;
            POUND = false;
        }

        if ((gameKey & GameCanvas.LEFT_PRESSED) != 0) {
            if (LEFT_PRESSED == true) {
                LEFT = false;
            } else {
                LEFT_PRESSED = true;
                LEFT = true;
            }
        } else {
            LEFT_PRESSED = false;
            LEFT = false;
        }

        if ((gameKey & GameCanvas.RIGHT_PRESSED) != 0) {
            if (RIGHT_PRESSED == true) {
                RIGHT = false;
            } else {
                RIGHT_PRESSED = true;
                RIGHT = true;
            }
        } else {
            RIGHT_PRESSED = false;
            RIGHT = false;
        }

        if ((gameKey & GameCanvas.DOWN_PRESSED) != 0) {
            if (DOWN_PRESSED == true) {
                DOWN = false;
            } else {
                DOWN_PRESSED = true;
                DOWN = true;
            }
        } else {
            DOWN_PRESSED = false;
            DOWN = false;
        }

        if ((gameKey & GameCanvas.UP_PRESSED) != 0) {
            if (UP_PRESSED == true) {
                UP = false;
            } else {
                UP_PRESSED = true;
                UP = true;
            }
        } else {
            UP_PRESSED = false;
            UP = false;
        }

        if ((gameKey & GameCanvas.FIRE_PRESSED) != 0) {
            if (FIRE_PRESSED == true) {
                FIRE = false;
            } else {
                FIRE_PRESSED = true;
                FIRE = true;
            }
        } else {
            FIRE_PRESSED = false;
            FIRE = false;
        }
    }
}
