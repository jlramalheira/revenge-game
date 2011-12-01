/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import framework.GameEngine;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import screen.SelectGameScreen;

/**
 * @author marcos
 */
public class Midlet extends MIDlet {
    private static Midlet instance;

    public void startApp() {
        instance = this;
        GameEngine engine = new GameEngine(Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT, true);
        GameEngine.setShowInfo(Global.SHOW_INFO);
        engine.start(new SelectGameScreen());
        Display.getDisplay(this).setCurrent(engine);
    }

    public static Midlet getInstance() {
        return instance;
    }

    public void exit() {
        destroyApp(false);
        notifyDestroyed();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
