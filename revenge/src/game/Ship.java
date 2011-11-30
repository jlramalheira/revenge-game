/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GameObjectSprite;
import framework.Key;
import framework.Screen;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import main.Global;

/**
 *
 * @author João
 */
public class Ship extends GameObjectSprite{

    public static final int[] ANIM_MOVE = {0,1,2};

    public Ship(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(true);
    }

    public void update() {
        //Controle da Nave
        if (Key.LEFT_PRESSED) {
            setX(getX() - Global.SHIP_SPEED);
        } else if (Key.RIGHT_PRESSED) {
            setX(getX() + Global.SHIP_SPEED);
        }

        if (getX() < 0) {
            setX(0);
        } else if ((getX() + getWidth()) > Screen.getWidth()) {
            setX(Screen.getWidth() - getWidth());
        }
        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public void collided(GameObjectSprite collidedObject) {
    }
    
}
