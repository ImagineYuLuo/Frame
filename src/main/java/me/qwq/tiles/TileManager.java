package me.qwq.tiles;

import me.qwq.panel.GamePanel;
import me.qwq.utils.SwingUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[GamePanel.maxScreenColumns][GamePanel.maxScreenRows];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/dirt.jpg")));

            tiles[1] = new Tile();
            tiles[1].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/grass.jpg")));

            tiles[2] = new Tile();
            tiles[2].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/sky.jpg")));
        }catch (Throwable ex){
            SwingUtils.showErrorPopup(ex);
        }
    }

    public void loadMap(String files){

        try{
            InputStream inputStream = getClass().getResourceAsStream(files);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
            int columns = 0, rows = 0;
            while (columns < GamePanel.maxScreenColumns && rows < GamePanel.maxScreenRows){
                String lines = bufferedReader.readLine();
                while (columns < GamePanel.maxScreenColumns){
                    String[] numbers = lines.split(" ");
                    int number = Integer.parseInt(numbers[columns]);
                    mapTileNumber[columns][rows] = number;
                    columns++;
                }
                if(columns == GamePanel.maxScreenColumns){
                    columns = 0;
                    rows++;
                }
            }
            bufferedReader.close();
        }catch (Throwable ex){
            SwingUtils.showErrorPopup(ex);
        }

    }

    public void draw(Graphics2D graphics2D){
        int columns = 0, rows = 0;
        int x = 0, y = 0;
        while (columns < GamePanel.maxScreenColumns && rows < GamePanel.maxScreenRows){
            int tileNum = mapTileNumber[columns][rows];
            graphics2D.drawImage(tiles[tileNum].bufferedImage, x, y, GamePanel.titleSize, GamePanel.titleSize, null);
            columns++;
            x += GamePanel.titleSize;
            if(columns == GamePanel.maxScreenColumns){
                columns = 0;
                x = 0;
                rows++;
                y += GamePanel.titleSize;
            }
        }
    }

}
