/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author marcos
 */
public class HudHints extends ParticleSystem {
    private int lifeFrames;
    private Sprite[] spriteHints;

    public HudHints(int maxNumberOfHints, int numberOfTypes, int lifeFrames, int framesDelay) {
        super(Image.createImage(1, 1), 1, 1, maxNumberOfHints);
        minDelayPerFrame = framesDelay;
        maxDelayPerFrame = framesDelay;
        spriteHints = new Sprite[numberOfTypes];
        this.lifeFrames = lifeFrames;
    }

    public void addHintType(int hintId, Image hintImage, int frameWidth, int frameHeight) {
        if (hintId < spriteHints.length) {
            spriteHints[hintId] = new Sprite(hintImage, frameWidth, frameHeight);
        }
    }

    public void show(int type, double x, double y) {
        if (type < spriteHints.length && getAvailableSize() > 0) {
            Particle p = (Particle) getAvailableObject();
            p.setSprite(spriteHints[type]);
            p.initialize(x - p.getWidth() / 2, y - p.getHeight() / 2, 0, -1, 0, 0, lifeFrames, 0, 0);
        }
    }
    
    protected void initializeConstants() {
        minInitialSpeed = 1;
        maxInitialSpeed = 1;

        minAcceleration = 0;
        maxAcceleration = 0;
        
        minDirectionAngle = 90;
        maxDirectionAngle = 90;
        
        gravity = 0;

        // delayPerFrame x numberOfFrames
        minLifetime = lifeFrames;
        maxLifetime = lifeFrames;
        
        minScale = 0.1;
        maxScale = 0.5;

        minNumParticles = 1;
        maxNumParticles = 1;
    }
}
