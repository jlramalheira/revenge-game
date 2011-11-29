/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GameObjectSprite;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author João
 */
public class Shield extends framework.GameObjectSprite {

    public Shield(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        setActive(false);
        setVisible(false);
    }

    public void update() {
        setY(getY() + getSpeedY());
    }

    public void paint(Graphics g) {
        drawSprite(g);
    }

    public void collided(GameObjectSprite collidedObject) {
        
    }
    
}
