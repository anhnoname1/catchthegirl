package com.t3h.catchtheheart.controller;

import com.t3h.catchtheheart.ImgUtils;
import com.t3h.catchtheheart.gui.Constants;
import com.t3h.catchtheheart.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ItemsManager implements Constants {
    public static int count;


    private ArrayList<Item> listItem;
    private int itemNumber;
    private Girl girl;
    private int delayHeartsmall;
    private int delayHeartbig;
    private int delayHeartblack;
    private int delaySnow;
    private int score;


    public ItemsManager() {
        girl = new Girl( ImgUtils.getImage(ImgUtils.ID_GIRL), 300, HEIGHT_GUI - 50, 50, 1);
        delayHeartblack = 5;
        delayHeartsmall = 4;
        delayHeartbig = 4;
        delaySnow = 5;
        count = 0;
        itemNumber = 15;
        initItem();
    }

    public Girl getGirl() {
        return girl;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void initItem() {
        listItem = new ArrayList<>();
        Random rd = new Random();
        int sizeItem = 40;
        for (int i = 0; i < 15; i++) {
            Item item;
            int x = rd.nextInt(WIDTH_GUI - sizeItem);
            int a = rd.nextInt(4);
            switch (a) {
                case 0:
                    item = new HeartBlack(ImgUtils.getImage(ImgUtils.ID_HEARTBLACK), x, -(i * sizeItem), sizeItem, delayHeartblack);
                    listItem.add(item);
                    break;
                case 1:
                    item = new HeartSmall(ImgUtils.getImage(ImgUtils.ID_HEARTSMALL), x, -(i * sizeItem), sizeItem, delayHeartsmall);
                    listItem.add(item);
                    break;
                case 2:
                    item = new HeartBig(ImgUtils.getImage(ImgUtils.ID_HEARTBIG), x, -(i * sizeItem), sizeItem, delayHeartbig);
                    listItem.add(item);
                    break;
                case 3:
                    item = new Snowflakes(ImgUtils.getImage(ImgUtils.ID_SNOW), x, -(i * sizeItem), sizeItem, delaySnow);
                    listItem.add(item);
                    break;
            }
        }
    }

    public void drawAll(Graphics2D g2d) {
        girl.draw(g2d);

        for (int i = 0; i < listItem.size(); i++) {
            listItem.get(i).draw(g2d);
        }
    }

    public void move(int count) {
        if (count >= Integer.MAX_VALUE) {
            count = 0;
        }

        girl.move(count, this);

        for (int i = 0; i < listItem.size(); i++) {
            listItem.get(i).move(count);
            if (listItem.get(i).getY() >= HEIGHT_GUI) {
                removeItem(i);
            }
        }

        if(score > 150) {

            delayHeartbig = 1;
            delayHeartsmall = 1;
            delayHeartblack = 1;
            delaySnow =4;
            for(int i = 0; i < listItem.size(); i++) {
                if(listItem.get(i) instanceof HeartBlack) {
                    listItem.get(i).setDelay(delayHeartblack);
                } else if(listItem.get(i) instanceof HeartBig) {
                    listItem.get(i).setDelay(delayHeartbig);
                }
                else if(listItem.get(i) instanceof Snowflakes) {
                    listItem.get(i).setDelay(delaySnow);
                }else {
                    listItem.get(i).setDelay(delayHeartsmall);
                }
            }
        } else if(score > 100) {
            delayHeartbig = 3;
            delayHeartsmall = 2;
            delayHeartblack = 2;
            delaySnow =4;
            for(int i = 0; i < listItem.size(); i++) {
                if(listItem.get(i) instanceof HeartBlack) {
                    listItem.get(i).setDelay(delayHeartblack);
                } else if(listItem.get(i) instanceof HeartBig) {
                    listItem.get(i).setDelay(delayHeartbig);
                }else if(listItem.get(i) instanceof Snowflakes) {
                    listItem.get(i).setDelay(delaySnow);
                } else {
                    listItem.get(i).setDelay(delayHeartsmall);
                }
            }
        } else if(score > 50) {
            delayHeartbig = 4;
            delayHeartsmall = 3;
            delayHeartblack = 3;
            delaySnow =4;
            for(int i = 0; i < listItem.size(); i++) {
                if(listItem.get(i) instanceof HeartBlack) {
                    listItem.get(i).setDelay(delayHeartblack);
                } else if(listItem.get(i) instanceof HeartBig) {
                    listItem.get(i).setDelay(delayHeartbig);
                }else if(listItem.get(i) instanceof Snowflakes) {
                    listItem.get(i).setDelay(delaySnow);
                } else {
                    listItem.get(i).setDelay(delayHeartsmall);
                }
            }

        }
    }

    public void removeItem(int i) {
        listItem.remove(i);
        Random rd = new Random();
        Item item;
        int sizeItem = 40;
        int x = rd.nextInt(WIDTH_GUI - sizeItem);
        int a = rd.nextInt(3);
        switch (a) {
            case 0:
                item = new HeartBlack(ImgUtils.getImage(ImgUtils.ID_HEARTBLACK), x, -(1*sizeItem), sizeItem, delayHeartblack);
                listItem.add(item);
                break;
            case 1:
                item = new HeartSmall(ImgUtils.getImage(ImgUtils.ID_HEARTSMALL), x, -(1*sizeItem), sizeItem, delayHeartsmall);
                listItem.add(item);
                break;
            case 2:
                item = new HeartBig(ImgUtils.getImage(ImgUtils.ID_HEARTBIG), x, -(1*sizeItem), sizeItem, delayHeartbig);
                listItem.add(item);
                break;
            case 3:
                item = new Snowflakes(ImgUtils.getImage(ImgUtils.ID_SNOW), x, -(1*sizeItem), sizeItem, delaySnow);
                listItem.add(item);
                break;
        }
    }

    public boolean isOver() {
        return girl.isDead();
    }


}
