package mizer.gaming.chipjrschallenge;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;


public class Tile {
    
    public enum TileType {
        FLOOR, BLOCK, BLOCKSWITCHON, BLOCKSWITCHOFF, SECRET, DIRT, MUD, GRAVEL, INFO, GOAL,
        FIRE, WATER, ICE, ICEWALLBL, ICEWALLBR, ICEWALLTL, ICEWALLTR, SKIDDOWN, SKIDLEFT,
        SKIDRIGHT, SKIDUP, SKIDDEATH, WALLDOWN, WALLLEFT, WALLRIGHT, WALLUP
    }
    
    private TileType type;
    private ImageView imageView;
    private Rectangle2D boundary;
    
    public Tile(TileType type, int tileSize) {
        this.type = type;
        this.imageView = new ImageView(getImageForType(type));
        this.imageView.setFitWidth(tileSize);
        this.imageView.setFitHeight(tileSize);
        
        boundary = new Rectangle2D(
        imageView.getX(),
        imageView.getY(),
        imageView.getFitWidth(),
        imageView.getFitHeight()
        );
    }
    
    private Image getImageForType(TileType type) {
        switch (type) {
            case BLOCK:
                return new Image("Tile - Block.png");
            case INFO:
                return new Image("Tile - Info.png");
            case GOAL:
                return new Image("Tile - Goal.png");
            case FIRE:
                return new Image("Tile - Fire.png");
            case WATER:
                return new Image("Tile - Water.png");
            case FLOOR:
            default:
                return new Image("Tile - Floor.png");
        }
    }

    public TileType getType() {
        return type;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }
    
}