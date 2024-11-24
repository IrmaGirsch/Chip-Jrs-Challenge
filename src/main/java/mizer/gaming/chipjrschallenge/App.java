package mizer.gaming.chipjrschallenge;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class App extends Application {

    private static final int VIEW_WIDTH = 1000;
    private static final int VIEW_HEIGHT = 750;
    private static final int INFO_BAR_WIDTH = 200;

    @Override
    public void start(Stage mainStage) {
        //Create Main Layout
        AnchorPane main = new AnchorPane();
        Scene level1Scene = new Scene(main, VIEW_WIDTH, VIEW_HEIGHT);

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
        main.setBackground(new Background(bgImage));

        // Create Gameboard
        GameBoard level1 = new GameBoard();
        main.getChildren().add(level1.getPane()); // Add gameboard to AnchorPane
        AnchorPane.setTopAnchor(level1.getPane(), 10.0);
        AnchorPane.setLeftAnchor(level1.getPane(),10.0);

        // Create Info Bar
        VBox infoBar = new VBox(10);
        infoBar.setPrefWidth(INFO_BAR_WIDTH);
        infoBar.setStyle("-fx-background-color: #cccccc;");
        infoBar.setPadding(new Insets(20));
        infoBar.getChildren().addAll(
                // Test children for the info bar
                new javafx.scene.control.Label("Player Info"),
                new javafx.scene.control.Label("Health: 100"),
                new javafx.scene.control.Label("Score: 0")
        );

        // Anchor Info Bar to the right
        main.getChildren().add(infoBar);
        AnchorPane.setTopAnchor(infoBar, 10.0);
        AnchorPane.setRightAnchor(infoBar, 10.0);
        AnchorPane.setBottomAnchor(infoBar, 10.0);

        mainStage.setTitle("Chip Jr's Challenge");
        mainStage.setScene(level1Scene);
        mainStage.show();

        level1.scrollToPlayer(VIEW_WIDTH, VIEW_HEIGHT);

        //Player Movement Controls
        level1Scene.setOnKeyPressed(event -> {
            Player player = level1.getPlayer();
            if (event.getCode() == KeyCode.LEFT) {
                player.move(-1, 0);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                player.move(1, 0);
            }
            if (event.getCode() == KeyCode.UP) {
                player.move(0, -1);
            }
            if (event.getCode() == KeyCode.DOWN) {
                player.move(0, 1);
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
