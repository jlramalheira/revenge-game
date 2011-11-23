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
    
    // jogo ball evader
    public static final String EVADER_IMG_BACK = FOLDER + "back3.png";    
    public static final String EVADER_IMG_STARS = FOLDER + "back4.jpg";    
    public static final String EVADER_IMG_FOG = FOLDER + "clouds.png";    
    public static final String EVADER_IMG_ASTEROID = FOLDER + "asteroid.png";
    public static final String EVADER_IMG_ITEM = FOLDER + "ball.png";
    public static final String EVADER_IMG_SHIP = FOLDER + "ship_30x30.png";
    public static final String EVADER_IMG_MISSLE = FOLDER + "missle_14x4.png";
    public static final String EVADER_IMG_EXPLOSION = FOLDER + "explosion_30x30.png";
    public static final String EVADER_IMG_BLUE = FOLDER + "blue_15x15.png";
    public static final String EVADER_IMG_RED = FOLDER + "red_15x15.png";
    public static final String EVADER_IMG_HINT_10 = FOLDER + "hint10_30x18.png";
    public static final String EVADER_IMG_HINT_20 = FOLDER + "hint20_25x15.png";
    public static final String EVADER_IMG_HINT_LEVELUP = FOLDER + "hint_levelup_84x30.png";
    public static final String EVADER_IMG_HINT_MISSLE = FOLDER + "hint_missle_40x13.png";
    public static final String EVADER_IMG_TITLE = FOLDER + "title.png";
    public static final String EVADER_IMG_GAMEOVER = FOLDER + "game_over.png";
    
    public static final String MARIO_IMG_SHIP2 = FOLDER + "ship2_30x30.png";
    public static final String MARIO_IMG_FLY = FOLDER + "fly_20x27.png";
    public static final String MARIO_IMG_FAT = FOLDER + "fat_32x34.png";
    public static final String MARIO_IMG_TALL = FOLDER + "tall_36x61.png";
    public static final String MARIO_IMG_MARIO = FOLDER + "mario_20x31.png";
    public static final String MARIO_MAP_FILE_TMX = FOLDER + "map1.tmx";
    public static final String MARIO_MAP_TILES_IMG = FOLDER + "tiles2_16x16.png";
    public static final String SELECT_IMG_GAMEPAD = FOLDER + "controller.png";
    
    public static final String SHOOTER_IMG_BG = FOLDER + "bg_city2.png";
    public static final String SHOOTER_IMG_SHIP = FOLDER + "ship_side_30x33.png";
    public static final String SHOOTER_IMG_ENEMY = FOLDER + "asteroid2.png";
    
    public static final String EVADER_FONT_WHITE = "/res/font_white_12x8.png";
    public static final String EVADER_FONT_BLACK = "/res/font_12x8.png";
    
    // tamanho da tela em pixels
    public static final int SCREEN_WIDTH = 240;
    public static final int SCREEN_HEIGHT= 320;

    // configuracoes dos objetos do jogo
    public static final double SHIP_START_SPEED = 3;
    public static final double SHIP_MAX_SPEED = 8;
    public static final int SHIP_ANIMATION_DELAY = 1;
    
    public static final int BULLET_START_NUMBER = 3;
    public static final double BULLET_SPEED = 8;
    public static final int BULLET_MAX_MISSLES = 5;
    public static final int BULLET_FIRE_FELAY = 10;
    public static final int BULLET_MAX_NUMBER = 10;
    
    public static final int ASTEROIDS_START_NUMBER = 1;
    public static final int ASTEROID_MAX_NUMBER = 25;
    public static final int ASTEROID_MAX_ABS_SPEED = 2;
    public static final int ASTEROID_RESPAWN_HEIGHT = 50;
    
    // configuracoes dos levels do jogo
    public static final double SCORE_INC_PER_FRAME = 0.05;
    public static final double SCORE_PER_ITEM = 20;
    public static final int ITEMS_TO_GET_MISSLE = 3;
    public static final int ITEM_DELAY_TO_RESPAWN = 200;
    public static final int DELAY_TO_NEXT_LEVEL = 350;
    
    // configuracao da aparencia do jogo
    public static final int FOG_SPEED = 2;
    public static final int STARS_SPEED = 1;
    public static final int EXPLOSTION_ANIMATION_DELAY = 2;
}
