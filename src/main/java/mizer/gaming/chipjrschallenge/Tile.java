package mizer.gaming.chipjrschallenge;

import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public class Tile {

    public enum TileType {
        FLOOR, BLOCK, CHIP, BLOCKSWITCHON, BLOCKSWITCHOFF, SECRET, DIRT, MUD, GRAVEL, INFO, GOAL,
        FIRE, WATER, ICE, ICEWALLBL, ICEWALLBR, ICEWALLTL, ICEWALLTR, SKIDDOWN, SKIDLEFT,
        SKIDRIGHT, SKIDUP, SKIDDEATH, WALLDOWN, WALLLEFT, WALLRIGHT, WALLUP, BLUEKEY,
        GREENKEY, REDKEY, YELLOWKEY
    }

    private TileType type;
    private ImageView imageView;
    private Image image;
    private Rectangle2D boundary;

    public Tile(TileType type, int tileSize) {
        this.type = type;
        this.image = getImageForType(type);  //So Image Can Be Drawn On Canvas
        this.imageView = new ImageView(image);  //So Image Can Also Be Used As Node
        this.imageView.setFitWidth(tileSize);
        this.imageView.setFitHeight(tileSize);
        this.boundary = new Rectangle2D(0, 0, tileSize, tileSize);
    }

    private Image getImageForType(TileType type) {
        switch (type) {
            case BLOCK:
                return new Image("Tile - Block.png");
            case CHIP:
                return new Image("Chip.png");
            case INFO:
                return new Image("Tile - Info.png");
            case GOAL:
                return new Image("Tile - Goal.png");
            case FIRE:
                return new Image("Tile - Fire.png");
            case WATER:
                return new Image("Tile - Water.png");
            case BLUEKEY:
                return new Image("Key - Blue.png");
            case GREENKEY:
                return new Image("Key - Green.png");
            case REDKEY:
                return new Image("Key - Red.png");
            case YELLOWKEY:
                return new Image("Key - Yellow.png");
            case FLOOR:
            default:
                return new Image("Tile - Floor.png");
        }
    }

    public TileType getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public void drawTile(GraphicsContext gc, int x, int y, int tileSize) {
        gc.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize);
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public ImageView getImageView() {
        return imageView;
    }
    
    

}
