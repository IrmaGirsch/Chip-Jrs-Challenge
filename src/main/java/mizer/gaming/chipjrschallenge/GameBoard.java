package mizer.gaming.chipjrschallenge;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
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

        chipsRemaining = 0;

        //Set Points For Chip Locations
        List<Point> chipPositions = Arrays.asList(
                new Point(5, 15), new Point(5, 25),
                new Point(15, 5), new Point(15, 25),
                new Point(25, 5), new Point(25, 15), new Point(25, 25)
        );

        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int col = 0; col < GRID_WIDTH; col++) {

                Tile.TileType tileType = (col % 10 == 0 || row % 10 == 0) ? Tile.TileType.BLOCK : Tile.TileType.FLOOR;
                if (col == 10 && row == 5) {
                    tileType = Tile.TileType.GREENDOOR;
                }
                if (col == 20 && row == 5) {
                    tileType = Tile.TileType.YELLOWDOOR;
                }
                if (col == 10 && row == 15) {
                    tileType = Tile.TileType.REDDOOR;
                }
                if (col == 20 && row == 15) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 10 && row == 25) {
                    tileType = Tile.TileType.BLUEDOOR;
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
                    tileType = Tile.TileType.GREENDOOR;
                }
                if (col == 5 && row == 20) {
                    tileType = Tile.TileType.REDDOOR;
                }
                if (col == 15 && row == 10) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (col == 15 && row == 20) {
                    tileType = Tile.TileType.FLOOR;
                }
                if (chipPositions.contains(new Point(col, row))) {
                    tileType = Tile.TileType.CHIP;
                    chipsRemaining++;
                }
                if (col == 15 && row == 14) {
                    tileType = Tile.TileType.INFO;
                }
                if (col == 5 && row == 5) {
                    tileType = Tile.TileType.GOAL;
                }
                if (col == 5 && row == 4) {
                    tileType = Tile.TileType.GATE;
                }
                if (col == 5 && row == 6) {
                    tileType = Tile.TileType.GATE;
                }
                if (col == 4 && row == 5) {
                    tileType = Tile.TileType.GATE;
                }
                if (col == 6 && row == 5) {
                    tileType = Tile.TileType.GATE;
                }
                //Some Actual Board Layout!  The Fun Stuff...
                if (col == 9 && row == 1) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 2) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 3) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 4) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 5) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 6) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 7) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 8) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 1 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 2 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 3 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 4 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 5 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 6 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 7 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 8 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 2 && row == 7) {
                    tileType = Tile.TileType.REDKEY;
                }
                if (col == 2 && row == 28) {
                    tileType = Tile.TileType.YELLOWKEY;
                }
                if (col == 15 && row == 2) {
                    tileType = Tile.TileType.BLUEKEY;
                }
                if (col == 28 && row == 28) {
                    tileType = Tile.TileType.GREENKEY;
                }
                if (col == 18 && row == 28) {
                    tileType = Tile.TileType.FIREBOOT;
                }
                if (col == 28 && row == 2) {
                    tileType = Tile.TileType.WATERBOOT;
                }
                if (col == 28 && row == 15) {
                    tileType = Tile.TileType.ICEBOOT;
                }
                if (col == 1 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 2 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 3 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 4 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 5 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 6 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 7 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 8 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 11) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 22 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 23 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 24 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 25 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 26 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 27 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 28 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 29 && row == 9) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 22 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 23 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 24 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 25 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 26 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 27 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 28 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 29 && row == 21) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 22) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 23) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 24) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 25) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 26) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 27) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 28) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 21 && row == 29) {
                    tileType = Tile.TileType.WATER;
                }
                if (col == 9 && row == 12) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 13) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 14) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 15) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 16) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 17) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 18) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 9 && row == 19) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 11) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 12) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 13) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 14) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 15) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 16) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 17) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 18) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 21 && row == 19) {
                    tileType = Tile.TileType.FIRE;
                }
                if (col == 13 && row == 7) {
                    tileType = Tile.TileType.BLOCK;
                }
                if (col == 14 && row == 7) {
                    tileType = Tile.TileType.BLOCK;
                }
                if (col == 15 && row == 7) {
                    tileType = Tile.TileType.BLOCK;
                }
                if (col == 16 && row == 7) {
                    tileType = Tile.TileType.BLOCK;
                }
                if (col == 17 && row == 7) {
                    tileType = Tile.TileType.BLOCK;
                }
                if (col == 13 && row == 8) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 14 && row == 8) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 15 && row == 8) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 16 && row == 8) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 17 && row == 8) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 13 && row == 9) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 14 && row == 9) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 15 && row == 9) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 16 && row == 9) {
                    tileType = Tile.TileType.ICE;
                }
                if (col == 17 && row == 9) {
                    tileType = Tile.TileType.ICE;
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

    public void decrementChipsRemaining(InfoBox infoBox, GameBoard level1) {
        if (chipsRemaining > 0) {
            chipsRemaining--;
            infoBox.updateChipsRemaining(chipsRemaining, level1);
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
        } else if (currentTile.getType() == Tile.TileType.CHIP) {
            player.playChipSound();
            currentTile.setNewType(Tile.TileType.FLOOR);
            decrementChipsRemaining(infoBox, this);
        } else if (currentTile.getType() == Tile.TileType.BLUEKEY) {
            player.playBlipSound();
            player.collectKey(0, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.GREENKEY) {
            player.playBlipSound();
            player.collectKey(1, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.REDKEY) {
            player.playBlipSound();
            player.collectKey(2, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.YELLOWKEY) {
            player.playBlipSound();
            player.collectKey(3, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.FIREBOOT) {
            player.playBlipSound();
            player.collectBoot(0, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.ICEBOOT) {
            player.playBlipSound();
            player.collectBoot(1, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.SKIDBOOT) {
            player.playBlipSound();
            player.collectBoot(2, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.WATERBOOT) {
            player.playBlipSound();
            player.collectBoot(3, infoBox, player);
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.BLUEDOOR && player.hasKey(0)) {
            player.playDoorSound();
            currentTile.setNewType(Tile.TileType.FLOOR);
            player.useKey(0, infoBox, player);
        } else if (currentTile.getType() == Tile.TileType.GREENDOOR && player.hasKey(1)) {
            player.playDoorSound();
            currentTile.setNewType(Tile.TileType.FLOOR);
            player.useKey(1, infoBox, player);
        } else if (currentTile.getType() == Tile.TileType.REDDOOR && player.hasKey(2)) {
            player.playDoorSound();
            currentTile.setNewType(Tile.TileType.FLOOR);
            player.useKey(2, infoBox, player);
        } else if (currentTile.getType() == Tile.TileType.YELLOWDOOR && player.hasKey(3)) {
            player.playDoorSound();
            currentTile.setNewType(Tile.TileType.FLOOR);
            player.useKey(3, infoBox, player);
        } else if (currentTile.getType() == Tile.TileType.FIRE && !player.hasBoot(0)) {
            player.hasFireDied();
            return;
        } else if (currentTile.getType() == Tile.TileType.GATE && this.getChipsRemaining() <= 0) {
            currentTile.setNewType(Tile.TileType.FLOOR);
        } else if (currentTile.getType() == Tile.TileType.WATER && !player.hasBoot(3)) {
            player.hasWaterDied();
            return;
        } else if (currentTile.getType() == Tile.TileType.GOAL) {
            player.playDittySound();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations");
            alert.setHeaderText(null);
            alert.setContentText("You Won!");

            alert.showAndWait();
        } else {
            infoBox.resetInfoDisplay();

        }
    }

    public int getChipsRemaining() {
        return countChipsOnBoard();
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
