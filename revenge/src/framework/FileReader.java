/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 *
 * @author marcos
 */
public class FileReader {

    private static FileReader instance = new FileReader();
    private static InputStream is; // stream de entrada (arquivo)
    private static int chr; // caractere sendo lido
    private static StringBuffer sb = new StringBuffer();

    public static void open(String fileName) {
        is = instance.getClass().getResourceAsStream(fileName);
    }

    public static String readLine() {
        sb.delete(0, sb.length());// limpa o buffer
        try {
            while ((chr = is.read()) != -1) {
                // precisamos pular o caractere \r que pula uma linha
                if (chr != '\r') {
                    if (chr == '\n') {
                        return sb.toString();
                    } else {
                        sb.append((char) chr);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            is.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads the entire file and outputs to a Vector, separating each line as an
     * vector element.
     * @param fileName File name.
     * @return Content of the file as an Vector of lines.
     */
    public static Vector readLines(String fileName) {
        open(fileName);
        Vector lines = new Vector();

        if (is == null) {
            System.out.println("ERROR: Unable to open file " + fileName);
            return null;
        }
        String line = readLine();
        while(line != null) {
            lines.addElement(line);
            line = readLine();
        }
        close();
        return lines;
    }

    /**
     * Reads the entire file and outputs to a String.
     * @param fileName File name.
     * @return Content of the file.
     */
    public static String readFile(String fileName) {
        open(fileName);
        if (is == null) {
            System.out.println("ERROR: Unable to open file " + fileName);
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        String line = readLine();
        while(line != null) {
            buffer.append(line);
            buffer.append('\n');
            line = readLine();
        }
        buffer.deleteCharAt(buffer.length()-1);
        close();
        return buffer.toString();
    }
}
