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
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
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

            tiles[3] = new Tile();
            tiles[3].tileImage = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/tiles/earth.png")));

            tiles[4] = new Tile();
            tiles[4].tileImage = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/tiles/tree.png")));

            tiles[5] = new Tile();
            tiles[5].tileImage = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/tiles/sand.png")));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading tile images.", e);
        }
    }

    public void loadMap(String filePath) {
        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

                String line = br.readLine();

                while(col < gamePanel.maxWorldCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gamePanel.maxWorldCol) {
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

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(tiles[tileNum].tileImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            } // we draw the tiles just if we are in these boundaries in order to remove extra processing

            worldCol++;

            if(worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
