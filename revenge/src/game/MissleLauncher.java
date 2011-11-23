/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.Bullet;
import framework.BulletLauncher;
import javax.microedition.lcdui.Image;

/**
 *
 * @author João
 */
public class MissleLauncher extends BulletLauncher{

    public MissleLauncher(Image image, int frameWidth, int frameHeight, int fireDelay, int numberOfBullets) {
        super(image, frameWidth, frameHeight, fireDelay, numberOfBullets);
    }
    
    public Bullet createBullet(Image image, int frameWidth, int frameHeight, BulletLauncher bulletLauncher) {
        return new Missle(image, frameWidth, frameHeight, bulletLauncher);
    }
    
}
