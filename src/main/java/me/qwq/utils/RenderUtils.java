package me.qwq.utils;

import java.awt.*;

public class RenderUtils {
    public static void drawRectangle(Graphics graphics, int x, int y, int w, int h, Color color){
        graphics.setColor(color);
        graphics.drawRect(x, y, w, h);
    }
}
