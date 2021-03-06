/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import framework.*;
import game.Boss;
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
    public static final int STATE_WINNER = 3;
    private int gameSate;
    private Background bgLined;
    private Background bgStars;
    private Background bgGameOver;
    private Background bgWinner;
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
    private int qtdeDoubleMissile;
    private Boss boss;
    private int contToBossDie;
    private int lives;
    private int score;
    private Image mini_ship;

    public ScreenGame() {
        level = 0;
        lives = 3;
        mini_ship = Util.loadImage(Global.IMG_MINI_SHIP);
        Sound.play(Global.MUSIC_GAME, true);
        loadResources();
        initGame();

    }

    public final void loadResources() {
        //Game objects
        ship = new Ship(Util.loadImage(Global.IMG_SHIP), 45, 45);
        enimiesup = new ObjectsCollection(Global.ENIMIES_MAX_NUMBER);
        enimiesdown = new ObjectsCollection(Global.ENIMIES_MAX_NUMBER);
        quantidadeEnimie = Global.ENIMIES_MAX_NUMBER * 2;
        contFireEnemie = 0;
        bgLined = new Background(Util.loadImage(Global.IMG_BG_LINED), Background.SCROLL_NONE);
        bgStars = new Background(Util.loadImage(Global.IMG_BG_STARS), Background.SCROLL_DOWN);
        bgGameOver = new Background(Util.loadImage(Global.IMG_BG_GAMEOVER), Background.SCROLL_NONE);
        bgWinner = new Background(Util.loadImage(Global.IMG_BG_WINNER), Background.SCROLL_NONE);

        shield = new Shield(Util.loadImage(Global.IMG_BONUS_SHIELD), 25, 25);
        shield.setSpeedY(Global.BONUS_SPEED);
        doubleMissile = new DoubleMissile(Util.loadImage(Global.IMG_BONUS_DOUBLE_MISSILE), 25, 25);
        doubleMissile.setSpeedY(Global.BONUS_SPEED);

        haveShield = false;
        haveDoubleMissile = false;
        timeBonus = 0;
        qtdeDoubleMissile = 0;
        contToBossDie = 0;


        Image imageMissleEnemie = Util.loadImage(Global.IMG_MISSLE_ENEMIE);
        missileEnimieLauncher = new MissileLauncher(imageMissleEnemie,
                14, 4, 10, Global.BULLET_MAX_NUMBER);
        Image imageMissle = Util.loadImage(Global.IMG_MISSILE);
        missileLauncher = new MissileLauncher(imageMissle,
                5, 5, Global.BULLET_FIRE_FELAY, Global.BULLET_MAX_MISSLES);

        if (level
                == 1) {
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
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_4), 30, 30, 10);
            Image imageEnimie3 = Util.loadImage(Global.IMG_ENEMIE_4);
            for (int i = 0; i < enimiesup.getTotalSize(); i++) {
                Enemie3 en = new Enemie3(imageEnimie3, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(10);
                en.setVelocidade(-7);

                enimiesup.addObject(en);
            }

            for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
                Enemie3 en = new Enemie3(imageEnimie3, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(55);
                en.setVelocidade(8);

                enimiesdown.addObject(en);
            }
        }

        if (level
                == 4) {
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_3), 30, 30, 10);
            Image imageEnimie4 = Util.loadImage(Global.IMG_ENEMIE_3);
            for (int i = 0; i < enimiesup.getTotalSize(); i++) {
                Enemie4 en = new Enemie4(imageEnimie4, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(10);
                en.setSpeedY(2);
                en.setSpeedX(8);
                enimiesup.addObject(en);
            }

            for (int i = 0; i < enimiesdown.getTotalSize(); i++) {
                Enemie4 en = new Enemie4(imageEnimie4, 52, 30);
                en.setX((52 * i) + (15 * i));
                en.setY(55);
                en.setSpeedY(2);
                en.setSpeedX(-7);
                enimiesdown.addObject(en);
            }
        }

        if (level == 5) {
            //BOSS
            boss = new Boss(Util.loadImage(Global.IMG_BOSS), 75, 75);
            particleSystemExplosion = new ParticleSystemExplosion(
                    Util.loadImage(Global.EVADER_IMG_EXPLOSION_BOSS), 30, 30, 10);
            boss.setSpeedX(Global.BOSS_SPEEDX);
            boss.setSpeedY(Global.BOSS_SPEEDY);

        }
        
        System.gc();
    }

    /**
     * Aqui criamos e carregamos todos os recursos usados na tela do jogo.
     */
    public final void initGame() {
        ship.setX(Util.centerX(ship.getWidth()));
        ship.setY(Screen.getHeight() - ship.getHeight() - 20);
        ship.setAnimation(Ship.ANIM_NONE);
        ship.setTransformation(Ship.TRANS_NONE);
        Sound.setEnabled(true);

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
                    Screen.setCurrentScreen(new SelectGameScreen());
                }
                break;

            case STATE_WINNER:
                if (Key.FIRE) {
                    Screen.setCurrentScreen(new SelectGameScreen());
                }
                break;
        }

    }

    private void updateGameTitle() {
        bgStars.update();
        if (Key.FIRE) {
            gameSate = STATE_PLAY;
            lives = 3;
            score = 0;
            level = 1;
            loadResources();
            initGame();

        }
    }

    private void updateCollisions() {
        //nave com shield
        if (shield.collidesWith(ship, true)) {
            haveShield = true;
            ship.setAnimation(Ship.ANIM_SHIELD);
            shield.setVisible(false);
            shield.setActive(false);
        }


        //se tem escudo nao pode receber colisao
        if (!haveShield) {
            //imimigos com nave
            if (enimiesup.collidesWithActiveObjects(ship, true)) {
                if (lives == 0) {
                    gameSate = STATE_GAMEOVER;
                } else {
                    lives--;
                    score = 0;
                    loadResources();
                    initGame();
                }
            }

            //tiro inimigo nave
            if (missileEnimieLauncher.collidesWithActiveObjects(ship, true)) {
                if (lives == 0) {
                    gameSate = STATE_GAMEOVER;
                } else {
                    lives--;
                    score = 0;
                    loadResources();
                    initGame();
                }
            }
        }

        if (haveShield) {
            if (timeBonus++ >= Global.TIME_BONUS) {
                haveShield = false;
                timeBonus = 0;
                ship.setAnimation(Ship.ANIM_NONE);
            }
            //some com o tiro caso esteja com escudo
            if (missileEnimieLauncher.collidesWithActiveObjects(ship, true)) {
                missileEnimieLauncher.makeAllObjectsAvailable();
                boss = null;
            }
        }

        //nave com double missile
        if (doubleMissile.collidesWith(ship, true)) {
            haveDoubleMissile = true;
            doubleMissile.setActive(false);
            doubleMissile.setVisible(false);
        }

        if (haveDoubleMissile) {
            if (qtdeDoubleMissile >= Global.NUMBER_DOUBLE_MISSILE) {
                haveDoubleMissile = false;
                qtdeDoubleMissile = 0;
            }
        }
        if (level != 5) {
            //nave com tiro
            for (int i = 0; i < enimiesup.getTotalSize(); i++) {
                if (enimiesup.getObject(i).isActive()) {
                    if (missileLauncher.collidesWithActiveObjects(enimiesup.getObject(i), false)) {
                        particleSystemExplosion.addParticles(enimiesup.getObject(i).getCenterX(), enimiesup.getObject(i).getCenterY());
                        enimiesup.getObject(i).setActive(false);
                        enimiesup.getObject(i).setVisible(false);
                        enimiesup.makeObjectAvailable(enimiesup.getObject(i));
                        quantidadeEnimie--;
                        score += 20;

                        //caindo bonus
                        if (!haveDoubleMissile && !haveShield) {
                            int teste = r.nextInt(Global.POSSIBILITES_BONUS);
                            if (teste == 1) {
                                if (!doubleMissile.isActive()) {
                                    doubleMissile.setActive(true);
                                    doubleMissile.setVisible(true);
                                    doubleMissile.setX(enimiesdown.getObject(i).getCenterX());
                                    doubleMissile.setY(enimiesdown.getObject(i).getCenterY());
                                }
                            } else {
                                if (!shield.isActive()) {
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
                        score += 20;

                        //caindo bonus
                        if (!haveDoubleMissile && !haveShield) {
                            int teste = r.nextInt(Global.POSSIBILITES_BONUS);
                            if (teste == 1) {
                                if (!doubleMissile.isActive()) {
                                    doubleMissile.setActive(true);
                                    doubleMissile.setVisible(true);
                                    doubleMissile.setX(enimiesdown.getObject(i).getCenterX());
                                    doubleMissile.setY(enimiesdown.getObject(i).getCenterY());
                                }
                            } else {
                                if (!shield.isActive()) {
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

        //nave com o boos
        if (boss != null) {
            if (boss.collidesWith(ship, true)) {
                if (lives == 0) {
                    boss = null;
                    gameSate = STATE_GAMEOVER;
                } else {
                    lives--;
                    score = 0;
                    loadResources();
                    initGame();
                }
            }

            //tiro com o boss
            if (missileLauncher.collidesWithActiveObjects(boss, true)) {
                particleSystemExplosion.addParticles(boss.getCenterX(), boss.getCenterY());
                score += 5;
                if (contToBossDie++ == Global.MISSLE_BOSS_DIE) {
                    gameSate = STATE_WINNER;
                }
            }
        }
    }

    private void updateGameObjects() {
        //TIRO DO PLAYER
        if (Key.FIRE) {
            if (haveDoubleMissile) {
                missileLauncher.doublefire(ship.getCenterX(), ship.getCenterY(), ship.getCenterX(), ship.getCenterY(),
                        105, 75, Global.BULLET_SPEED);
                qtdeDoubleMissile++;
            } else {
                missileLauncher.fire(ship.getCenterX(), ship.getCenterY(),
                        90, Global.BULLET_SPEED);
            }
        }

        //MÉTODO PARA O INIMIGO ATIRAR

        //Procura um inimigo que esteja ativo e dentro da tela, se não achar, encontra outro
        boolean atirado = false;
        if (level != 5) {

            do {
                int inimigo = r.nextInt(enimiesup.getAvailableSize());
                if (enimiesup.getObject(inimigo).isActive()) {
                    if (enimiesup.getObject(inimigo).getCenterX() < Screen.getWidth()) {
                        missileEnimieLauncher.fire(enimiesup.getObject(inimigo).getCenterX(), enimiesup.getObject(inimigo).getCenterY(),
                                270, Global.BULLET_ENEMIE_SPEED);
                        contFireEnemie = 0;
                        atirado = true;
                    }
                }
                if (enimiesdown.getObject(inimigo).isActive()) {
                    if (enimiesdown.getObject(inimigo).getCenterX() < (Screen.getWidth())) {
                        missileEnimieLauncher.fire(enimiesdown.getObject(inimigo).getCenterX(), enimiesdown.getObject(inimigo).getCenterY(),
                                270, Global.BULLET_ENEMIE_SPEED);
                        contFireEnemie = 0;
                        atirado = true;
                    }
                }
                //Faz o processo enquanto não encontrar e fazer dois segundos
            } while ((!atirado) && (contFireEnemie++ >= (300) / level));
            atirado = false;
        } else { //tiro do boss
            if (boss.getY() < 5) {
                if (contFireEnemie++ >= 30) {
                    missileEnimieLauncher.doublefire(boss.getCenterX() + 18, boss.getCenterY() + 29, boss.getCenterX() - 18, boss.getCenterY() + 29,
                            270, 270, Global.BULLET_ENEMIE_SPEED);
                    contFireEnemie = 0;
                }
            }
        }
        //UPDATE DOS OBJETCTS
        bgLined.update();
        bgStars.update();
        bgGameOver.update();
        bgWinner.update();
        particleSystemExplosion.update();
        ship.update();
        missileLauncher.update();
        missileEnimieLauncher.update();
        shield.update();
        if (boss != null) {
            boss.update();
        }

        doubleMissile.update();
        enimiesup.updateActiveObjects();
        enimiesdown.updateActiveObjects();
    }

    private void updateGameScoreAndLevel() {
        if (quantidadeEnimie <= 0) {
            level++;
            score += 100;
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
            case STATE_WINNER:
                paintWinner(g);
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
        if (boss != null) {
            boss.paint(g);
        }

        enimiesup.paintVisibleObjects(g);
        enimiesdown.paintVisibleObjects(g);
        particleSystemExplosion.paint(g);
    }

    private void paintHud(Graphics g) {
        Text.drawText("SCORE: " + score, 10, 0, g);
        for (int i = 1; i <= lives; i++) {
            Util.drawImage(mini_ship, 140 + 20 * i, 0, g);
        }
    }

    private void paintGameOver(Graphics g) {
        bgGameOver.paint(g);
        paintHud(g);
        if (Key.ACTION_LEFT) {
            Screen.setCurrentScreen(new SelectGameScreen());
        }
        if (Key.ACTION_RIGHT) {
            Screen.setCurrentScreen(new SelectGameScreen());
        }
    }

    private void paintTitle(Graphics g) {
        bgLined.paint(g);
        bgStars.paint(g);
        Text.drawText("APERTE -FIRE- PARA INICIAR", Text.CENTER, 180, g);
    }

    private void paintWinner(Graphics g) {
        bgWinner.paint(g);
        if (Key.ACTION_LEFT) {
            Screen.setCurrentScreen(new SelectGameScreen());
        }
        if (Key.ACTION_RIGHT) {
            Screen.setCurrentScreen(new SelectGameScreen());
        }
    }
}
