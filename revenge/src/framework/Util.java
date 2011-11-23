package framework;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Util {

    public static final int TARGET_FPS = 30;
    public static final int COMMAND_LEFT = 0;
    public static final int COMMAND_RIGHT = 1;
    public static final int COMMAND_CENTER = 2;
    public static final int CENTER = -999;
    private static final Random random = new Random();

    /**
     * Calcula a posicao X para centralizar uma imagem.
     * @param img Imagem a centralizar.
     * @return Posicao X centralizada.
     */
    public static int centerX(Image img) {
        return (Screen.getWidth() - img.getWidth()) / 2;
    }

    /**
     * Calcula a posicao Y para centralizar uma imagem.
     * @param img Imagem a centralizar.
     * @return Posicao Y centralizada.
     */
    public static int centerY(Image img) {
        return (Screen.getHeight() - img.getHeight()) / 2;
    }

    /**
     * Calcula a posicao X para centralizar um objeto de largura especificada.
     * @param img Largura do objeto a centralizar.
     * @return Posicao X centralizada.
     */
    public static int centerX(int size) {
        return (Screen.getWidth() - size) / 2;
    }

    /**
     * Calcula a posicao Y para centralizar um objeto de altura especificada.
     * @param img Altura do objeto a centralizar.
     * @return Posicao U centralizada.
     */
    public static int centerY(int size) {
        return (Screen.getHeight() - size) / 2;
    }


    /**
     * Desenha uma parte de uma imagem.
     * @param g Objeto Graphics.
     * @param image Imagem.
     * @param offsetX Deslocamento X da parte da imagem.
     * @param offsetY Deslocamento Y da parte da imagem.
     * @param width Largura da parte da imagem.
     * @param height Altura da parte da imagem.
     * @param targetX Coordenada X onde a parte da imagem deve ser desenhada.
     * @param targetY Coordenada Y onde a parte da imagem deve ser desenhada.
     */
    public static void drawRegion(Graphics g, Image image,
            int offsetX, int offsetY,
            int width, int height,
            int targetX, int targetY) {
        // save previous clip region
        int clipX = g.getClipX();
        int clipY = g.getClipY();
        int clipWidth = g.getClipWidth();
        int clipHeight = g.getClipHeight();

        // special case: no transformation
        g.clipRect(targetX, targetY, width, height);
        g.drawImage(image, targetX - offsetX, targetY - offsetY,
                Graphics.TOP | Graphics.LEFT);

        //restore original clip
        g.setClip(clipX, clipY, clipWidth, clipHeight);
    }

    /**
     * Desenha um fundo cinza claro na base da tela, como fundo para os comandos.
     * @param g Objeto Graphics.
     */
    public static void drawCommandBackground(Graphics g) {
        g.setColor(0xDDDDDD);
        g.fillRect(0, Screen.getHeight()-15, Screen.getWidth(), 15);
    }

    /**
     * Desenha um comando na base da tela (esquerda, centro ou direita).
     * @param text Texto para o comando.
     * @param command Posicao do comando: LEFT, CENTER ou RIGHT.
     * @param g Object Graphics.
     */
    public static void drawCommand(String text, int command, Graphics g) {
        if (command == COMMAND_LEFT) {
            Text.drawText(text, 5,
                    Screen.getHeight() - Text.CHAR_HEIGHT *2, g);
        } else if (command == COMMAND_CENTER) {
            Text.drawText(text,
                    Util.centerX(text.length() * Text.CHAR_WIDTH),
                    Screen.getHeight() - Text.CHAR_HEIGHT * 2, g);
        } else if (command == COMMAND_RIGHT) {
            Text.drawText(text,
                    Screen.getWidth() - text.length() * Text.CHAR_WIDTH - 5,
                    Screen.getHeight() - Text.CHAR_HEIGHT * 2, g);
        }
    }

    public static double randomBetween(double min, double max) {
        if ((min == 0) && (max == 0)) return 0;
        return min + random.nextDouble() * (max - min + 1);
    }

    public static int randomBetween(int min, int max) {
        if ((min == 0) && (max == 0)) return 0;
        return min + random.nextInt(max - min + 1);
    }
    
    public static double cos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }
    
    public static double sin(double angle) {
        // invertemos porque o quadrante negativo eh invertido
        // para as coordenadas da tela
        return -Math.sin(Math.toRadians(angle));
    }
    
    /**
     * Calculates the signed depth of intersection between two rectangles.
     * @param rectA Bounding box from object A.
     * @param rectB Bounding box from object A.
     * @return The amount of overlap between two intersecting rectangles. These
     * depth values can be negative depending on which wides the rectangles
     * intersect. This allows callers to determine the correct direction
     * to push objects in order to resolve collisions.
     * If the rectangles are not intersecting, Point(0,0) is returned.
     */
    public static Point getIntersectionDepth(Rectangle rectA, Rectangle rectB) {
        
        // Calculate half sizes.
        double halfWidthA = rectA.width / 2.0f;
        double halfHeightA = rectA.height / 2.0f;
        double halfWidthB = rectB.width / 2.0f;
        double halfHeightB = rectB.height / 2.0f;

        // Calculate centers.
        Point centerA = new Point(rectA.x + halfWidthA, rectA.y + halfHeightA);
        Point centerB = new Point(rectB.x + halfWidthB, rectB.y + halfHeightB);

        // Calculate current and minimum-non-intersecting distances between centers.
        double distanceX = centerA.x - centerB.x;
        double distanceY = centerA.y - centerB.y;
        double minDistanceX = halfWidthA + halfWidthB;
        double minDistanceY = halfHeightA + halfHeightB;

        // If we are not intersecting at all, return (0, 0).
        if (Math.abs(distanceX) >= minDistanceX || Math.abs(distanceY) >= minDistanceY) {
            return new Point(0f, 0f);
        }

        // Calculate and return intersection depths.
        double depthX = distanceX > 0 ? minDistanceX - distanceX : -minDistanceX - distanceX;
        double depthY = distanceY > 0 ? minDistanceY - distanceY : -minDistanceY - distanceY;
        return new Point(depthX, depthY);
    }
    
    /**
     * Carrega uma imagem para memoria.
     * @param fileName Arquivo da imagem.
     * @return Objeto de imagem.
     */
    public static Image loadImage(String fileName) {
        Image img = null;
        System.out.println("Util.loadImage " + fileName);
        try {
            img = Image.createImage(fileName);
        } catch (IOException ex) {
            System.out.println("Util.loadImage ERRO: Imagem " + fileName + " não pôde ser carregada.");
        }
        return img;
    }

    /**
     * Desenha uma imagem.
     * @param img Imagem a desenhar.
     * @param x Coordenada X a desenhar a imagem.
     * @param y Coordenada Y a desenhar a imagem.
     * @param g Object Graphics.
     */
    public static void drawImage(Image img, double x, double y, Graphics g) {
        if (x == CENTER) {
            x = centerX(img);
        }
        if (y == CENTER) {
            y = centerY(img);
        }

        g.drawImage(img, (int)x, (int)y, 0);
    }
    
    /**
     * Invoda repetidamente o Coletor de Lixo da JVM do dispositivo.
     */
    public static void forceGC() {
        for (int k = 0; k < 5; k++) {
            System.gc();
        }
    }

    public static void clearScreen(Graphics g, int color) {
        g.setColor(color);
        g.fillRect(0, 0, Screen.getWidth(), Screen.getHeight());
    }
}
