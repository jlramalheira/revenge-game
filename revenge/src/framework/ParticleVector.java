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
public class ParticleVector extends Particle {
    private double particleWidth;
    private double particleHeight;
    private double currentWidth;
    private double currentHeight;
    private int vectorType;
    private int color;

    public ParticleVector(int color, int particleWidth, int  particleHeight, int vectorType) {
        this.currentWidth = particleWidth;
        this.currentHeight = particleHeight;
        this.vectorType = vectorType;
        this.particleWidth = particleWidth;
        this.particleHeight = particleHeight;
        this.color = color;
    }

    public void initialize(double x, double y, double speedX, double speedY, double accelerationX, double accelerationY, int lifetime, double scale, double gravity) {
        super.initialize(x, y, speedX, speedY, accelerationX, accelerationY, lifetime, scale, gravity);
        currentWidth = particleWidth;
        currentHeight = particleHeight;
    }
    
    public void paint(Graphics g) {
//        double colorRatio = (1 - ((double)getLifeFramesCount() / getLifeFrames()))*color;
////        g.setColor((int)(colorRatio),(int)(colorRatio),(int)(colorRatio));
//        g.setColor((int)(colorRatio));

        g.setColor(color);
        currentWidth += getScale();
        currentHeight+= getScale();
        
        switch(vectorType) {
            case ParticleSystem.PARTICLE_VECTOR_OVAL: 
                g.fillArc((int)getX(), (int)getY(), (int)currentWidth, (int)currentHeight, 0, 360);
                break;
            case ParticleSystem.PARTICLE_VECTOR_RECT: 
                g.fillRect((int)getX(), (int)getY(), (int)currentWidth, (int)currentHeight);
                break;
            case ParticleSystem.PARTICLE_VECTOR_ROUND_RECT: 
                g.fillRoundRect((int)getX(), (int)getY(), (int)currentWidth, (int)currentHeight,25,25);
                break;
        }
    }

    public int getHeight() {
        return (int)currentHeight;
    }

    public int getWidth() {
        return (int)currentWidth;
    }

    public void setHeight(int height) {
        this.currentHeight = height;
    }

    public void setWidth(int width) {
        this.currentWidth = width;
    }
}
