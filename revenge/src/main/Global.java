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
    public static final String IMG_ENEMIE_1 = FOLDER + "en1.png";
    public static final String IMG_ENEMIE_2 = FOLDER + "en2.png";
    public static final String IMG_ENEMIE_3 = FOLDER + "en3.png";
    public static final String IMG_ENEMIE_4 = FOLDER + "en4.png";
    public static final String IMG_MISSLE = FOLDER + "missle_14x4.png";
    public static final String EVADER_IMG_EXPLOSION = FOLDER + "explosion_30x30.png";
    
    
    public static final int BULLET_START_NUMBER = 3;
    public static final double BULLET_SPEED = 10;
    public static final int BULLET_MAX_MISSLES = 2;
    public static final int BULLET_FIRE_FELAY = 15;
    public static final int BULLET_MAX_NUMBER = 2;


    public static final String EVADER_FONT = "/res/font_white_12x8.png";
    
    // tamanho da tela em pixels
    public static final int SCREEN_WIDTH = 240;
    public static final int SCREEN_HEIGHT= 320;

    // configuracoes dos objetos do jogo
    public static final int SHIP_SPEED = 5;
    
    // configuracoes dos levels do jogo
    
    //configuracies inimigos
    public static final int ENIMIES_MAX_NUMBER = 10;
    
    // configuracao da aparencia do jogo   
    public static final int SHIP_ANIMATION_DELAY = 1;
    public static final int EXPLOSTION_ANIMATION_DELAY = 1;
}
