import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    public static void playSound(String filename, boolean loop) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the clip continuously
            } else {
                clip.start(); // Play the clip once
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
