/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.TiledLayer;

/**
 *
 * @author marcos
 */
public class BackgroundTiled extends GameObject {
    public static final int SCROLL_NONE  = 0;
    public static final int SCROLL_UP    = 1;
    public static final int SCROLL_DOWN  = 2;
    public static final int SCROLL_RIGHT = 3;
    public static final int SCROLL_LEFT  = 4;

    private int scroll;
    private TiledLayer tiledLayer;

    public BackgroundTiled(TiledLayer tiledLayer, int scroll) {
        this.tiledLayer = tiledLayer;
        setWidth(tiledLayer.getWidth());
        setHeight(tiledLayer.getHeight());
        setScroll(scroll);
    }

    public TiledLayer getTiledLayer() {
        return tiledLayer;
    }

    public void setScroll(int scroll) {
        this.scroll = scroll;
        switch(scroll) {
            case SCROLL_LEFT:
                if (getSpeedX() == 0)
                    setSpeed(1);
                setX(0);
                setY(0);
                break;
            case SCROLL_RIGHT:
                if (getSpeedX() == 0)
                    setSpeed(1);
                setX(-(getWidth()-Screen.getWidth()));
                setY(0);
                break;
            case SCROLL_UP:   
                if (getSpeedY() == 0)
                    setSpeed(1);
                setX(0);
                setY(0);
                break;
            case SCROLL_DOWN: 
                if (getSpeedY() == 0)
                    setSpeed(1);
                setX(0);
                setY(-(getHeight()-Screen.getHeight()));
                break;
        }
    }

    public void setX(int x) {
        tiledLayer.setPosition(x, (int)getY());
        super.setX(x);
    }

    public void setY(int y) {
        tiledLayer.setPosition((int)getX(), y);
        super.setY(y);
    }

    public boolean isMapEnded() {
        if (scroll == SCROLL_LEFT) {
            if (getX() < -(getWidth()-Screen.getWidth())) {
                return true;
            }
        } else
        if (scroll == SCROLL_RIGHT) {
            if (getX() >= 0) {
                return true;
            }
        } else
        if (scroll == SCROLL_UP) {
            if (getY() < -(getHeight()-Screen.getHeight())) {
                return true;
            }
        } else
        if (scroll == SCROLL_DOWN) {
            if (getY() >= 0) {
                return true;
            }
        }
        return false;
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
        //tiledLayer.move(getSpeedX(), getSpeedY());
        moveBySpeedXY();
    }

    public void paint(Graphics g) {
        tiledLayer.paint(g);
    }
}
