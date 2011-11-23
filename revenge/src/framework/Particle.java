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
public class Particle extends GameObjectSprite {
    private double accelerationX;
    private double accelerationY;
    private double scale;
    private double gravity;
    private int lifeFrames;
    private int lifeFramesCount;

    public Particle() {
        setActive(false);
        setVisible(false);
    }

    public Particle(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(false);
        setVisible(false);
    }
    
    public void initialize(double x, double y, double speedX, double speedY, 
            double accelerationX, double accelerationY, int lifetime, double scale, double gravity) {
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
        this.lifeFrames = lifetime;
        this.scale = scale;
        this.gravity = gravity;
        setX(x);
        setY(y);
        setSpeedX(speedX);
        setSpeedY(speedY);
        
        this.lifeFramesCount = 0;
    }
    
    public void update() {
        // vary velocity (acelerate)
        setSpeedX(getSpeedX() + accelerationX);
        setSpeedY(getSpeedY() + accelerationY - gravity);
        
        // move the particle
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
        
        // update life time control
        lifeFramesCount++;
        
        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }
    
    public boolean isActive() {
        return lifeFramesCount < lifeFrames;
    }

    public void collided(GameObjectSprite collidedObject) {
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

    public double getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}