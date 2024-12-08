package mizer.gaming.chipjrschallenge;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class InfoBox {

    private VBox infoBox;
    private Label levelLbl = new Label("LEVEL");
    private Label levelNumberLbl = new Label("001");
    private Label timeLbl = new Label("TIME");
    private Label countdownLbl = new Label();
    private Label chipsLbl = new Label("CHIPS\n  LEFT");
    private Label chipsRemainingLbl;
    private static final int INFO_BOX_WIDTH = 300;
    private static final int INFO_BOX_HEIGHT = 600;
    //Timer stuff
    private long timerStart = 200;
    private TimeManager timeManager;
    private Timer timer;
    private boolean timerRunning = true;
    //Inventory Stuff
    private GridPane inventoryGrid;
    private static final int INVENTORY_SIZE = 8;
    private Image[] inventoryImages = new Image[INVENTORY_SIZE];
    private Image floorTileImage;
    private Image blueKeyImage;
    private Image greenKeyImage;
    private Image redKeyImage;
    private Image yellowKeyImage;
    private ImageView[] inventoryTiles;

    public InfoBox(GameBoard level1) {

        int TILE_SIZE = GameBoard.TILE_SIZE;
        infoBox = new VBox();
        infoBox.getStyleClass().add("infoBox");
        infoBox.getStylesheets().add("style.css");
        infoBox.setPrefWidth(INFO_BOX_WIDTH);
        infoBox.setPrefHeight(INFO_BOX_HEIGHT);
        chipsRemainingLbl = new Label(String.valueOf(level1.getChipsRemaining()));
        timeManager = new TimeManager();

        VBox.setVgrow(levelLbl, Priority.NEVER);
        VBox.setVgrow(timeLbl, Priority.NEVER);
        VBox.setVgrow(countdownLbl, Priority.NEVER);
        VBox.setVgrow(chipsLbl, Priority.NEVER);
        infoBox.setSpacing(20);
        infoBox.setAlignment(Pos.TOP_CENTER);

        //Inventory Box
        inventoryTiles = new ImageView[8];
        inventoryGrid = new GridPane();
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                Tile.TileType tileType = Tile.TileType.FLOOR;
                Tile tile = new Tile(tileType, TILE_SIZE);
                tile.getImageView().setFitWidth(TILE_SIZE * 2);
                tile.getImageView().setFitHeight(TILE_SIZE * 2);
                GridPane.setRowIndex(tile.getImageView(), row);
                GridPane.setColumnIndex(tile.getImageView(), col);

                inventoryGrid.getChildren().add(tile.getImageView());
                inventoryTiles[row * 4 + col] = tile.getImageView();
            }
        }

        //Add Elements to Info Box
        infoBox.getChildren().addAll(levelLbl, levelNumberLbl, timeLbl, countdownLbl, chipsLbl, chipsRemainingLbl, inventoryGrid);
        startCountdown();
    }

    private void startCountdown() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long elapsedSeconds = (timeManager.getCurrentTime() - timeManager.getStartTime()) / 1_000_000_000;
                long secondsLeft = timerStart - elapsedSeconds;

                Platform.runLater(() -> {
                    if (secondsLeft > 0) {
                        countdownLbl.setText(String.valueOf(secondsLeft));
                    } else {
                        timer.cancel();
                    }
                });
            }
        }, 0, 1000);

    }

    public void showInfoTileMessage(String message) {
        infoBox.getChildren().clear();
        infoBox.setStyle("-fx-background-color: black;");
        Label infoMessage = new Label(message);
        infoMessage.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-text-alignment: center;");
        infoMessage.setWrapText(true);
        infoMessage.setAlignment(Pos.CENTER);
        infoBox.getChildren().add(infoMessage);
    }

    public void resetInfoDisplay() {

        infoBox.getChildren().clear();
        infoBox.setStyle(null);

        infoBox.getChildren().addAll(levelLbl, levelNumberLbl, timeLbl, countdownLbl, chipsLbl, chipsRemainingLbl, inventoryGrid);
    }

    public void updateChipsRemaining(int chipsRemaining, GameBoard level1) {
        chipsRemainingLbl.setText(String.valueOf(level1.getChipsRemaining()));
    }

    public void updateInventory(Player player) {
        for (int i = 0; i < inventoryTiles.length; i++) {
            Tile.TileType tileType = Tile.TileType.FLOOR;

            switch (i) {
                case 0:
                    if (player.hasKey(0)) {
                        tileType = Tile.TileType.BLUEKEY;
                    }
                    break;
                case 1:
                    if (player.hasKey(1)) {
                        tileType = Tile.TileType.GREENKEY;
                    }
                    break;
                case 2:
                    if (player.hasKey(2)) {
                        tileType = Tile.TileType.REDKEY;
                    }
                    break;
                case 3:
                    if (player.hasKey(3)) {
                        tileType = Tile.TileType.YELLOWKEY;
                    }
                    break;
                case 4:
                    if (player.hasBoot(0)) {
                        tileType = Tile.TileType.FIREBOOT;
                    }
                    break;
                case 5:
                    if (player.hasBoot(1)) {
                        tileType = Tile.TileType.ICEBOOT;
                    }
                    break;
                case 6:
                    if (player.hasBoot(2)) {
                        tileType = Tile.TileType.SKIDBOOT;
                    }
                    break;
                case 7:
                    if (player.hasBoot(3)) {
                        tileType = Tile.TileType.WATERBOOT;
                    }
                    break;
                default:
                    tileType = Tile.TileType.FLOOR;
            }

            inventoryTiles[i].setImage(Tile.loadImageForType(tileType));
        }
    }

    public VBox getInfoBox() {
        return infoBox;
    }

    public static int getINFO_BOX_WIDTH() {
        return INFO_BOX_WIDTH;
    }
}
