/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GameObjectSprite;
import framework.Screen;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author João
 */
public class Boss extends GameObjectSprite {

    private int contToDown;
    private boolean down;

    public Boss(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        contToDown = 0;
        down = false;
    }

    public void update() {

        if (getX() < 0) {
            setSpeedX(-getSpeedX());
        } else if ((getX() + getWidth()) > Screen.getWidth()) {
            setSpeedX(-getSpeedX());
        }

        if (getY() < 0) {
            setSpeedY(-getSpeedY());
        } else if ((getY() + getHeight()) > Screen.getHeight()) {
            setSpeedY(-getSpeedY());
        }

        //verifica pra descer
        if (!down) {
            contToDown++;
        }

        if (down) {
            if (getY() < 1) {
                down = false;
                contToDown = 0;
            }
        }

        if (getY() > 1) {
            down = true;
        }

        if (contToDown > 110) {
            setY(getY() + getSpeedY());
        } else {
            setX(getX() + getSpeedX());
        }

        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);

    }

    public void collided(GameObjectSprite collidedObject) {
    }
}
