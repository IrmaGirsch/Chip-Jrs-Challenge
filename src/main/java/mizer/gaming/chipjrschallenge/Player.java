package mizer.gaming.chipjrschallenge;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {

    TimeManager timeManager;

    private Tile[][] tiles;
    private boolean isAlive = true;
    //Location
    private int x;
    private int y;
    //Images
    private Image leftImage;
    private Image rightImage;
    private Image upImage;
    private Image downImage;
    private Image currentImage;
    //Inventory
    private boolean[] keys;
    private boolean[] boots;

//    private boolean isSkid;
//    private boolean isSwim;
//    private boolean isSlide;
    //Constructor for Chip Jr
    public Player(Tile[][] tiles, int startX, int startY, int tileSize) {

        this.timeManager = new TimeManager();
        this.tiles = tiles;
        this.x = startX;
        this.y = startY;

        this.leftImage = new Image("Chip - Left.png");
        this.rightImage = new Image("Chip - Right.png");
        this.upImage = new Image("Chip - Up.png");
        this.downImage = new Image("Chip - Down.png");

        this.currentImage = downImage;

        //Set Up Inventory
        this.keys = new boolean[4];
        this.boots = new boolean[4];

//        this.isSkid = false;
//        this.isSwim = false;
//        this.isSlide = false;
    }

    public void move(int dx, int dy, GameBoard gameBoard, InfoBox infoBox, Player player) {
        if (!isAlive) {
            return;
        }

        if (dx < 0) {
            currentImage = leftImage;
        } else if (dx > 0) {
            currentImage = rightImage;
        } else if (dy < 0) {
            currentImage = upImage;
        } else if (dy > 0) {
            currentImage = downImage;
        }

        int nextX = this.x + dx;
        int nextY = this.y + dy;

        // Check For Collision With Block and Doors, and Handle Ice
        if (tiles[nextY][nextX].getType() == Tile.TileType.BLOCK) {
            playCollisionSound();
            return;
        } else if (tiles[nextY][nextX].getType() == Tile.TileType.BLUEDOOR && !player.hasKey(0)) {
            playCollisionSound();
            return;
        } else if (tiles[nextY][nextX].getType() == Tile.TileType.GREENDOOR && !player.hasKey(1)) {
            playCollisionSound();
            return;
        } else if (tiles[nextY][nextX].getType() == Tile.TileType.REDDOOR && !player.hasKey(2)) {
            playCollisionSound();
            return;
        } else if (tiles[nextY][nextX].getType() == Tile.TileType.YELLOWDOOR && !player.hasKey(3)) {
            playCollisionSound();
            return;
        } else if (tiles[nextY][nextX].getType() == Tile.TileType.ICE && !hasBoot(1)) {
            slide(dx, dy, gameBoard, infoBox);
            return;
        }

        this.x = nextX;
        this.y = nextY;

        gameBoard.handlePlayerMove(this, infoBox);
    }

    public void draw(GraphicsContext gc, int tileSize) {
        gc.drawImage(currentImage, x * tileSize, y * tileSize);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return currentImage;
    }

    public boolean detectCollision(int newX, int newY) {
        return tiles[newY][newX].getType() == Tile.TileType.BLOCK;
    }

    public void playCollisionSound() {
        String soundFile = "OOF3.WAV";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void playChipSound() {
        String soundFile = "CLICK3.WAV";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void playBlipSound() {
        String soundFile = "BLIP2.WAV";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void playDoorSound() {
        String soundFile = "DOOR.WAV";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void playDeathSound() {
        String soundFile = "BUMMER.WAV";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public Tile getCurrentTile() {
        return tiles[y][x];
    }

    public void collectKey(int keyIndex, InfoBox infoBox, Player player) {
        if (keyIndex >= 0 && keyIndex < keys.length) {
            keys[keyIndex] = true;
            infoBox.updateInventory(player);
        }
    }

    public void collectBoot(int bootIndex, InfoBox infoBox, Player player) {
        if (bootIndex >= 0 && bootIndex < boots.length) {
            boots[bootIndex] = true;
            infoBox.updateInventory(player);
        }
    }

    public boolean hasKey(int keyIndex) {
        return keyIndex >= 0 && keyIndex < keys.length && keys[keyIndex];
    }

    public void useKey(int keyIndex, InfoBox infoBox, Player player) {
        if (hasKey(keyIndex)) {
            keys[keyIndex] = false;
            infoBox.updateInventory(player);
        }
    }

    public boolean hasBoot(int bootIndex) {
        return bootIndex >= 0 && bootIndex < boots.length && boots[bootIndex];
    }

    public void hasFireDied() {
        playDeathSound();
        getCurrentTile().setNewType(Tile.TileType.FIREDEATH);
        removePlayerFromBoard();
        resetInventory();
    }

    public void hasWaterDied() {

        playDeathSound();
        getCurrentTile().setNewType(Tile.TileType.WATERDEATH);
        removePlayerFromBoard();
        resetInventory();
    }

    public void slide(int dx, int dy, GameBoard gameBoard, InfoBox infoBox) {
        int slideX = this.x;
        int slideY = this.y;

        while (true) {
            slideX += dx;
            slideY += dy;

            Tile nextTile = tiles[slideY][slideX];

            if (nextTile.getType() == Tile.TileType.BLOCK) {
                slideX -= dx;
                slideY -= dy;

                // Reverse Direction
                dx = -dx;
                dy = -dy;

                //Reverse Image
                if (dx < 0) {
                    currentImage = leftImage;
                } else if (dx > 0) {
                    currentImage = rightImage;
                } else if (dy < 0) {
                    currentImage = downImage;
                } else if (dy > 0) {
                    currentImage = upImage;
                }
                continue;
            }

            if (nextTile.getType() != Tile.TileType.ICE) {
                break;
            }
        }

        this.x = slideX;
        this.y = slideY;
        gameBoard.handlePlayerMove(this, infoBox);
    }

    public void removePlayerFromBoard() {
        setCurrentImage(null);
        setAlive(false);
    }

    public void resetInventory() {
        Arrays.fill(keys, false);
        Arrays.fill(boots, false);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public void setCurrentImage(Image newImage) {
        this.currentImage = newImage;
    }
//    private void swim(){
//        
//    }
//    
//    private void skid(){
//        
//    }
}
