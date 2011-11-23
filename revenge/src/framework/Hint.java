/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author marcos
 */
public class Hint extends GameObjectSprite {
    private int lifeFramesCount;
    private int lifeFrames;

    public void update() {
        moveBySpeedXY();
        // update life time control
        lifeFramesCount++;
        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }
    
    public void collided(GameObjectSprite collidedObject) {
    }
    
    public boolean isActive() {
        return lifeFramesCount < lifeFrames;
    }

    public int getLifeFrames() {
        return lifeFrames;
    }

    public void setLifeFrames(int lifeFrames) {
        this.lifeFrames = lifeFrames;
    }

    public int getLifeFramesCount() {
        return lifeFramesCount;
    }

    public void setLifeFramesCount(int lifeFramesCount) {
        this.lifeFramesCount = lifeFramesCount;
    }
}
