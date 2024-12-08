package mizer.gaming.chipjrschallenge;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {

    private Tile[][] tiles;
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
//    private boolean isDead;
//    private boolean isSkid;
//    private boolean isSwim;
//    private boolean isSlide;

    //Constructor for Chip Jr
    public Player(Tile[][] tiles, int startX, int startY, int tileSize) {
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

//        this.isDead = false;
//        this.isSkid = false;
//        this.isSwim = false;
//        this.isSlide = false;
    }

    public void move(int dx, int dy, GameBoard gameBoard, InfoBox infoBox) {

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

        // Check For Collision With Block Tile
        if (tiles[nextY][nextX].getType() == Tile.TileType.BLOCK) {
            playCollisionSound();
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

    public Tile getCurrentTile() {
        return tiles[y][x];
    }

    public void collectKey(int keyIndex) {
        if (keyIndex >= 0 && keyIndex < keys.length) {
            keys[keyIndex] = true;
        }
    }

    public void collectBoot(int bootIndex) {
        if (bootIndex >= 0 && bootIndex < boots.length) {
            boots[bootIndex] = true;
        }
    }

    public boolean hasKey(int keyIndex) {
        return keyIndex >= 0 && keyIndex < keys.length && keys[keyIndex];
    }

    public boolean hasBoot(int bootIndex) {
        return bootIndex >= 0 && bootIndex < boots.length && boots[bootIndex];
    }
//    
//    private void die(){
//        isDead = true;
//    }
//    
//    private void swim(){
//        
//    }
//    
//    private void skid(){
//        
//    }
}
