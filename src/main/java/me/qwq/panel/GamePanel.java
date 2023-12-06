package me.qwq.panel;

import me.qwq.KeyHandler;
import me.qwq.MainFrame;
import me.qwq.tiles.TileManager;
import me.qwq.utils.RenderUtils;
import me.qwq.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    static final int originalTitleSize = 16;
    static final int scale = 3;
    public static final int titleSize = originalTitleSize * scale; // 48*48
    public static final int maxScreenColumns = 16;
    public static final int maxScreenRows = 8;
    public static final int screenWidth = titleSize * maxScreenColumns;
    public static final int screenHeight = titleSize * maxScreenRows;

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    static int playerX = 100;
    static int playerY = 100;
    static int playerSpeed = 4;

    public static int rectSize = 0;

    private static JLabel displayFps = null;
    private static JLabel displayPosition = null;

    public static boolean b = false;
    public static int timeCount = 0;

    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public static JLabel getDisplayFps(){
        if(displayFps == null){
            try{
                displayFps = new JLabel();
                displayFps.setName("DisplayFps");
                displayFps.setBounds(5, 5, 80, 20);
                displayFps.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
                displayFps.setHorizontalAlignment(SwingConstants.CENTER);
                displayFps.setForeground(Color.WHITE);
                displayFps.setText("FPS:60");
            }catch (Throwable ex){
                SwingUtils.showErrorPopup(ex);
            }
        }
        return displayFps;
    }

    public static JLabel getDisplayPosition(){
        if (displayPosition == null){
            try{
                displayPosition = new JLabel();
                displayPosition.setName("DisplayPosition");
                displayPosition.setBounds(10, 25, 200, 20);
                displayPosition.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
                displayPosition.setHorizontalAlignment(SwingConstants.LEFT);
                displayPosition.setForeground(Color.WHITE);
                displayPosition.setText("Location:" + playerX + " " + playerY);
            }catch (Throwable ex){
                SwingUtils.showErrorPopup(ex);
            }
        }
        return displayPosition;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000F / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                displayFps.setText("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(keyHandler.upPressed){
            playerY = (playerY >= 4)?playerY - playerSpeed:0;
        }else if(keyHandler.downPressed){
            playerY = (playerY <= screenHeight - titleSize - 4)?playerY + playerSpeed:screenHeight - titleSize;
        }else if(keyHandler.leftPressed){
            playerX = (playerX >= 4)?playerX - playerSpeed:0;
        }else if(keyHandler.rightPressed){
            playerX = (playerX <= screenWidth - titleSize - 4)?playerX + playerSpeed:screenWidth - titleSize;
        }else if(keyHandler.fPressed){
            b = true;
        }

        if(b){
            if(rectSize >= screenHeight){
                rectSize = 0;
                b = false;
            }
            rectSize+=2;
        }

        displayPosition.setText("Location:" + playerX + " " + playerY);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);

        Graphics2D player = (Graphics2D) graphics;
        player.setColor(Color.WHITE);
        player.fillRect(playerX, playerY, titleSize, titleSize);

        Graphics2D rectangle = (Graphics2D) graphics;
        RenderUtils.drawRectangle(rectangle, playerX + titleSize / 2 - rectSize / 2, playerY + titleSize / 2 - rectSize / 2, rectSize, rectSize, Color.WHITE);

        player.dispose();
        rectangle.dispose();
    }

}
