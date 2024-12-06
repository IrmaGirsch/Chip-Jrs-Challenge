package mizer.gaming.chipjrschallenge;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameBoard {

    public static int TILE_SIZE = 34;
    private static int GRID_WIDTH = 31;
    private static int GRID_HEIGHT = 31;
    private Tile[][] tiles;
    private Player player;
    private Canvas canvas;
    private GraphicsContext gc;

    private static final int GAMEBOARD_WIDTH = GRID_WIDTH * TILE_SIZE;
    private static final int GAMEBOARD_HEIGHT = GRID_HEIGHT * TILE_SIZE;
    private static final int VIEWPORT_WIDTH = 500;
    private static final int VIEWPORT_HEIGHT = 500;

    private int chipsRemaining;

    public GameBoard() {
        this.tiles = new Tile[GRID_WIDTH][GRID_HEIGHT];
        this.canvas = new Canvas(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        initializeTiles();
        initializePlayer();
    }

    private void initializeTiles() {
        
        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int col = 0; col < GRID_WIDTH; col++) {
                Tile.TileType tileType = (col % 10 == 0 || row % 10 == 0) ? Tile.TileType.BLOCK : Tile.TileType.FLOOR;
                if (col == 10 && row == 5) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 20 && row == 5) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 10 && row == 15) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 20 && row == 15) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 10 && row == 25) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 20 && row == 25) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 25 && row == 10) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 25 && row == 20) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 5 && row == 10) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 5 && row == 20) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 15 && row == 10) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 15 && row == 20) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 15 && row == 14) {
                    tileType = Tile.TileType.INFO;
                }
                if (col == 5 && row == 15) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 5 && row == 25) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 15 && row == 5) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 15 && row == 25) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 25 && row == 5) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 25 && row == 15) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 25 && row == 25) {
                    tileType = Tile.TileType.CHIP;
                }
                if (col == 5 && row == 5) {
                    tileType = Tile.TileType.GOAL;
                }

                Tile tile = new Tile(tileType, TILE_SIZE);
                tiles[row][col] = tile;
                gc.drawImage(tile.getImage(), col * TILE_SIZE, row * TILE_SIZE);
            }
        }
    }

    //For Initializing the Chip Counter in InfoBox
    public int countChipsOnBoard() {
        int count = 0;
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (tiles[row][col] != null && tiles[row][col].getType() == Tile.TileType.CHIP) {
                    count++;
                }
            }
        }
        return count;
    }

    public void decrementChipsRemaining() {
        if (chipsRemaining > 0) {
            chipsRemaining--;
        }
    }

    private void initializePlayer() {
        int centerX = GRID_WIDTH / 2;
        int centerY = GRID_HEIGHT / 2;
        this.player = new Player(tiles, centerX, centerY, TILE_SIZE);
        gc.drawImage(player.getImage(), player.getX() * TILE_SIZE, player.getY() * TILE_SIZE);
    }

    public void scrollToPlayer(int viewWidth, int viewHeight) {
        double playerX = player.getX() * TILE_SIZE;
        double playerY = player.getY() * TILE_SIZE;

        double viewportX = Math.max(0, Math.min(GAMEBOARD_WIDTH - VIEWPORT_WIDTH, playerX - VIEWPORT_WIDTH / 2));
        double viewportY = Math.max(0, Math.min(GAMEBOARD_HEIGHT - VIEWPORT_HEIGHT, playerY - VIEWPORT_HEIGHT / 2));

        // Clear Canvas
        gc.clearRect(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        gc.save();
        gc.translate(-viewportX, -viewportY);

        // Redraw the visible portion of the tiles and the player
        redrawTiles(viewportX, viewportY);
        redrawPlayer();
        gc.restore();
    }

    private void redrawTiles(double offsetX, double offsetY) {

        // Calculate the columns and rows that are visible based on the offset and viewport size
        int startCol = (int) (offsetX / TILE_SIZE);
        int endCol = (int) Math.min((offsetX + VIEWPORT_WIDTH) / TILE_SIZE + 1, GRID_WIDTH);

        int startRow = (int) (offsetY / TILE_SIZE);
        int endRow = (int) Math.min((offsetY + VIEWPORT_HEIGHT) / TILE_SIZE + 1, GRID_HEIGHT);

        // Loop through only the visible portion of the grid
        for (int row = startRow; row < endRow; row++) {
            for (int col = startCol; col < endCol; col++) {
                Tile tile = tiles[row][col];
                gc.drawImage(tile.getImage(), col * TILE_SIZE, row * TILE_SIZE);
            }
        }
    }

    private void redrawPlayer() {
        gc.drawImage(player.getImage(), player.getX() * TILE_SIZE, player.getY() * TILE_SIZE);
    }

    public void handlePlayerMove(Player player, InfoBox infoBox) {
        Tile currentTile = player.getCurrentTile();

        if (currentTile.getType() == Tile.TileType.INFO) {
            infoBox.showInfoTileMessage("Your mother, Miranda, has been missing for months.  Your father, Chip, left a week ago to try and go find her.  A mysterious message appears on your smart watch to go to the location of your mom's old Fun House.  You go and find a secret back door that drops you into a room called Level 148.  Collect enough chips to proceed through the NAND gate.");
        } else {
            infoBox.resetInfoDisplay();
        }
    }

    public int getChipsRemaining() {
        return countChipsOnBoard();
    }

    public void setChipsRemining() {
        this.chipsRemaining = chipsRemaining;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Player getPlayer() {
        return player;
    }
}
