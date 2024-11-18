package mizer.gaming.chipjrschallenge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class App extends Application {

    private static final int VIEW_WIDTH = 1000;
    private static final int VIEW_HEIGHT = 750;

    @Override
    public void start(Stage Level1) {
        GameBoard level1 = new GameBoard();
        Scene scene = new Scene(level1.getPane(), VIEW_WIDTH, VIEW_HEIGHT);
        level1.scrollToPlayer(VIEW_WIDTH, VIEW_HEIGHT);

        scene.setOnKeyPressed(event -> {
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

        Level1.setTitle("Chip Jr's Challenge");
        Level1.setScene(scene);
        Level1.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
