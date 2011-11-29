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
 * @author João
 */
public class Enemie4 extends GameObjectSprite {

    public static final int[] ANIM_MOVE = {0};
    private int contBack;
    private int contGo;
    private boolean correto;
    private int velocidade;

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Enemie4(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(true);
        contBack = 0;
        contGo = 0;
        correto = true;
    }

    public void update() {
        if (correto) {
            setSpeedX(velocidade);
            contGo++;
        } else {
            setSpeedX(-velocidade);
            contBack++;
        }


        if (contGo <= 60) {
            correto = true;
            contBack = 0;
        } else {
            if (contBack <= 30) {
                correto = false;
            } else {
                correto = true;
                contGo = 0;
            }
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
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public void collided(GameObjectSprite collidedObject) {
    }
}
