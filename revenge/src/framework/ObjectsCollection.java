/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.Stack;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author marcos
 */
public class ObjectsCollection {

    private Vector objects;
    private Stack freeObjects;

    public ObjectsCollection(int initialCapacity) {
        freeObjects = new Stack();
        objects = new Vector(initialCapacity);
    }

    // apenas dentro do framework
    protected Vector getObjects() {
        return objects;
    }

    public void addObject(GameObjectSprite object) {
        objects.addElement(object);
        freeObjects.push(object);
    }

    public GameObjectSprite getObject(int index) {
        if (index < objects.size()) {
            return (GameObjectSprite) objects.elementAt(index);
        }
        return null;
    }

    public int getTotalSize() {
        return objects.capacity();
    }

    public int getAvailableSize() {
        return freeObjects.size();
    }

    public GameObjectSprite getAvailableObject() {
        if (freeObjects.size() > 0) {
            GameObjectSprite obj = (GameObjectSprite) freeObjects.pop();
            obj.setActive(true);
            obj.setVisible(true);
            return obj;
        }
        return null;
    }

    public void makeAllObjectsAvailable() {
        freeObjects.removeAllElements();
        for (int i = 0; i < objects.size(); i++) {
            GameObjectSprite obj = (GameObjectSprite) objects.elementAt(i);
            obj.setActive(false);
            obj.setVisible(false);
            freeObjects.push(obj);
        }
    }

    public void makeObjectAvailable(GameObjectSprite object) {
        if (!freeObjects.contains(object)) {
            object.setActive(false);
            object.setVisible(false);
            freeObjects.push(object);
        }
    }

    public void removeAll() {
        objects.removeAllElements();
    }

    public void updateActiveObjects() {
        for (int i = 0; i < objects.size(); i++) {
            GameObjectSprite obj = (GameObjectSprite) objects.elementAt(i);
            if (!obj.isActive()) {
                continue;
            }
            obj.update();
        }
    }

    public void paintVisibleObjects(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObjectSprite obj = (GameObjectSprite) objects.elementAt(i);
            if (!obj.isVisible()) {
                continue;
            }
            obj.paint(g);
        }
    }

    public boolean collidesWithActiveObjects(GameObjectSprite target, boolean precise) {
        for (int i = 0; i < objects.size(); i++) {
            GameObjectSprite obj = (GameObjectSprite) objects.elementAt(i);
            if (obj.isActive()) {
                if (target.collidesWith(obj, precise)) {
                    target.collided(obj);
                    obj.collided(target);
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return "ObjectsCollection{" + "objects=" + objects + "\nfreeObjects=" + freeObjects + '}';
    }
    
}