package com.t3h.catchtheheart.model;

import com.t3h.catchtheheart.ImgUtils;
import com.t3h.catchtheheart.controller.ItemsManager;
import com.t3h.catchtheheart.gui.Constants;

import java.awt.*;
import java.util.ArrayList;

public class Girl extends Item implements Constants {
    public static final int DIR_LEFT = 0;
    public static final int DIR_RIGHT = 1;
    private AudioManager au;


    private boolean isDead;
    private int speed;

    public Girl(Image img, int x, int y, int size, int delay) {
        super(img, x, y, size, delay);
        this.img = ImgUtils.getImage(ImgUtils.ID_GIRL);

        isDead = false;



    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isDead() {
        return isDead;
    }

    public void move(int times, ItemsManager itemsManager) {
        if (times % delay == 0) {
            if (speed < 0) {

                x += speed;
                this.img = ImgUtils.getImage(ImgUtils.ID_GIRL);
                if (x <= -size) {
                    x = WIDTH_GUI - 1;
                }
//                au = new AudioManager( "/audio/flap.wav" );
//                au.play();
            } else if(speed > 0) {

                x += speed;
                this.img = ImgUtils.getImage(ImgUtils.ID_GIRL);
                if (x >= WIDTH_GUI) {
                    x = 1 - size;
                }
//                au = new AudioManager( "/audio/flap.wav" );
//                au.play();
            }
        }

        Rectangle rect = new Rectangle(x, y, size, size);
        ArrayList<Item> items = itemsManager.getListItem();
        for (int i = 0; i < items.size(); i++) {
            Rectangle r = new Rectangle(items.get(i).getX(), items.get(i).getY(), items.get(i).getSize(), items.get(i).getSize());
            if (rect.intersects(r)) {
                if (items.get(i) instanceof HeartBlack) {

                    isDead = true;
                    au = new AudioManager( "/audio/die.wav" );
                    au.play();
                    return;
                } else if (items.get(i) instanceof HeartBig) {
                    itemsManager.setScore(itemsManager.getScore() + 10);
                    itemsManager.removeItem(i);
                    au = new AudioManager( "/audio/point.wav" );
                    au.play();


                }
                else if (items.get(i) instanceof Snowflakes) {
                    itemsManager.setScore(itemsManager.getScore() + 0);



                }else {
                    itemsManager.setScore(itemsManager.getScore() + 5);
                    itemsManager.removeItem(i);
                    au = new AudioManager( "/audio/point.wav" );
                    au.play();

                }
            }
        }
    }
}
