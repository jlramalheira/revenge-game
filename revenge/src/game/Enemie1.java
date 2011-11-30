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
public class Enemie1 extends GameObjectSprite {

    public static final int[] ANIM_MOVE = {0, 1, 2};

    public Enemie1(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(true);
        setAnimationDelay(10);
    }

    public void update() {

        if (getSpeedX() > 0) {
            if (getX() > 620) { //625 é o numero para 10 inimigos
                setX(0 - getWidth());
            }
        } else {
            if (getX() < -425) { //425 é o numero para 10 inimigos para a esquerda
                setX(Screen.getWidth());
            }
        }

        setX(getX() + getSpeedX());
        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public void collided(GameObjectSprite collidedObject) {
    }
}
