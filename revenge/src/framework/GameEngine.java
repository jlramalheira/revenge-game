package framework;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * Game Engine
 */
public class GameEngine extends GameCanvas implements Runnable {
    private static final long TARGET_FRAME_TIME = 1000/Util.TARGET_FPS;
    private Graphics graphics; // graphics da BufferedImage para desenhar nela
    private int fps;
    private long frameStart;
    private long frameTime;
    private Thread thread;
    private static boolean showInfo;

    public GameEngine(int width, int height, boolean fullScreen) {
        super(true);

        Screen.setWidth(width);
        Screen.setHeight(height);

        setFullScreenMode(fullScreen);

        showInfo = false;
    }

    public static void setShowInfo(boolean showInfo) {
        GameEngine.showInfo = showInfo;
    }

    public static boolean mustShowInfo() {
        return showInfo;
    }

    public void start(Screen screen) {
        Screen.setCurrentScreen(screen);
        graphics = getGraphics();
        graphics.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        thread = new Thread(this);
        thread.start();
    }

    protected void keyPressed(int keyCode){
        Key.canvasKey = keyCode;
        System.out.println(keyCode);
    }

    public void run() {
        frameStart = System.currentTimeMillis();
        frameTime = 0;

        // LACO PRINCIPAL DO JOGO (GAME LOOP)
        while (true) {
            // PASSO 1: atualiza logica do jogo
            update();

            // PASSO 2: desenha todos os objetos do jogo
            paint();

            // PASSO 3: sincronizacao para o fps desejado
            syncronizeTime();
        }
    }

    private void update() {
        Key.gameKey = getKeyStates();
        Key.updateKeys();
        Screen.getCurrentScreen().update();
        Key.canvasKey = 0;
    }
    
    /*
     * PROCESSO DE DESENHO 
     * Passo 1: desenhamos os objetos do jogo na BufferedImage atraves de seu Graphics. 
     * Passo 2: desenhamos a BufferedImage no painel contido no JFrame.
     */
    private void paint() {
        Screen.getCurrentScreen().paint(graphics);

        if (showInfo) {
            graphics.setColor(0x00FF00);
            graphics.drawString("FPS  " + fps, 5, 15, 0);
            graphics.drawString("FREE  " + Runtime.getRuntime().freeMemory(), 5, 30, 0);
            graphics.drawString("TOTAL " + Runtime.getRuntime().totalMemory(), 5, 45, 0);
        }

        flushGraphics();
    }
    
    private void syncronizeTime() {
        frameTime = System.currentTimeMillis() - frameStart;
        
        try {
            if (frameTime < TARGET_FRAME_TIME) {
                Thread.sleep(TARGET_FRAME_TIME - frameTime);
            } else {
                Thread.sleep(1);
            }
        } catch (InterruptedException ex) {}
        
        fps = (int)(1000/(System.currentTimeMillis()-frameStart));
        
        frameStart = System.currentTimeMillis();        
    }
}