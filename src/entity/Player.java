package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        Logger logger = Logger.getLogger(Player.class.getName());
        try {

        up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_up_1.png")));
        up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_up_2.png")));
        down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_down_1.png")));
        down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_down_2.png")));
        left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_left_1.png")));
        left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_left_2.png")));
        right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_right_1.png")));
        right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/dac_right_2.png")));
        }catch (IOException | NullPointerException e) {
            logger.severe("Failed to load player images: " + e.getMessage());
        }
    }

    public void update() {

        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            }
            if(keyHandler.downPressed) {
                direction = "down";
                y += speed;
            }
            if(keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            }
            if(keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > 12) { // player image change every 12 frames
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                else if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                else if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                else if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                else if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
