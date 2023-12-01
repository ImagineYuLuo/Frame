package me.qwq.panel;

import me.qwq.KeyHandler;
import me.qwq.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class MovePanel extends JPanel implements Runnable{

    static final int originalTitleSize = 16;
    static final int scale = 3;
    static final int titleSize = originalTitleSize * scale; // 48*48
    static final int maxScreenColumns = 16;
    static final int maxScreenRows = 8;
    public static final int screenWidth = titleSize * maxScreenColumns;
    public static final int screenHeight = titleSize * maxScreenRows;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    static int playerX = 100;
    static int playerY = 100;
    static int playerSpeed = 4;

    private static JLabel displayFps = null;

    public MovePanel(){
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
        }
        //System.out.println(playerX + " " + playerY);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerX, playerY, titleSize, titleSize);


        Graphics2D enemy1 = (Graphics2D) graphics;
        enemy1.setColor(Color.CYAN);
        enemy1.fillRect(384, 214, 16, 16);

        graphics2D.dispose();
        enemy1.dispose();
    }

}
