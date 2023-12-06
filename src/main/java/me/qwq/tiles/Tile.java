package me.qwq.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Tile extends Image {
    public BufferedImage bufferedImage;
    public boolean collision = false;

    @Override
    public int getWidth(ImageObserver observer) {
        return 0;
    }

    @Override
    public int getHeight(ImageObserver observer) {
        return 0;
    }

    @Override
    public ImageProducer getSource() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Object getProperty(String name, ImageObserver observer) {
        return null;
    }
}
