package mizer.gaming.chipjrschallenge;

import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameBoard {

    private static int TILE_SIZE = 68;
    private static int GRID_HEIGHT = 31;
    private static int GRID_WIDTH = 31;
    private Tile[][]tiles;
    private Player player;
    private Pane pane;

    public GameBoard() {
        this.tiles = new Tile[GRID_WIDTH][GRID_HEIGHT];
        this.pane = new Pane();

        initializeTiles();
        initializePlayer();
    }

    private void initializeTiles() {
        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int col = 0; col < GRID_WIDTH; col++) {
                Tile.TileType tileType = (col % 10 == 0 || row % 10 == 0) ? Tile.TileType.BLOCK : Tile.TileType.FLOOR;
                if (col == 10 && row == 5) tileType = Tile.TileType.FLOOR;
                if (col == 20 && row == 5) tileType = Tile.TileType.FLOOR;
                if (col == 10 && row == 15) tileType = Tile.TileType.FLOOR;
                if (col == 20 && row == 15) tileType = Tile.TileType.FLOOR;
                if (col == 10 && row == 25) tileType = Tile.TileType.FLOOR;
                if (col == 20 && row == 25) tileType = Tile.TileType.FLOOR;
                if (col == 25 && row == 10) tileType = Tile.TileType.FLOOR;
                if (col == 25 && row == 20) tileType = Tile.TileType.FLOOR;
                if (col == 5 && row == 10) tileType = Tile.TileType.FLOOR;
                if (col == 5 && row == 20) tileType = Tile.TileType.FLOOR;
                if (col == 15 && row == 10) tileType = Tile.TileType.FLOOR;
                if (col == 15 && row == 20) tileType = Tile.TileType.FLOOR;
                if (col == 15 && row == 14) tileType = Tile.TileType.INFO;
                if (col == 5 && row == 5) tileType = Tile.TileType.GOAL;

                Tile tile = new Tile(tileType, TILE_SIZE);
                tile.getImageView().setX(col * TILE_SIZE);
                tile.getImageView().setY(row * TILE_SIZE);
                pane.getChildren().add(tile.getImageView());
                tiles[row][col] = tile;
            }
        }
    }

    private void initializePlayer() {
        int centerX = GRID_WIDTH / 2;
        int centerY = GRID_HEIGHT / 2;
        this.player = new Player(tiles, centerX, centerY, TILE_SIZE);
        pane.getChildren().add(player.getImageView());
    }
    
    public void scrollToPlayer(int viewWidth, int viewHeight) {
        double playerX = player.getX() * TILE_SIZE;
        double playerY = player.getY() * TILE_SIZE;

        double offsetX = Math.max(0, Math.min(playerX - viewWidth / 2, GRID_WIDTH * TILE_SIZE - viewWidth));
        double offsetY = Math.max(0, Math.min(playerY - viewHeight / 2, GRID_HEIGHT * TILE_SIZE - viewHeight));

        pane.setTranslateX(-offsetX);
        pane.setTranslateY(-offsetY);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Pane getPane() {
        return pane;
    }

    public Player getPlayer() {
        return player;
    }
    
}
