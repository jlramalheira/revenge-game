/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.ParticleSystem;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Max
 */
public class ParticleSystemExplosion extends ParticleSystem {
    public ParticleSystemExplosion(Image particleImage, int particleFrameWidth, int particleFrameHeigh
            , int minNumParticles) {
        super(particleImage, particleFrameWidth, particleFrameHeigh, minNumParticles);
    }

    protected void initializeConstants() {
        minInitialSpeed = 1;
        maxInitialSpeed = 2;

        minAcceleration = 0.1;
        maxAcceleration = 0.5;
        
        minDirectionAngle = 45;
        maxDirectionAngle = 135;
        
        gravity = 0;

        // delayPerFrame x numberOfFrames
        minLifetime = 2*4;
        maxLifetime = 2*4;

        minScale = 0.1;
        maxScale = 0.5;

        minNumParticles = 5;
        maxNumParticles = 7;
    }
}
