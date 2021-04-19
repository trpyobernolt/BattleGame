package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameControl extends BorderPane {
    private ScorePane scores;
    private GamePane game;

    public GameControl(Main main) {
        super();
        this.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        Button reset = new Button("Reset");
        reset.setOnAction(e -> reset());
        this.scores = new ScorePane(reset);
        this.game = new GamePane(scores, main);
        Pane filler = new Pane();
        filler.setPrefWidth(125.0);
        //Filler pane just makes it look pretty
        this.setTop(scores);
        this.setCenter(game);
        this.setLeft(filler);
    }

    public void reset() {
        //resets a game
        this.scores.resetScores();
        this.game.resetDeck();
        setOnMouseClicked(e -> {});
        this.scores.setOnMouseClicked(e -> {});
        this.game.setOnMouseClicked(e -> {});
    }

}
