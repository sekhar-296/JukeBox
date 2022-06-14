package Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Dao.songData;

import static Main.Main.access;

public class SimpleAudioPlayer {
    Scanner sc = new Scanner(System.in);
    // to store current position
    Long currentFrame;
    Clip clip;


    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    // constructor to initialize streams and clip
    public SimpleAudioPlayer(String filePath) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException, SQLException, ClassNotFoundException {
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio(String filePath) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio being played");
            return;
        }
        clip.close();
        resetAudioStream(filePath);
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart(String filePath) throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream(filePath);
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

//    //foraward next song
//    public void nextSong(List<songData> list, int Tid) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        Iterator<songData>t= list.iterator();
//
//        while (t.hasNext()){
//            audioInputStream = AudioSystem.getAudioInputStream(new File(t.next().getUrl()).getAbsoluteFile());
//
//            // create clip reference
//            clip = AudioSystem.getClip();
//
//            // open audioInputStream to the clip
//            clip.open(audioInputStream);
//
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        }
//    }

    // Method to jump over a specific part
    public void forward()
    {
       clip.setMicrosecondPosition(clip.getMicrosecondPosition()+10000000);

    }
    public void reverse(){
        clip.setMicrosecondPosition(clip.getMicrosecondPosition()-10000000);
    }

    // Method to reset audio stream
    public void resetAudioStream(String filePath) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



    public void playerOptionsDisplay(){
        System.out.println("""
                1️⃣----Pause---->⏸️
                2️⃣----Resume--->⏯️
                3️⃣----Restart-->⏯️
                4️⃣----Stop----->⏹️
                5️⃣----Forward-->⏩
                6️⃣----Reverse-->◀️
                7️⃣----Shuffle-->🔀️️
                8️⃣----Next----->◀️
                """);
    }

    int choose(){
        System.out.println("Select: ");
        return sc.nextInt();
    }

    public List<songData> tempList() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Enter PlayList Name to Play the songs");
        return access.viewUserPlaylist(sc.next());
    }
}