import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Main{
    // Funcion to play sound effects
    // will receive the song path inside '/songs' folder
    public static void playClick(String song){
        try {
            // Get the audio file
            File musicFile = new File(song);
            if (!musicFile.exists()) {
                System.err.println("Music file not found at: " + song);
                return;
            }

            // Create an AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            Clip clip = AudioSystem.getClip();

            // Open the audio input stream and load the data into the clip
            clip.open(audioStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float volumeReduction = -10.0f; // control de volume
            gainControl.setValue(volumeReduction);

        
            // Start the playback from the beginning
            clip.start();

        } catch (Exception e) {
            // Handle any errors that might occur during loading or playback
            e.printStackTrace();
        }
    }
    
    public static void playBackgroundMusic(String musicFilePath) {
        try {
            // Get the audio file
            File musicFile = new File(musicFilePath);
            if (!musicFile.exists()) {
                System.err.println("Music file not found at: " + musicFilePath);
                return;
            }

            // Create an AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            Clip clip = AudioSystem.getClip();

            // Open the audio input stream and load the data into the clip
            clip.open(audioStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float volumeReduction = -25.0f; // control the volume
            gainControl.setValue(volumeReduction);

            // Start playing the music
            // clip to repeat forever
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Start the playback from the beginning
            clip.start();

        } catch (Exception e) {
            // Handle any errors that might occur during loading or playback
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Window window = new Window();
        
        // start the background soundtrack
        playBackgroundMusic("songs/playback.wav");
    }
}
