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
public abstract class ParticleSystem extends ObjectsCollection {
    public static final int PARTICLE_VECTOR_OVAL = 0;
    public static final int PARTICLE_VECTOR_RECT = 1;
    public static final int PARTICLE_VECTOR_ROUND_RECT = 2;
    
    // parametros para configurar o sistema de particulas
    protected int minNumParticles;
    protected int maxNumParticles;
    protected int minDelayPerFrame = 1;
    protected int maxDelayPerFrame = 1;
    protected double minInitialSpeed;
    protected double maxInitialSpeed;
    protected double minAcceleration;
    protected double maxAcceleration;
    protected double minScale;
    protected double maxScale;
    protected int minLifetime;
    protected int maxLifetime;
    protected double minDirectionAngle;
    protected double maxDirectionAngle;
    protected double gravity;

    public ParticleSystem(int particleFrameWidth, int particleFrameHeight, 
            int numberOfParticles, int particlesColor, int vectorType) {
        super(numberOfParticles);

        initializeConstants();
        for (int i = 0; i < numberOfParticles; i++) {
            Particle p = new ParticleVector(particlesColor, particleFrameWidth, 
                    particleFrameHeight, vectorType);
            addObject(p);
        }
    }
    
    public ParticleSystem(Image particleImage, int particleFrameWidth,
            int particleFrameHeight, int numberOfParticles) {
        super(numberOfParticles);

        initializeConstants();
        for (int i = 0; i < numberOfParticles; i++) {
            Particle p = new Particle(particleImage, particleFrameWidth, particleFrameHeight);
            addObject(p);
        }
    }

    /**
     * Deve ser implementado no sistema de particulas a ser criado.
     * Em seu conteudo, deve definir os valores MIN e MAX dos atributos de controle
     * do sistema de particulas.
     */
    protected abstract void initializeConstants();

    
    /**
     * Adiciona uma ou mais particulas ao jogo na posicao do objeto especificado.
     * O numero de particulas depende dos atributos minNumParticles e maxNumParticles.
     * @param object As particulas serao lancadas no centro do objeto.
     */
    public void addParticles(GameObjectSprite object) {
        addParticles(object.getCenterX(), object.getCenterY());
    }    
    /**
     * Adiciona uma ou mais particulas ao jogo na posicao especificada.
     * O numero de particulas depende dos atributos minNumParticles e maxNumParticles.
     * @param x Posicao X da particula.
     * @param y Posicao Y da particula.
     */
    public void addParticles(double x, double y) {
        int numParticles = (int) Util.randomBetween(minNumParticles, maxNumParticles);

        for (int i = 0; i < numParticles && getAvailableSize() > 0; i++) {
            Particle p = (Particle) getAvailableObject();
            initializeParticle(p, x-p.getWidth()/2, y-p.getHeight()/2);
        }
    }

    /**
     * Prepara cada particula a iniciar. Deve ser sobrescrito caso queira modificar
     * as caracteristicas da particula a ser iniciada.
     * @param p Particula que sera inciada.
     * @param x Posicao X para a particula.
     * @param y Posicao Y para a particula.
     */
    protected void initializeParticle(Particle p, double x, double y) {

        double direction = Util.randomBetween(minDirectionAngle, maxDirectionAngle);
        double speed = Util.randomBetween(minInitialSpeed, maxInitialSpeed);
        double acceleration = Util.randomBetween(minAcceleration, maxAcceleration);
        int lifetime = (int) Util.randomBetween(minLifetime, maxLifetime);
        double scale = Util.randomBetween(minScale, maxScale);
        int delayPerFrame = (int) Util.randomBetween(minDelayPerFrame, maxDelayPerFrame);

        p.initialize(x, y,
                speed * Util.cos(direction), speed * Util.sin(direction),
                acceleration * Util.cos(direction), acceleration * Util.sin(direction),
                lifetime*delayPerFrame, scale, gravity);
        p.setAnimationDelay(delayPerFrame);
        p.setCurrentFrame(0);
    }

    public void update() {
        for (int i = 0; i < getTotalSize(); i++) {
            Particle p = (Particle) getObject(i);
            if (p.isActive()) {
                p.update();
                if (!p.isActive()) {
                    makeObjectAvailable(p);
                }
            }
        }
    }

    public void paint(Graphics g) {
        paintVisibleObjects(g);
    }

    
// OLD PAINT (FROM XNA)
//            double normalizedLifetime = p.getLifeFramesCount() / p.getLifeFrames();
//            double alpha = 4 * normalizedLifetime * (1 - normalizedLifetime);
//
//            // make particles grow as they age. they'll start at 75% of their size,
//            // and increase to 100% once they're finished.
//            double scale = p.getScale() * (0.75 + 0.25 * normalizedLifetime);
//            g.setColor((int)scale, (int)scale, (int)scale);
}