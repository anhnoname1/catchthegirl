package com.t3h.catchtheheart;

import javax.swing.*;
import java.awt.*;

public class ImgUtils {

    public static final int ID_GIRL= 0;
    public static final int ID_HEARTBLACK = 1;
    public static final int ID_HEARTBIG = 2;
    public static final int ID_HEARTSMALL = 3;
    public static final int ID_BACKGROUND = 4;
    public static final int ID_POINT=5;
    public static final int ID_SNOW = 6;
    public static final int ID_ICON = 7;

    private static final Image GIRL = new ImageIcon(Image.class.getResource("/imgs/girl_left.png")).getImage();
    private static final Image HEARTBLACK = new ImageIcon(Image.class.getResource("/imgs/bom.png")).getImage();
    private static final Image HEARTBIG = new ImageIcon(Image.class.getResource("/imgs/timdo2.png")).getImage();
    private static final Image HEARTSMALL = new ImageIcon(Image.class.getResource("/imgs/timdo.png")).getImage();
    private static final Image BACKGROUND = new ImageIcon(Image.class.getResource("/imgs/background.jpg")).getImage();
    private static final Image POINT = new ImageIcon( Image.class.getResource( "/imgs/diem1.gif" ) ).getImage();
    private static final Image SNOW = new ImageIcon( Image.class.getResource("/imgs/bongtuyet.png")).getImage();
    private static final Image ICON = new ImageIcon( Image.class.getResource( "/imgs/icon-nen.jpg" ) ).getImage();

    public static Image getImage(int id) {
        switch (id) {
            case ID_GIRL:
                return GIRL;
            case ID_HEARTBLACK:
                return HEARTBLACK;
            case ID_HEARTBIG:
                return HEARTBIG;
            case ID_HEARTSMALL:
                return HEARTSMALL;
            case ID_BACKGROUND:
                return BACKGROUND;
            case ID_POINT:
                return POINT;
            case ID_SNOW:
                return SNOW;
            case ID_ICON:
                return ICON;
            default:
                return null;
        }
    }
}

