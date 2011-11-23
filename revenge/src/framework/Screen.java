package framework;

import javax.microedition.lcdui.Graphics;

public abstract class Screen {
    private static Screen currentScreen;
    private static int width;
    private static int height;

    public Screen() {
        loadResources();
        initGame();
    }
    
    /**
     * A atualizacao a logica da tela deve estar neste metodo.
     */
    public abstract void update();

    /**
     * Os desenhos da tela devem estar neste metodo.
     * @param g Graphics para desenhar.
     */
    public abstract void paint(Graphics g);
    
    /**
     * Os recursos usados na tela e em seus objetos de jogo agregados devem ser carregados neste metodo.
     */
    public abstract void loadResources();
    
    /**
     * A preparacao da tela e de seus objetos de jogo agregados deve ser fetias neste metodo.
     */
    public abstract void initGame();

    /**
     * Retorna a tela atual.
     * @return Objeto Screen da tela atual.
     */
    public static Screen getCurrentScreen() {
        return currentScreen;
    }

    /**
     * Muda a tela atual para uma nova.
     * @param newScreen Nova tela.
     */
    public static void setCurrentScreen(Screen newScreen) {
        Screen.currentScreen = null;
        for (int i = 0; i < 10; i++) {
            System.gc();
            Runtime.getRuntime().gc();
        }
        Screen.currentScreen = newScreen;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Screen.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Screen.height = height;
    }

    public static void clear(Graphics g, int color) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
