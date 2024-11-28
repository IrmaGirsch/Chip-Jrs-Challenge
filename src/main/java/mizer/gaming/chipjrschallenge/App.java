package mizer.gaming.chipjrschallenge;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.canvas.GraphicsContext;

public class App extends Application {

    private static final int VIEW_WIDTH = 1000;
    private static final int VIEW_HEIGHT = 750;

    @Override
    public void start(Stage mainStage) {
        //Create Main Layout
        AnchorPane root = new AnchorPane();
        Scene level1Scene = new Scene(root, VIEW_WIDTH, VIEW_HEIGHT);

        //Load Theme Music
        String themeSong = "CHIP02.mp3";
        Media sound = new Media(new File(themeSong).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();
        });

        //Create Background Image
        Image backgroundImage = new Image("Circuit Board.png");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true
                )
        );
        root.setBackground(new Background(bgImage));

        // Create Info Bar
        InfoBox infoBox = new InfoBox();

        // Anchor Info Bar to the right
        root.getChildren().add(infoBox.getInfoBox());
        AnchorPane.setTopAnchor(infoBox.getInfoBox(), 10.0);
        AnchorPane.setRightAnchor(infoBox.getInfoBox(), 10.0);
        AnchorPane.setBottomAnchor(infoBox.getInfoBox(), 10.0);

        // Create Gameboard
        GameBoard level1 = new GameBoard();
        Canvas canvas = level1.getCanvas();

        root.getChildren().add(level1.getCanvas());

        AnchorPane.setTopAnchor(canvas, 150.0);
        AnchorPane.setRightAnchor(canvas, 425.0);
        

        
        mainStage.setTitle("Chip Jr's Challenge");
        mainStage.setScene(level1Scene);
        mainStage.show();
       
        level1.scrollToPlayer(VIEW_WIDTH, VIEW_HEIGHT);


        //Player Movement Controls
        level1Scene.setOnKeyPressed(event -> {
            Player player = level1.getPlayer();
            GameBoard gameBoard = level1;
            
            if (event.getCode() == KeyCode.LEFT) {
                player.move(-1, 0, gameBoard, infoBox);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                player.move(1, 0, gameBoard, infoBox);
            }
            if (event.getCode() == KeyCode.UP) {
                player.move(0, -1, gameBoard, infoBox);
            }
            if (event.getCode() == KeyCode.DOWN) {
                player.move(0, 1, gameBoard, infoBox);
            }
            level1.scrollToPlayer(VIEW_WIDTH, VIEW_HEIGHT);
        });

        //Stop Music When Exiting Window
        mainStage.setOnCloseRequest(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }

}
