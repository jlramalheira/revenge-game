/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import framework.*;
import game.DoubleMissile;
import game.Enemie1;
import game.Enemie2;
import game.Enemie3;
import game.Enemie4;
import game.MissileLauncher;
import game.ParticleSystemExplosion;
import game.Shield;
import game.Ship;
import java.util.Random;
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
    private MissileLauncher missileLauncher;
    private int quantidadeEnimie;
    private ParticleSystemExplosion particleSystemExplosion;
    private int level;
    private MissileLauncher missileEnimieLauncher;
    private int contFireEnemie;
    private Random r = new Random();
    private boolean haveShield;
    private boolean haveDoubleMissile;
    private int timeBonus;
    private Shield shield;
    private DoubleMissile doubleMissile;

    public ScreenGame() {
        level = 3;
        loadResources();
        initGame();

    }

    public final void loadResources() {
        //Game objects
        ship = new Ship(Util.loadImage(Global.IMG_SHIP), 50, 50);
        enimiesup = new ObjectsCollection(Global.ENIMIES_MAX_NUMBER);
        enimiesdown = new ObjectsCollection(Global.ENIMIES_MAX_NUMBER);
        quantidadeEnimie = Global.ENIMIES_MAX_NUMBER * 2;
        contFireEnemie = 0;

        shield = new Shield(Util.loadImage(Global.IMG_BONUS_SHIELD), 16, 16);
        shield.setSpeedY(Global.BONUS_SPEED);
        doubleMissile = new DoubleMissile(Util.loadImage(Global.IMG_BONUS_DOUBLE_MISSILE), 24, 24);
        doubleMissile.setSpeedY(Global.BONUS_SPEED);

        haveShield = false;
        haveDoubleMissile = false;
        timeBonus = 0;


        Image imageMissleEnemie = Util.loadImage(Global.IMG_MISSLE_ENEMIE);
        missileEnimieLauncher = new MissileLauncher(imageMissleEnemie,
                14, 4, Global.BULLET_FIRE_FELAY, Global.BULLET_MAX_NUMBER);
        Image imageMissle = Util.loadImage(Global.IMG_MISSILE);
        missileLauncher = new MissileLauncher(imageMissle,
                14, 4, Global.BULLET_FIRE_FELAY, Global.BULLET_MAX_MISSLES);

        if (level
                == 1) {
            bgLined = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
            bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_1), 30, 30, 10);
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
        }

        if (level
                == 2) {
            bgLined = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
            bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_2), 30, 30, 10);
            Image imageEnimie2 = Util.loadImage(Global.IMG_ENEMIE_2);
            for (int i = 0; i < enimiesup.getTotalSize(); i++) {
                Enemie2 en = new Enemie2(imageEnimie2, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(10);
                en.setVelocidade(8);
                enimiesup.addObject(en);
            }

            for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
                Enemie2 en = new Enemie2(imageEnimie2, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(55);
                en.setVelocidade(-7);
                enimiesdown.addObject(en);
            }
        }

        if (level
                == 3) {
            bgLined = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
            bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_3), 30, 30, 10);
            Image imageEnimie3 = Util.loadImage(Global.IMG_ENEMIE_3);
            for (int i = 0; i < enimiesup.getTotalSize(); i++) {
                Enemie3 en = new Enemie3(imageEnimie3, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(10);
                en.setSpeedY(2);
                en.setSpeedX(8);
                enimiesup.addObject(en);
            }

            for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
                Enemie3 en = new Enemie3(imageEnimie3, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(55);
                en.setSpeedY(2);
                en.setSpeedX(-7);
                enimiesdown.addObject(en);
            }
        }

        if (level
                == 4) {
            bgLined = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
            bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_4), 30, 30, 10);
            Image imageEnimie4 = Util.loadImage(Global.IMG_ENEMIE_4);
            for (int i = 0; i < enimiesup.getTotalSize(); i++) {
                Enemie4 en = new Enemie4(imageEnimie4, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(10);
                en.setVelocidade(8);
                enimiesup.addObject(en);
            }

            for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
                Enemie4 en = new Enemie4(imageEnimie4, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(55);
                en.setVelocidade(-7);
                enimiesdown.addObject(en);
            }
        }
    }

    /**
     * Aqui criamos e carregamos todos os recursos usados na tela do jogo.
     */
    public final void initGame() {
        ship.setX(Util.centerX(ship.getWidth()));
        ship.setY(Screen.getHeight() - ship.getHeight() - 20);
        ship.setAnimation(Ship.ANIM_MOVE);
        ship.setTransformation(Ship.TRANS_NONE);

        missileLauncher.makeAllObjectsAvailable();
        missileEnimieLauncher.makeAllObjectsAvailable();

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
                    gameSate = STATE_TITLE;
                }
                break;
        }

    }

    private void updateGameTitle() {
        bgStars.update();
        if (Key.FIRE) {
            gameSate = STATE_PLAY;
            level = 1;
            loadResources();
            initGame();
        }
    }

    private void updateCollisions() {
        //nave com shield
        if (shield.collidesWith(ship, true)) {
            haveShield = true;
            shield.setVisible(false);
            shield.setActive(false);
        }

        //se tem escudo nao pode receber colisao
        if (!haveShield) {
            //imimigos com nave
            if (enimiesup.collidesWithActiveObjects(ship, true)) {
                gameSate = STATE_GAMEOVER;
            }

            //tiro inimigo nave
            if (missileEnimieLauncher.collidesWithActiveObjects(ship, true)) {
                gameSate = STATE_GAMEOVER;
            }
        }

        if (haveShield) {
            if (timeBonus++ >= Global.TIME_BONUS) {
                haveShield = false;
                timeBonus = 0;
            }
        }

        //nave com double missile
        if (doubleMissile.collidesWith(ship, true)) {
            haveDoubleMissile = true;
            doubleMissile.setActive(false);
            doubleMissile.setVisible(false);
        }

        if (haveDoubleMissile) {
            if (timeBonus++ >= Global.TIME_BONUS) {
                haveDoubleMissile = false;
                timeBonus = 0;
            }
        }

        //nave com tiro
        for (int i = 0; i < enimiesup.getTotalSize(); i++) {
            if (enimiesup.getObject(i).isActive()) {
                if (missileLauncher.collidesWithActiveObjects(enimiesup.getObject(i), false)) {
                    particleSystemExplosion.addParticles(enimiesup.getObject(i).getCenterX(), enimiesup.getObject(i).getCenterY());
                    enimiesup.getObject(i).setActive(false);
                    enimiesup.getObject(i).setVisible(false);
                    enimiesup.makeObjectAvailable(enimiesup.getObject(i));
                    quantidadeEnimie--;

                    //caindo bonus
                    if (!haveDoubleMissile && !haveShield) {
                        int teste = r.nextInt(Global.POSSIBILITES_BONUS);
                        System.out.println(teste);
                        if (teste == 1) {
                            if (0 == 0) {
                                doubleMissile.setActive(true);
                                doubleMissile.setVisible(true);
                                doubleMissile.setX(enimiesdown.getObject(i).getCenterX());
                                doubleMissile.setY(enimiesdown.getObject(i).getCenterY());
                            } else {
                                shield.setActive(true);
                                shield.setVisible(true);
                                shield.setX(enimiesdown.getObject(i).getCenterX());
                                shield.setY(enimiesdown.getObject(i).getCenterY());
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
            if (enimiesdown.getObject(i).isActive()) {
                if (missileLauncher.collidesWithActiveObjects(enimiesdown.getObject(i), false)) {
                    particleSystemExplosion.addParticles(enimiesdown.getObject(i).getCenterX(), enimiesdown.getObject(i).getCenterY());
                    enimiesdown.getObject(i).setActive(false);
                    enimiesdown.getObject(i).setVisible(false);
                    enimiesdown.makeObjectAvailable(enimiesdown.getObject(i));
                    quantidadeEnimie--;

                    //caindo bonus
                    if (!haveDoubleMissile && !haveShield) {
                        int teste = r.nextInt(Global.POSSIBILITES_BONUS);
                        System.out.println(teste);
                        if (teste == 1) {
                            if (0 == 0) {
                                doubleMissile.setActive(true);
                                doubleMissile.setVisible(true);
                                doubleMissile.setX(enimiesdown.getObject(i).getCenterX());
                                doubleMissile.setY(enimiesdown.getObject(i).getCenterY());
                            } else {
                                shield.setActive(true);
                                shield.setVisible(true);
                                shield.setX(enimiesdown.getObject(i).getCenterX());
                                shield.setY(enimiesdown.getObject(i).getCenterY());
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateGameObjects() {
        if (Key.FIRE) {
            if (haveDoubleMissile) {
                missileLauncher.doublefire(ship.getCenterX(), ship.getCenterY(),
                        105,75, Global.BULLET_SPEED);                
                
            } else {
                missileLauncher.fire(ship.getCenterX(), ship.getCenterY(),
                        90, Global.BULLET_SPEED);
            }
        }

        //MÉTODO PARA O INIMIGO ATIRAR
        //Procura um inimigo que esteja ativo e dentro da tela, se não achar, encontra outro
        boolean atirado = false;
        do {
            int inimigo = r.nextInt(enimiesup.getAvailableSize());
            if (enimiesup.getObject(inimigo).isActive()) {
                if (enimiesup.getObject(inimigo).getCenterX() < Screen.getWidth()) {
                    missileEnimieLauncher.fire(enimiesup.getObject(inimigo).getCenterX(), enimiesup.getObject(inimigo).getCenterY(),
                            270, Global.BULLET_SPEED);
                    contFireEnemie = 0;
                    atirado = true;
                }
            }
            if (enimiesdown.getObject(inimigo).isActive()) {
                if (enimiesdown.getObject(inimigo).getCenterX() < (Screen.getWidth())) {
                    missileEnimieLauncher.fire(enimiesdown.getObject(inimigo).getCenterX(), enimiesdown.getObject(inimigo).getCenterY(),
                            270, Global.BULLET_SPEED);
                    contFireEnemie = 0;
                    atirado = true;
                }
            }
        } while ((atirado = false) && (contFireEnemie++ >= 60));

        atirado = false;

        bgLined.update();
        bgStars.update();
        particleSystemExplosion.update();
        ship.update();
        missileLauncher.update();
        missileEnimieLauncher.update();
        shield.update();
        doubleMissile.update();

        enimiesup.updateActiveObjects();
        enimiesdown.updateActiveObjects();
    }

    private void updateGameScoreAndLevel() {
        if (quantidadeEnimie <= 0) {
            System.out.println("Estou em" + level);
            level++;
            System.out.println("Fui para" + level);
            loadResources();
            initGame();
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
        missileLauncher.paint(g);
        missileEnimieLauncher.paint(g);
        shield.paint(g);
        doubleMissile.paint(g);

        enimiesup.paintVisibleObjects(g);
        enimiesdown.paintVisibleObjects(g);
        particleSystemExplosion.paint(g);


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
