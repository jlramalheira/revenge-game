/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Marcos
 */
public class Dialog extends GameObject {
    public static final int COLOR_BACK  = 0xDDDDDD; 
    public static final int COLOR_BORDER= 0x333333; 
    public static final int COLOR_TEXT  = 0xFFFFFF; 
    public static final int COLOR_TITLE = 0xFFFFFF; 

    public static final int DIALOG_NONE   = 0;
    public static final int DIALOG_OK     = 1;
    public static final int DIALOG_YES_NO = 2;
    public static final int OPTION_YES = 0;
    public static final int OPTION_NO  = 1;

    private static final int DIALOG_BORDER= 2;
    private static final int BUTTON_BORDER= 6;
    private static final int TEXT_MARGIN  = DIALOG_BORDER + 5;

    private static final int BLINK_DELAY = 5; // 15 frames
    //private Sprite spriteIcon = null;

    private String title;
    private Vector textWrapped;
    private boolean exit = false;
    private int textX;
    private int textY;
    private int textWidth;
    private int textHeight;
    private int options;
    private Rectangle buttonYes;
    private Rectangle buttonNo;
    private int selected;

    private int blinkCount = 0;
    private boolean blinkText = true;

    public Dialog(String text) {
        this(text, 5, 5, Screen.getWidth()-10, DIALOG_NONE);
    }

    public Dialog(String text, int options) {
        this(text, 5, 5, Screen.getWidth()-10, options);
    }

    public Dialog(String text, int x, int y, int width) {
        this(text, x, y, width, DIALOG_NONE);
    }

    public Dialog(String text, int x, int y, int width, int options) {
        this.setWidth(width);
        
        // posiscionando texto
        textWidth = width - TEXT_MARGIN*2;
        setX(x);
        textX = x + TEXT_MARGIN;
        setY(y);
        textY = y + TEXT_MARGIN;
        setText(text);

        this.options = options;

        // posicionando botoes
        buttonYes= new Rectangle(0, 0, 3*Text.CHAR_WIDTH+BUTTON_BORDER, Text.CHAR_HEIGHT+BUTTON_BORDER);
        buttonNo = new Rectangle(0, 0, 3*Text.CHAR_WIDTH+BUTTON_BORDER, Text.CHAR_HEIGHT+BUTTON_BORDER);
        int xOffset = (Screen.getWidth()-(int)buttonYes.width*2)/3;
        buttonYes.x= xOffset;
        buttonYes.y = textY + textHeight + 10;
        buttonNo.x = xOffset*2 + buttonYes.width;
        buttonNo.y = textY + textHeight + 10;
    }

    public void update() {
        if (Key.FIRE) {
            exit = true;
        }
        if (Key.LEFT) {
            if (--selected < 0) {
                selected = 0;
            }
        } else
        if (Key.RIGHT) {
            if (++selected > 1) {
                selected = 1;
            }
        }
    }

    /**
     * Prepara os controles inernos para (re)mostrar a caixa de dialogo.
     */
    public void reset() {
        exit = false;
        selected = 0;
    }

    public boolean exitPressed() {
        return exit;
    }

    public int getSelectedOption() {
        return selected;
    }

    public int getPressedOption() {
        if (exit) {
            return selected;
        } else {
            return -1;
        }
    }

    public boolean yesPressed() {
        return exit && (selected == 0);
    }

    public boolean noPressed() {
        return exit && (selected == 1);
    }
    
    private void paintBox(Graphics g, int x, int y, int width, int height) {
        g.setColor(COLOR_BORDER);
        // parte externa da borda
        g.fillRoundRect(x, y, width, height, 10, 10);

        g.setColor(COLOR_BACK);
        // parte interna da borda
        g.fillRoundRect(x+DIALOG_BORDER,y+DIALOG_BORDER,
                width-DIALOG_BORDER*2, height-DIALOG_BORDER*2,
                10, 10);
    }

    private void paintBlinkText(Graphics g) {
        // alterna desenho do texo piscante
        if (blinkCount++ > BLINK_DELAY) {
            blinkText = !blinkText;
            blinkCount = 0;
        }
        if (blinkText == true) {
            // desenha o texto para próxima página
            g.setColor(COLOR_TEXT);
            Text.drawText(">>", textX + textWidth - Text.CHAR_WIDTH * 2 - 5, textY + textHeight - Text.CHAR_HEIGHT - 5, g);
        }
    }

    public void paint(Graphics g) {

        if (options == DIALOG_YES_NO) {
            // desenha borda
            paintBox(g, (int)getX(), (int)getY(), getWidth(), getHeight() + (int)buttonYes.height + 10);
            paintButtons( g);
        } else {
            paintBox(g, (int)getX(), (int)getY(), getWidth(), getHeight());
        }

        if (textWrapped != null) {
            Text.drawText(textWrapped, 0, textX, textY, textHeight, g, false);
        }

        paintBlinkText(g);
    }

    private void paintButtons(Graphics g) {
        // desenha borda dos botoes
        g.setColor(0x000000);
        if (selected == 0) {
            paintBox(g, (int)buttonYes.x - BUTTON_BORDER/2,
                    (int)buttonYes.y - BUTTON_BORDER/2,
                    (int)buttonYes.width, (int)buttonYes.height);
        } else {
            paintBox(g, (int)buttonNo.x - BUTTON_BORDER/2,
                    (int)buttonNo.y - BUTTON_BORDER/2,
                    (int)buttonNo.width, (int)buttonNo.height);
        }
        // desenha textos dos botoes
        Text.drawText("SIM", (int)buttonYes.x, (int)buttonYes.y, g);
        Text.drawText("NÃO", (int)buttonNo.x, (int)buttonNo.y, g);
    }

    public void setText(String text) {
        textWrapped = Text.wrapText(text, textWidth);
        // calcula altura do dialog baseado no numero de linhas de texto
        setHeight(textWrapped.size()*Text.CHAR_HEIGHT+TEXT_MARGIN*3);
        textHeight= getHeight() - TEXT_MARGIN*2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
