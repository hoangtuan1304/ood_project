package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TestSound {
    public static void playSound() {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            TestSound.class.getResourceAsStream("/res/soundMove.wav"));
                    clip.open(inputStream);
                    clip.start();
                    System.out.println("nge");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
    public static void main(String[] args) {

    }
}
