/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;


import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author marcos
 */
public class Sound {
    private static Sound instance;
    private static boolean enabled = true;
    private static Player audioPlayer;
    private static boolean playing = false;

    static {
        instance = new Sound();
    }

    public static void stop() {
        try {
            if (audioPlayer != null)
                audioPlayer.stop();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
        playing = false;
    }

    public static void play(String file) {
        play(file, false);
        playing = true;
    }

    public static void play(String file, boolean loop) {
        if (!enabled)
            return;

        if (audioPlayer != null)
            stop();

        try {
            InputStream is = instance.getClass().getResourceAsStream(file);
            /* Tipos de audio compativeis (Nokia)
            audio/x-wav
            audio/midi
            audio/x-tone-seq
            audio/sp-midi
            audio/x-nokia-rng
            audio/amr
            video/3gpp
             */

            audioPlayer = Manager.createPlayer(is, "audio/sp-midi");
            if (loop)
                audioPlayer.setLoopCount(-1);
            
            audioPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        Sound.enabled = enabled;
        // para a musica
        if (enabled == false)
            stop();
    }

    public static boolean isPlaying() {
        return playing;
    }
}
