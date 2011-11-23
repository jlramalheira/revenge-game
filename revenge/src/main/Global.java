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
    public static final String IMG_ENEMIE_1 = FOLDER + "en.png";


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
    public static final int EXPLOSTION_ANIMATION_DELAY = 2;
}
