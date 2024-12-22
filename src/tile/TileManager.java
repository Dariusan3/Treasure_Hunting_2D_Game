package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;
    private static final Logger LOGGER = Logger.getLogger(TileManager.class.getName());

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        try {

            tiles[0] = new Tile();
            tiles[0].tileImage = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].tileImage = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/tiles/wall.png")));

            tiles[2] = new Tile();
            tiles[2].tileImage = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/tiles/water.png")));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading tile images.", e);
        }
    }

    public void loadMap() {
        try {

            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

                String line = br.readLine();

                while(col < gamePanel.maxScreenCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;
                }

                if(col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        }catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

            int tileNum = mapTileNum[row][col];

            g2.drawImage(tiles[tileNum].tileImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
