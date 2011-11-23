package framework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Marcos
 */
public class Menu extends GameObject{

    public static final int CENTER = -1;

    private String[] items;
    private int lineSpace = 5;
    private int borderColor= 0x000000;
    private int backgroundColor  = 0xDDDDDD;
    private int borderSpace= 5;
    private int borderSize = 2;
    private String selector = "-";
    private int selectedIndex = 0;
    private MenuListener listener;

    public Menu(String[] items, int x, int y, int width, MenuListener listener) {
        this.items = items;
        setWidth(width);
        setHeight(items.length*(Text.CHAR_HEIGHT + lineSpace)+borderSpace*2);
        if (x == CENTER)
            x = Util.centerX(width);
        if (y == CENTER)
            y = Util.centerY(getHeight());
        setX(x);
        setY(y);
        this.listener = listener;
    }

    private void paintBox(Graphics g) {

        g.setColor(borderColor);
        g.fillRoundRect((int)getX(), (int)getY(), 
                getWidth(), items.length*(Text.CHAR_HEIGHT + lineSpace)+borderSpace*2,
                10, 10);

        g.setColor(backgroundColor);
        g.fillRoundRect((int)getX()+borderSize, (int)getY()+borderSize,
                getWidth()-borderSize*2,
                items.length*(Text.CHAR_HEIGHT + lineSpace)+borderSpace*2-borderSize*2,
                10, 10);
    }

    public void paint(Graphics g) {
        paintBox(g);

        for (int i = 0; i < items.length; i++) {
            String text = items[i];

            Text.drawText(text,
                    (int)getX() + borderSpace + selector.length()*Text.CHAR_WIDTH*2 ,
                    (int)getY()+(Text.CHAR_HEIGHT+lineSpace)*i+borderSpace, g);
        }

        // selector
        Text.drawText(selector,
                (int)getX() + borderSpace ,
                (int)getY()+(Text.CHAR_HEIGHT+lineSpace)*selectedIndex+borderSpace, g);
    }

    public void update() {
        if (Key.DOWN) {
            selectedIndex++;
            if (selectedIndex >= items.length) {
                selectedIndex = 0;
            }
        } else
        if (Key.UP) {
            selectedIndex--;
            if (selectedIndex < 0) {
                selectedIndex = items.length-1;
            }
        } else
        if (Key.FIRE) {
            listener.menuAction(selectedIndex);
        }
    }

    public int getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public int getBorderSpace() {
        return borderSpace;
    }

    public void setBorderSpace(int borderSpace) {
        this.borderSpace = borderSpace;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public void setItem(String item, int index) {
        items[index] = item;
    }

    public String getItem(int index) {
        return items[index];
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
