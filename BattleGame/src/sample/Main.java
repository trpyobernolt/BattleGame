package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class Main extends Application {
    Scene scene;
    GameControl control;

    @Override
    public void start(Stage primaryStage) {
        this.control = new GameControl(this);
        this.scene = new Scene(control, 750, 750, Paint.valueOf("Green"));
        //scene.setOnMouseClicked(e -> control.playRound());
        primaryStage.setTitle("Battle Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void endGame(){
        scene.setOnMouseClicked(e -> this.reset());
        System.out.println("Game has ended");
    }

    public void reset() {
        System.out.println("Mouse was instantly clicked");
        scene.setOnMouseClicked(null);
        control.reset();
        control.setBottom(new Text("Game has ended!"));
    }
}
