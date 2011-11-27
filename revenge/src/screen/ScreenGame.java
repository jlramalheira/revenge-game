/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import framework.*;
import game.Enemie1;
import game.MissileLauncher;
import game.Ship;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import main.Global;

/**
 *
 * @author João
 */
public class ScreenGame extends Screen {

    public static final int STATE_TITLE = 0;
    public static final int STATE_PLAY = 1;
    public static final int STATE_GAMEOVER = 2;
    private int gameSate;
    private Background bgLined;
    private Background bgStars;
    private Ship ship;
    private ObjectsCollection enimiesup;
    private ObjectsCollection enimiesdown;
    private MissileLauncher missleLauncher;
    private int quantidadeEnimie;

    public ScreenGame() {
        loadResources();
        initGame();
    }

    public final void loadResources() {
        //Game objects
        bgLined = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
        bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
        ship = new Ship(Util.loadImage(Global.IMG_SHIP), 50, 50);
        enimiesup = new ObjectsCollection(Global.ENIMIES_MAX_NUMBER);
        enimiesdown = new ObjectsCollection(Global.ENIMIES_MAX_NUMBER);
        quantidadeEnimie = Global.ENIMIES_MAX_NUMBER * 2;
        Image imageEnimie1 = Util.loadImage(Global.IMG_ENEMIE_1);
        for (int i = 0; i < enimiesup.getTotalSize(); i++) {
            Enemie1 en = new Enemie1(imageEnimie1, 52, 30);
            en.setX((52 * i) + (15 * i));
            en.setY(10);
            en.setSpeedX(4);
            enimiesup.addObject(en);
        }

        for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
            Enemie1 en = new Enemie1(imageEnimie1, 52, 30);
            en.setX((52 * i) + (15 * i));
            en.setY(55);
            en.setSpeedX(-3);
            enimiesdown.addObject(en);
        }

        Image imageMissle = Util.loadImage(Global.IMG_MISSLE);
        missleLauncher = new MissileLauncher(imageMissle,
                14, 4, Global.BULLET_FIRE_FELAY, Global.BULLET_MAX_NUMBER);

    }

    /**
     * Aqui criamos e carregamos todos os recursos usados na tela do jogo.
     */
    public final void initGame() {
        ship.setX(Util.centerX(ship.getWidth()));
        ship.setY(Screen.getHeight() - ship.getHeight() - 20);
        ship.setAnimation(Ship.ANIM_MOVE);
        ship.setTransformation(Ship.TRANS_NONE);

        missleLauncher.makeAllObjectsAvailable();

    }

    public void update() {
        switch (gameSate) {
            case STATE_TITLE:
                updateGameTitle();
                break;

            case STATE_PLAY:
                updateGameScoreAndLevel();
                updateGameObjects();
                updateCollisions();
                break;

            case STATE_GAMEOVER:
                if (Key.FIRE) {
                    initGame();
                }
                break;
        }

    }

    private void updateGameTitle() {
        bgStars.update();
        if (Key.FIRE) {
            gameSate = STATE_PLAY;
        }
    }

    private void updateCollisions() {
        //imimigos com nave
        if (enimiesup.collidesWithActiveObjects(ship, true)) {
            gameSate = STATE_GAMEOVER;
        }
        //nave com tiro
        for (int i = 0; i < enimiesup.getTotalSize(); i++) {
            Enemie1 en = (Enemie1) enimiesup.getObject(i);
            if (en.isActive()){
                if (missleLauncher.collidesWithActiveObjects(en, false)){
                    en.setActive(false);
                    en.setVisible(false);
                    enimiesup.makeObjectAvailable(en);
                    quantidadeEnimie--;
                }
            }
        }
         for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
            Enemie1 en = (Enemie1) enimiesdown.getObject(i);
            if (en.isActive()){
                if (missleLauncher.collidesWithActiveObjects(en, false)){
                    en.setActive(false);
                    en.setVisible(false);
                    enimiesdown.makeObjectAvailable(en);
                    quantidadeEnimie--;
                }
            }
        }
    }

    private void updateGameObjects() {
        if (Key.FIRE) {
            missleLauncher.fire(ship.getCenterX(), ship.getCenterY(),
                    90, Global.BULLET_SPEED);
        }
        bgLined.update();
        bgStars.update();
        ship.update();
        missleLauncher.update();

        enimiesup.updateActiveObjects();
        enimiesdown.updateActiveObjects();
    }

    private void updateGameScoreAndLevel() {
        if (quantidadeEnimie <= 0){
            //passou de fase
        }
    }

    public void paint(Graphics g) {
        switch (gameSate) {
            case STATE_TITLE:
                paintTitle(g);
                break;
            case STATE_PLAY:
                paintGameObjects(g);
                paintHud(g);
                break;
            case STATE_GAMEOVER:
                paintGameOver(g);
                break;
        }
    }

    private void paintGameObjects(Graphics g) {
        bgLined.paint(g);
        bgStars.paint(g);
        ship.paint(g);
        missleLauncher.paint(g);

        enimiesup.paintVisibleObjects(g);
        enimiesdown.paintVisibleObjects(g);
        

    }

    private void paintHud(Graphics g) {
    }

    private void paintGameOver(Graphics g) {
        Text.drawText("G-A-M-E O-V-E-R", Text.CENTER, 150, g);
        Text.drawText("APERTE -FIRE- PARA REINICIAR", Text.CENTER, 200, g);
        paintHud(g);
    }

    private void paintTitle(Graphics g) {
        bgLined.paint(g);
        bgStars.paint(g);
        Text.drawText("APERTE -FIRE- PARA INICIAR", Text.CENTER, 180, g);
    }
}
