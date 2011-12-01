/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import framework.Background;
import framework.Key;
import framework.Menu;
import framework.MenuListener;
import framework.Screen;
import framework.Text;
import framework.Util;
import javax.microedition.lcdui.Graphics;
import main.Global;
import main.Midlet;

/**
 *
 * @author João
 */
public class SelectGameScreen extends Screen implements MenuListener {

    private static final int STATE_MENU = 0;
    private static final int STATE_INSTRUCTION = 1;
    private static final int STATE_CREDITS = 2;
    private int gameState;
    private Background bgIntro;
    private Background bgLine;
    private Background bgCredit;
    private Background bgStars;
    private Menu menu;

    public SelectGameScreen() {
        loadResources();
        initGame();
    }
    
    public final void loadResources() {
        bgIntro = new Background(Util.loadImage(Global.IMG_BG_INTRO), Background.SCROLL_NONE);
        bgLine = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
        bgCredit = new Background(Util.loadImage(Global.IMG_BG_CREDITS), Background.SCROLL_NONE);
        bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
        
        String []items = new String[]{"INICIAR", "INSTRUCOES", "CREDITOS"};
        menu = new Menu(items, 60, 190, 120, this);
        menu.setBackgroundColor(0xFFFFFF);
    }

    public final void initGame() {
        gameState = STATE_MENU;
    }

    public void update() {
        bgIntro.update();
        bgLine.update();
        bgStars.update();
        bgCredit.update();
        menu.update();
        switch (gameState) {
            case STATE_MENU:
                updateMenu();
                break;
            case STATE_INSTRUCTION:
                updateInstruciton();
                break;
            case STATE_CREDITS:
                updateCredit();
                break;
        }
    }

    public void paint(Graphics g) {
        
        switch (gameState) {
            case STATE_MENU:
                paintMenu(g);
                break;
            case STATE_INSTRUCTION:
                paintInstruction(g);
                break;
            case STATE_CREDITS:
                paintCredits(g);
                break;
        }
    }

    public void paintInstruction(Graphics g) {
        bgLine.paint(g);
        bgStars.paint(g);
        Text.drawText("---REVENGE OF INVADERS---", 15, 15, g);
        Text.drawText("!! INSTRUCOES !!", 15, 35, g);
        Text.drawText("DIRECIONAIS MOVE A NAVE", 15, 75, g);
        Text.drawText("FIRE PARA ATIRAR", 15, 95, g);
        Text.drawText("COLETE OS ITENS QUE CAEM", 15, 135, g);
        Text.drawText("DESVIE DOS TIROS INIMIGOS", 15, 115, g);
        
        
    }

    public void menuAction(int menuIndex) {
        switch(menuIndex) {
            case 0:
                Screen.setCurrentScreen(new ScreenGame());
                break;
            case 1:
                gameState = STATE_INSTRUCTION;
                break;
            case 2:
                gameState = STATE_CREDITS;
                break;
        }
    }

    private void paintMenu(Graphics g) {
        bgIntro.paint(g);
        menu.paint(g);
    }

    private void paintCredits(Graphics g) {
        bgCredit.paint(g);
    }

    private void updateInstruciton() {
        if (Key.ACTION_LEFT) {
            gameState = STATE_MENU;
        }
        if (Key.ACTION_RIGHT) {
            gameState = STATE_MENU;
        }
    }

    private void updateCredit() {
        if (Key.ACTION_LEFT) {
            gameState = STATE_MENU;
        }
        if (Key.ACTION_RIGHT) {
            gameState = STATE_MENU;
        }
    }

    private void updateMenu() {
        if (Key.ACTION_LEFT) {
            Midlet.getInstance().exit();
        }
    }
}
