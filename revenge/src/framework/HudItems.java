/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Utilitario para desenhar componentes da interface do jogo.
 * @author marcos
 */
public class HudItems extends GameObjectSprite {
    private int items = 3;
    private int itemSpace = 5;
    private String text;
    private int textPosY;

    public HudItems(String text, Image image, int frameWidth, int frameHeight, 
            int numberOfItems, double x, double y) {
        super(image, frameWidth, frameHeight);
        this.items = numberOfItems;
        this.text = text;
        setX(x);
        textPosY = (int)y; // posicao do texto eh Y
        int textCenterY = (int)(y + Text.CHAR_HEIGHT/2);
        // posicao da image eh alinhada em Y com o centro do texto
        setY(textCenterY - image.getHeight()/2); 
    }
    
    public void update() {
    }

    public void paint(Graphics g) {
        double startX = getX();
        Text.drawText(text, (int)getX(), textPosY, g);
        // deixa um espaco de uma letra entre texto e desenhos
        setX(getX() + Text.getTextWidth(text) + Text.CHAR_WIDTH); 
        
        for (int i = 0; i < items; i++) {
            animate();
            drawSprite(g);
            setX(getX() + getWidth() + itemSpace);
        }
        setX(startX);
    }

    public void collided(GameObjectSprite collidedObject) {
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getItemSpace() {
        return itemSpace;
    }

    public void setItemSpace(int itemSpace) {
        this.itemSpace = itemSpace;
    }
}
