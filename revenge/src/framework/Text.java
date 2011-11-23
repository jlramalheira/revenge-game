/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Marcos
 */
public class Text {

    public static int CHAR_HEIGHT = 12;
    public static int CHAR_WIDTH = 8;
    public static int LINE_SPACE = 0;
    
    public static final String DEFAULT_FONT = "/res/font_12x8.png";
    public static final int CENTER = -999;    
    
    public static final String ALPHABET = "?¿!¡:.,-ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789’/#ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÜÛÇÑß$";
    public static Image imageFont = null;
    
    // usado no drawTextPages()
    private static int currentPage;
    private static int pages;

    static {
        Text.setFont(DEFAULT_FONT, 12, 8);
    }
    
    /**
     * Define novo arquivo de fonte para os textos.
     * @param fontFile Arquivo de imagem (sprite) de fonte.
     * @param charWidth Largura de pixels do caractere.
     * @param charHeight Altura em pixels do caractere.
     */
    public static void setFont(String fontFile, int charWidth, int charHeight) {
        Text.imageFont = Util.loadImage(fontFile);
        Text.CHAR_HEIGHT = charWidth;
        Text.CHAR_WIDTH = charHeight;
    }    
    
    public static int getTextWidth(String text) {
        return text.length()*CHAR_WIDTH;
    }
    
    /**
     * Escreve texto sem quebra de linha.
     * @param str Texto.
     * @param x Posicao X.
     * @param y Posicao Y.
     * @param g Objeto Graphics.
     */
    public static void drawText(String str, int x, int y, Graphics g) {
        if (x == CENTER) {
            x = Util.centerX(str.length() * CHAR_WIDTH);
        }
        if (y == CENTER) {
            y = Util.centerY(CHAR_HEIGHT);
        }

        drawText(str, 0, str.length(), x, y, CHAR_WIDTH, imageFont, ALPHABET,
                Graphics.TOP | Graphics.LEFT, g);
    }
    
    /**
     * Devolve o numero de paginas de um texto.
     * @param textLines Texto em linhas.
     * @param height Altura da area onde o texto sera desenhado.
     * @return Numero de paginas.
     */
    public static int getNumberOfPages(Vector textLines, int height) {
        return textLines.size() / (height / CHAR_HEIGHT);
    }

    /**
     * Desenha texto em linhas em varias paginas.
     * @param textLines Texto em linhas.
     * @param x Coordenada X do desenho.
     * @param y Coordenada Y do desenho.
     * @param height Altura da area de desenho.
     * @param g Objeto Graphics.
     * @param navigable Paginas sao navegaveis ESQ-DIR
     * @return True ao chegar no final das paginas quando navegavel.
     */
    public static boolean drawTextPages(Vector textLines, int x, int y,
            int height, Graphics g, boolean navigable) {

        if (navigable) {
            // navega no texto <-- e -->
            if (Key.RIGHT) {
                currentPage++;
                if (currentPage > pages) {
                    currentPage = pages;
                }
            } else if (Key.LEFT) {
                currentPage--;
                if (currentPage < 0) {
                    currentPage = 0;
                }
            }
        } else {
            // tecla FIRE avanca paginas e sai da caixa de texto
            if (Key.FIRE) {
                currentPage++;
                if (currentPage > pages) {
                    return true;
                }
            }
        }

        // desenha a pagina corrente
        drawText(textLines, currentPage, x, y, height, g, navigable);

        return false;
    }

    /**
     * Serapa um texto em linhas.
     * @param str Texto a separar.
     * @param width Largura a considerar para separar o texto.
     * @return Texto separado em linhas.
     */
    public static Vector wrapText(String str, int width) {
        Vector text = new Vector();
        //int lineNumber = 0;
        int lineStart = 0;
        int lineSize = 0;
        int lineLength = (width / CHAR_WIDTH); // qtde de caracteres por linha
        String line = "";

        while (lineStart < str.length()) {
            // linhas normais
            if ((lineStart + lineLength - 1) < str.length()) {
                line = str.substring(lineStart, lineStart + lineLength - 1);
                lineSize = line.lastIndexOf(' ');
                if (lineSize < 0) // indica que nao ha espaco no texto
                    lineSize = line.length();
            } else {
             // ultima linha
                line = str.substring(lineStart, str.length());
                lineSize = line.length();
            }
            // verificando se ha marcador de nova linha
            int newLine = line.indexOf('\n');
            if (newLine > 0) {
                lineSize = newLine;
            }

            line = line.substring(0, lineSize);
            // acrescenta cada linha ao array
            text.addElement(line);
            lineStart += lineSize + 1; // acrescentar 1 caractere do espaco
        }
        // prepara controles para o drawTextPages()
        currentPage = 0;
        pages = Text.getNumberOfPages(text, Screen.getHeight() - 10);

        return text;
    }
    
    static boolean drawText(Vector textLines, int page, int x, int y,
            int height, Graphics g, boolean showPageNumber) {

        // desenha borda ao redor do texto (debug)
//        g.setColor(0x000000);
//        g.drawRect(x, y, width, height);

        int linesPerPage = height / CHAR_HEIGHT;
        int pages = (textLines.size() / linesPerPage);

        for (int i = 0; i < linesPerPage; i++) {
            int index = i + page * linesPerPage;
            if (index >= textLines.size()) {
                break;
            }
            String line = (String) textLines.elementAt(index);
            drawText(line, x, y + i * CHAR_HEIGHT + LINE_SPACE, g);
        }
        // escreve mostrado
//        if (showPageNumber) {
//            drawText((page + 1) + "/" + (pages + 1),
//                    x + width - 5 * CHAR_WIDTH, y + LINE_SPACE, g);
//        }

        return true;
    }
    
    private static void drawText(String str, int start, int end, int x, int y,
            int charWidth, Image lettersImage, String alphabet, int anchor,
            Graphics g) {

        // horizontal position of the clip area for each number
        int xClip = x;

        // int value of a digit
        int intVal = 0;

        // ATTENTION: Include one pixel between chars.
        // Use specified charWidth only to indicate the space between
        // chars will be painted. Each char on letters image have
        // 1 pixel lesser than specified charWidth, in order
        // to simulate a border between chars.
        charWidth = charWidth - 1;

        // painting the number
        for (int i = start; i < end; i++) {

            int j = i;
            int positionInc = charWidth + 1;

            if (anchor == (Graphics.RIGHT | Graphics.TOP)) {
                j = ((str.length() - 1) - i);
                positionInc = -positionInc;
            }

            // getting the int value of a digit
            intVal = alphabet.indexOf(str.charAt(j));

            if (intVal != -1) {
                // paint letter
                Util.drawRegion(g, lettersImage, (intVal * charWidth), 0,
                        charWidth, lettersImage.getHeight(), xClip, y);
            }

            // calculating the horizontal position of cliping area
            xClip += positionInc;
        }
    }


}
