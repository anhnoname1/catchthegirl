package com.t3h.catchtheheart.model;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioManager {
    private Clip clip;

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public AudioManager(String path){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream( getClass().getResource( path ) );
            clip = AudioSystem.getClip();
            clip.open(in);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public void loop(){
        clip.loop( clip.LOOP_CONTINUOUSLY );
    }
}
