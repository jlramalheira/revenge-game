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
public class Enemie1 extends GameObjectSprite{

    public static final int[] ANIM_MOVE = {0};

    public Enemie1(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(true);
    }


    public void update() {
        setX(getX() + getSpeedX());
        if (getSpeedX() > 0) {
            if (getX() > 620) { //625 é o numero para 10 inimigos
                setX(0 - getWidth());
            }
        } else {
            if (getX() < -425) { //425 é o numero para 10 inimigos para a esquerda
                setX(Screen.getWidth());
            }
        }
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public void collided(GameObjectSprite collidedObject) {
    }
    
}
