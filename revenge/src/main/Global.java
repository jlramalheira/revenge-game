/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 *
 * @author Marcos
 */
public class Global {

    // exibe informacoes na tela
    public static final boolean SHOW_INFO = false;

    // caminhos de imagens, sprites e sons
    public static final String FOLDER = "/res/game/";
    
    // jogo revenge
    public static final String IMG_BG_LINED = FOLDER + "back1.jpg";
    public static final String IMG_BG_STARS = FOLDER + "back2.png";
    public static final String IMG_SHIP = FOLDER + "ship.png";
    public static final String IMG_MINI_SHIP = FOLDER + "mini_ship.png";
    public static final String IMG_BOSS = FOLDER + "boss.png";
    public static final String IMG_ENEMIE_1 = FOLDER + "en1.png";
    public static final String IMG_ENEMIE_2 = FOLDER + "en2.png";
    public static final String IMG_ENEMIE_3 = FOLDER + "en3.png";
    public static final String IMG_ENEMIE_4 = FOLDER + "en4.png";
    public static final String IMG_MISSILE = FOLDER + "missle.png";
    public static final String IMG_MISSLE_ENEMIE = FOLDER + "missle_en.png";
    public static final String EVADER_IMG_EXPLOSION_1 = FOLDER + "explosion1.png";
    public static final String EVADER_IMG_EXPLOSION_2 = FOLDER + "explosion2.png";
    public static final String EVADER_IMG_EXPLOSION_3 = FOLDER + "explosion3.png";
    public static final String EVADER_IMG_EXPLOSION_4 = FOLDER + "explosion4.png";
    public static final String EVADER_IMG_EXPLOSION_BOSS = FOLDER + "explosion_boss.png";
    
    
    public static final int BULLET_START_NUMBER = 3;
    public static final int BULLET_SPEED = 10;
    public static final int BULLET_ENEMIE_SPEED = 5;
    public static final int BULLET_MAX_MISSLES = 3;
    public static final int BULLET_FIRE_FELAY = 15;
    public static final int BULLET_MAX_NUMBER = 3;


    public static final String EVADER_FONT = "/res/font_white_12x8.png";
    
    // tamanho da tela em pixels
    public static final int SCREEN_WIDTH = 240;
    public static final int SCREEN_HEIGHT= 320;
    
    //sons do jogo
    public static final String MUSIC_INTRO = FOLDER + "Intro.mid";
    public static final String MUSIC_GAME = FOLDER + "Game.mid";
    public static final String MUSIC_END = FOLDER + "Ending.mid";
    public static final String MUSIC_BOSS = FOLDER + "Boss.mid";

    // configuracoes dos objetos do jogo
    public static final int SHIP_SPEED = 5;    
    public static final int BOSS_SPEEDX = 4;    
    public static final int BOSS_SPEEDY = 9;
    
    //boss
    public static final int MISSLE_BOSS_DIE = 50;
    
    //configuracao dos bonus
    public static final int POSSIBILITES_BONUS = 10;
    public static final int BONUS_SPEED = 5;
    public static final int TIME_BONUS = 60;
    public static final int NUMBER_DOUBLE_MISSILE = 3;
    public static final String IMG_BONUS_SHIELD = FOLDER + "shield.png";
    public static final String IMG_BONUS_DOUBLE_MISSILE = FOLDER + "double_missile.png";
   
    
    // configuracoes dos levels do jogo
    
    //configuracies inimigos
    public static final int ENIMIES_MAX_NUMBER = 10;
    
    // configuracao da aparencia do jogo   
    public static final int SHIP_ANIMATION_DELAY = 1;
    public static final int EXPLOSTION_ANIMATION_DELAY = 1;
}
