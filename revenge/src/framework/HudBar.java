/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import javax.microedition.lcdui.Graphics;

/**
 * Utilitario para desenhar componentes da interface do jogo.
 * @author marcos
 */
public class HudBar extends GameObject {
    private int backColor = 0xFFFFFF;
    private int barColor = 0x0000FF;
    private int borderColor = 0x000000;
    private int borderSize = 2;
    private int maxValue = 10;
    private int value = 10;

    public HudBar(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void update() {
    }

    public void paint(Graphics g) {
        g.setColor(borderColor);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());

        int proportionalWidth = (getWidth()-borderSize*2)*value/maxValue;

        g.setColor(backColor);
        g.fillRect((int)getX()+borderSize, (int)getY()+borderSize,
                getWidth()-borderSize*2, getHeight()-borderSize*2);
        g.setColor(barColor);
        g.fillRect((int)getX()+borderSize, (int)getY()+borderSize,
                proportionalWidth, getHeight()-borderSize*2);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }

    public int getBarColor() {
        return barColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }
}
