/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GameObjectSprite;
import framework.Screen;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author a968501
 */
public class Enemie2 extends GameObjectSprite {

    public static final int[] ANIM_MOVE = {0};
    private int contStop;
    private int contGo;
    private boolean flying;
    private int velocidade;

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    
    public Enemie2(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(true);
        contStop = 0;
        contGo = 10;
        flying = true;
    }

    public void update() {
        if (flying){
            contStop++;
        } else {
            contGo++;
        }
        if (contStop >= 60) {
            contStop = 0;
            setSpeedX(0);
            flying = false;
        }
        if (contGo >= 10){
            setSpeedX(velocidade);
            contGo=0;
            flying = true;
        }
        
        setX(getX() + getSpeedX());
        if (getSpeedX() > 0) {
            if (getX() > 620) { //625 é o numero para 10 inimigos
                setX(0 - getWidth());
            }
        } else {
            if (getX() < -425) { //425 é o numero para 10 inimigos para a esquerda
                setX(Screen.getWidth());
            }
        }
        animate();
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public void collided(GameObjectSprite collidedObject) {
    }
}
