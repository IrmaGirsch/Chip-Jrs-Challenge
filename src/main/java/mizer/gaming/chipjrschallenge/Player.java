package mizer.gaming.chipjrschallenge;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player {

    private Tile[][] tiles;
    private int x;
    private int y;
    private Rectangle2D boundary;
    
    private ImageView imageView;
    private Image leftImage;
    private Image rightImage;
    private Image upImage;
    private Image downImage;
//    private ArrayList<Item> inventory;
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
        
        Image playerIcon = new Image("Chip - Down.png");
        this.imageView = new ImageView(playerIcon);
        this.imageView.setFitWidth(tileSize);
        this.imageView.setFitHeight(tileSize);
        updatePosition();
        updateBoundary();
//        this.inventory = new ArrayList<>();
//        this.isDead = false;
//        this.isSkid = false;
//        this.isSwim = false;
//        this.isSlide = false;
    }
    
    public void move(int dx, int dy) {
        int nextX = this.x + dx;
        int nextY = this.y + dy;
        
        if (dx < 0) imageView.setImage(leftImage);
        else if (dx > 0) imageView.setImage(rightImage);
        else if (dy < 0) imageView.setImage(upImage);
        else if (dy > 0) imageView.setImage(downImage);
        
        // Check collision
        if (tiles[nextY][nextX].getType() == Tile.TileType.BLOCK) {
            // Collision detected, do not move
            playCollisionSound();
            return;
        }

        this.x = nextX;
        this.y = nextY;
        
        updatePosition();
        updateBoundary();
    }

    public void updatePosition() {
        imageView.setX(x * imageView.getFitWidth());
        imageView.setY(y * imageView.getFitHeight());
    }
    
    public void updateBoundary() {
        boundary = new Rectangle2D(
            imageView.getX(),
            imageView.getY(),
            imageView.getFitWidth(),
            imageView.getFitHeight()
        );
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageView getImageView() {
        return imageView;
    }
    
    public Rectangle2D getBoundary() {
        return boundary;
    }
    
    public boolean detectCollision(Rectangle2D other) {
        return boundary.intersects(other);
    }
    
        private void playCollisionSound() {
        String soundFile = "OOF3.WAV";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

//    
//    private void collectItem(Item item){
//        inventory.add(item);
//    }
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