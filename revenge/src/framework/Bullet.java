/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author marcos
 */
public abstract class Bullet extends GameObjectSprite {

    private BulletLauncher bulletLauncher;

    public Bullet(Image image, int frameWidth, int frameHeight, BulletLauncher bulletLauncher) {
        super(image, frameWidth, frameHeight);
        this.bulletLauncher = bulletLauncher;
    }

    public abstract void fire(double x, double y, double direction, double speed);

    public void update() {
        moveBySpeedXY();
        if (outOfScreen()) {
            setActive(false);
            getBulletLauncher().makeObjectAvailable(this);
        }
        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public BulletLauncher getBulletLauncher() {
        return bulletLauncher;
    }

    public void setBulletLauncher(BulletLauncher bulletLauncher) {
        this.bulletLauncher = bulletLauncher;
    }
}
