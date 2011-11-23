/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.Bullet;
import framework.BulletLauncher;
import framework.GameObjectSprite;
import framework.Util;
import javax.microedition.lcdui.Image;

/**
 *
 * @author João
 */
public class Missle extends Bullet{

    public Missle(Image image, int frameWidth, int frameHeight, BulletLauncher bulletLauncher) {
        super(image, frameWidth, frameHeight, bulletLauncher);
        setActive(false);
    }
    
    public void fire(double x, double y, double direction, double speed) {
        setSpeedX(speed * Util.cos(direction));
        setSpeedY(speed * Util.sin(direction));
        setX(x);
        setY(y);
        setActive(true);
        setDirection(direction);
        defineSpriteByDirection();
    }

    public void collided(GameObjectSprite collidedObject) {
        setActive(false);
        getBulletLauncher().makeObjectAvailable(this);
    }
    
}
