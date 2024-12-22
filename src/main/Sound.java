package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    private static final Logger LOGGER = Logger.getLogger(Sound.class.getName());

    public Sound() {

        soundURL[0]  = getClass().getResource("/sound/DacAdventure.wav");
        soundURL[1]  = getClass().getResource("/sound/coin.wav");
        soundURL[2]  = getClass().getResource("/sound/powerup.wav");
        soundURL[3]  = getClass().getResource("/sound/unlock.wav");
        soundURL[4]  = getClass().getResource("/sound/fanfare.wav");
    }

    public void setFile(int index) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading the audio sound.", e);
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }
}
