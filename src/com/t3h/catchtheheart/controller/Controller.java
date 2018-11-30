package com.t3h.catchtheheart.controller;

import com.t3h.catchtheheart.gui.MyGame;
import com.t3h.catchtheheart.model.AudioManager;


import javax.sound.sampled.AudioInputStream;
import java.awt.event.KeyEvent;

public class Controller {
    private AudioManager au;
    private MyGame myGame;
    public Controller(MyGame myGame) {
        this.myGame = myGame;
    }

    public void keyPressed(int keyCode) {

        if(myGame.isOverGame()) {
            return;
        }
        switch (keyCode) {
            case  KeyEvent.VK_ENTER:
                if(!myGame.isRunning()) {
                    myGame.startGame();
                }
                break;
            case KeyEvent.VK_LEFT:
                myGame.getItemsManager().getGirl().setSpeed(-1);
                break;
            case KeyEvent.VK_RIGHT:
                myGame.getItemsManager().getGirl().setSpeed(1);
                break;
            case KeyEvent.VK_P:
                if(!myGame.isPause()) {
                    myGame.getThread().suspend();
                    myGame.setPause(true);
                } else {
                    myGame.getThread().resume();
                    myGame.setPause(false);
                }
                break;
        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                myGame.getItemsManager().getGirl().setSpeed(0);
                break;
            case KeyEvent.VK_RIGHT:
                myGame.getItemsManager().getGirl().setSpeed(0);
                break;
        }
    }

}

