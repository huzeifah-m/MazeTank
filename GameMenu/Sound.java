
package GameMenu;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/**
 * 
 * @author Sakinah 
 * import mp3 file in resources folder and use javafx media player to play sounds.
 *
 */
public class Sound {

    //menu background sound
    static String music = "resources/sound/music.mp3";
    static Media bsound = new Media(new File(music).toURI().toString());
    static MediaPlayer mp = new MediaPlayer(bsound);

    //tank explode sound
    static String msc = "resources/sound/explodetank.mp3";
    static Media shoot = new Media(new File(msc).toURI().toString());
    static MediaPlayer explode = new MediaPlayer(shoot);

    //menu click sound
    static String musicFile = "resources/sound/click.mp3";
    static Media sound = new Media(new File(musicFile).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(sound);

    //tank shooting sound 
    static String mfile = "resources/sound/pops.mp3";
    static Media fir = new Media(new File(mfile).toURI().toString());
    static MediaPlayer fire = new MediaPlayer(fir);

    /**
     * get the volume of game menu background sound.
     * @return volume of mp 
     */
    public static double vol()
    {
        return mp.getVolume();
    }
    
    /**
     * get the menu background sound
     * @return mp
     */
    public static MediaPlayer mpSound()
    {
        return mp;
    }

    /**
     * get the tank exploding sound
     * @return explode sound
     */
    public static MediaPlayer explodeSound()
    {
        return explode;
    }

    /**
     * get the click sound
     * @return click sound
     */
    public static MediaPlayer mediaplayerSound()
    {
        return mediaPlayer;
    }

    /**
     * get the shooting sound
     * @return shooting sound
     */
    public static MediaPlayer fireSound()
    {
        return fire;
    }
    
    /**
     * play the explode sound
     */
    public static void explode()
    {
    
        explode.play();
    
    }
    
    /**
     * stop the explode sound
     */
    public static void stopexplode()
    {
        explode.stop();
    
    }
    
    
    /**
     * stop the shooting sound and then play it.
     */
    public static void fire()
    {
        fire.stop();
        fire.play();

    }

    
    /**
     * play the menu background sound in infinite loop.
     */
    public static void playBack()
    {
         mp.setOnEndOfMedia(new Runnable() {
               @Override
            public void run() {
                 mp.seek(Duration.ZERO);
               }
           });
          mp.play();
    }


    /**
     * stop the click sound and play it.
     */
    public static void clicksound()
    {

        mediaPlayer.stop();
        mediaPlayer.play();

    }


    /**
     * stop the menu background sound
     */
    public static void stopBack()
    {

        mp.stop();
    }

    /**
     * mute all sound
     */
    public static void offSound()
    {

        mp.setMute(true);
        fire.setMute(true);
        explode.setMute(true);
        mediaPlayer.setMute(true);
       
    }
    
    /**
     * mute the shooting sound
     */
    public static void muteFire()
    {
        fire.setMute(true);
    }
    
    /**
     * unmute the shooting sound
     */
    public static void unmuteFire()
    {
        fire.setMute(false);
    }

    
    /**
     * unmute all sound
     */
    public static void onSound()
    {

        mp.setMute(false);
        fire.setMute(false);
        explode.setMute(false);
        mediaPlayer.setMute(false);
        mediaPlayer.setMute(false);
    }

}

