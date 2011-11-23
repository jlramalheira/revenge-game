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
public abstract class BulletLauncher extends ObjectsCollection {

    private int fireDelay;
    private int fireDelayCount;

    public BulletLauncher(Image image, int frameWidth, int frameHeight,
            int fireDelay, int numberOfBullets) {
        super(numberOfBullets);
        
        for (int i = 0; i < numberOfBullets; i++) {
            Bullet b = createBullet(image, frameWidth, frameHeight, this);
            b.setAnimationDelay(fireDelay);
            addObject(b);
        }
        this.fireDelay = fireDelay;
    }
    
    public abstract Bullet createBullet(Image image, int frameWidth, int frameHeight, 
            BulletLauncher bulletLauncher);

    public void fire(double x, double y, double direction, double speed) {
        if (fireDelayCount > fireDelay) {
            Bullet b = (Bullet) getAvailableObject();
            if (b != null) {
                b.fire(x - b.getWidth() / 2, y - b.getHeight() / 2, direction, speed);
                fireDelayCount = 0;
            }
        }
    }

    public void update() {
        fireDelayCount++;
        updateActiveObjects();
    }

    public void paint(Graphics g) {
        paintVisibleObjects(g);
    }
}
