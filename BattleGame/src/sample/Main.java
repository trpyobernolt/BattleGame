package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class Main extends Application {
    Scene scene;
    GameControl control;

    @Override
    public void start(Stage primaryStage) {
        this.control = new GameControl(this);
        this.scene = new Scene(control, 750, 750, Paint.valueOf("Green"));
        primaryStage.setTitle("Battle Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void endGame(){
        scene.setOnMouseClicked(e -> this.reset());
        System.out.println("endGame was called");
    }

    public void reset() {
        scene.setOnMouseClicked(e -> {});
        System.out.println("Game was reset");
        control.reset();
    }
}
