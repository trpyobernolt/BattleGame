package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class ScorePane extends HBox {
    private int playerOneScore;
    private int playerTwoScore;
    private Text score1;
    private Text score2;

    public ScorePane(Button reset) {
        super(70.0);
        init(reset);
    }

    public void init(Button reset){
        this.score1 = new Text("Player One's Score is: " + playerOneScore);
        score1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
        this.score2 = new Text("Player Two's Score is: " + playerTwoScore);
        score2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
        this.getChildren().addAll(score1, reset, score2);
    }

    public void addScore(int winner) {
        //Takes an int representing which player won, modifies their score, and displays it
        if(winner == 1) {
            playerOneScore += 1;
            score1.setText("Player One's Score is: " + playerOneScore);
        }
        if (winner == 2) {
            playerTwoScore += 1;
            score2.setText("Player Two's Score is: " + playerTwoScore);
        }
    }

    public void resetScores() {
        //sets scores to 0 and displays them
        playerOneScore = 0;
        playerTwoScore = 0;
        score1.setText("Player One's Score is: " + playerOneScore);
        score2.setText("Player Two's Score is: " + playerTwoScore);
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }
}
