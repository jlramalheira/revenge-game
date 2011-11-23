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
public class Background extends GameObjectSprite {
    public static final int SCROLL_NONE  = 0;
    public static final int SCROLL_UP    = 1;
    public static final int SCROLL_DOWN  = 2;
    public static final int SCROLL_RIGHT = 3;
    public static final int SCROLL_LEFT  = 4;

    private int offsetX, offsetY;
    private int scroll;
    private boolean scrollEnded = false;

    public Background(Image img, int scroll) {
        super(img);
        setScroll(scroll);
    }

    public void setScroll(int scroll) {
        this.scroll = scroll;
        switch(scroll) {
            case SCROLL_LEFT:
                if (getSpeedX() == 0)
                    setSpeed(1);
                offsetX = getWidth();
                break;
            case SCROLL_RIGHT:
                if (getSpeedX() == 0)
                    setSpeed(1);
                offsetX = -getWidth();
                break;
            case SCROLL_UP:   
                if (getSpeedY() == 0)
                    setSpeed(1);
                offsetY = getHeight();
                break;
            case SCROLL_DOWN: 
                if (getSpeedY() == 0)
                    setSpeed(1);
                offsetY = -getHeight();
                break;
        }
    }

    public boolean isScrollEnded() {
        return scrollEnded;
    }

    public void setSpeed(int speed) {
        if (scroll == SCROLL_DOWN) {
            setSpeedY(Math.abs(speed));
        } else
        if (scroll == SCROLL_UP) {
            setSpeedY(-Math.abs(speed));
        } else
        if (scroll == SCROLL_LEFT) {
            setSpeedX(-Math.abs(speed));
        } else
        if (scroll == SCROLL_RIGHT) {
            setSpeedX(Math.abs(speed));
        }
    }

    public void update() {
        moveBySpeedXY();

        if (scroll == SCROLL_LEFT) {
            if (getX() < -getWidth()) {
                setX(0);
                scrollEnded = true;
            } else {
                scrollEnded = false;
            }
        } else
        if (scroll == SCROLL_RIGHT) {
            if (getX() >= getWidth()) {
                setX(0);
                scrollEnded = true;
            } else {
                scrollEnded = false;
            }
        } else
        if (scroll == SCROLL_UP) {
            if (getY() < -getHeight()) {
                setY(0);
                scrollEnded = true;
            } else {
                scrollEnded = false;
            }
        }
        if (scroll == SCROLL_DOWN) {
            if (getY() >= getHeight()) {
                setY(0);
                scrollEnded = true;
            } else {
                scrollEnded = false;
            }
        }
    }

    public void paint(Graphics g) {
        drawSprite(g, getX(), getY());
        if (scroll != SCROLL_NONE) {
            drawSprite(g, getX() + offsetX, getY() + offsetY);
        }
    }

    public void collided(GameObjectSprite collidedObject) {
    }    
}
