package com.t3h.catchtheheart.gui;

import com.t3h.catchtheheart.ImgUtils;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    private MyGame myGame;

    public GUI() {
        init();

    }

    private void init() {
        setTitle("Catch The Heart");
        setSize(WIDTH + 6, HEIGHT + 29);
        setResizable(false);
        setIconImage( ImgUtils.getImage(ImgUtils.ID_ICON));
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        myGame = new MyGame();
        add(myGame);
        addKeyListener(myGame);
    }

}