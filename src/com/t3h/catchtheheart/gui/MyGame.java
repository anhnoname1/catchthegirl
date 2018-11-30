package com.t3h.catchtheheart.gui;

import com.t3h.catchtheheart.ImgUtils;
import com.t3h.catchtheheart.controller.Controller;
import com.t3h.catchtheheart.controller.ItemsManager;
import com.t3h.catchtheheart.model.AudioManager;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class MyGame extends JPanel implements Runnable, KeyListener,Constants {
    private ItemsManager itemsManager;
    private Thread thread;
    private Controller controller;
    private boolean isRunning;
    private boolean isPause;
    public AudioManager au;



    public MyGame() {
        init();
        initComponents();



    }

    private void init() {
        setSize(GUI.WIDTH, GUI.HEIGHT);
        setLocation(0, 0);
        setLayout(null);

    }

    private void initComponents() {
        isPause = false;
        isRunning = false;
        controller = new Controller(this);

    }

    public Thread getThread() {
        return thread;
    }

    public boolean isPause() {

        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ItemsManager getItemsManager() {
        return itemsManager;
    }

    public void startGame() {
        itemsManager = new ItemsManager();
        thread = new Thread(this);
        isRunning = true;
        thread.start();
        au= new AudioManager( "/audio/nhacnen-enter.wav" );
        au.loop();
        au.play();
        if(isPause){

            au.stop();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(isRunning()) {
            g2d.drawImage( ImgUtils.getImage(ImgUtils.ID_BACKGROUND), 0, 0, WIDTH_GUI, HEIGHT_GUI, null);
            itemsManager.drawAll(g2d);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("Score: " + itemsManager.getScore(), 10, 30);
            g2d.drawString( "Pause: P ",10,70 );
            g2d.drawImage( ImgUtils.getImage( ImgUtils.ID_POINT ),155,0,45,45,null );
        }
          else {
            g2d.drawImage( ImgUtils.getImage( ImgUtils.ID_BACKGROUND ),0,0,WIDTH_GUI,HEIGHT_GUI,null );
            g2d.setFont(new Font("Arial", Font.BOLD, 60));
            g2d.setColor( Color.RED );
            g2d.drawString("ENTER to play", 100, 300);

        }

    }



    public boolean isOverGame(){
        if ( itemsManager == null ) {
            return false;
        }
        return itemsManager.isOver();
    }

    @Override
    public void run() {

        while (!itemsManager.isOver()) {
            try {
                Thread.sleep( 3 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isRunning()) {
                itemsManager.count++;
                itemsManager.move( itemsManager.count );
            }
            repaint();
        }
        if (itemsManager.isOver()) {
            isRunning = false;
            isPause = true;
            au.stop();

            int i = JOptionPane.showConfirmDialog( this, "Game Over !\n Bạn có muốn chơi lại không?",
                    "Thông báo", JOptionPane.YES_NO_OPTION );
            if (i == JOptionPane.YES_OPTION) {

                isRunning = true;
                startGame();
                au.loop();
                au.play();
            } else {
                isRunning = false;
                isPause = true;
                return;

            }
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        controller.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.keyReleased(e.getKeyCode());
    }

}
