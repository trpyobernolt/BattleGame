package sample;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import java.util.Random;

public class GamePane extends VBox {
    private Deck deck;
    private Image cardBack = new Image("155.gif");
    private ImageView player1Deck = new ImageView(cardBack);
    private ImageView player2Deck = new ImageView(cardBack);
    private ImageView MainDeck = new ImageView(cardBack);
    private ScorePane scorePane;
    private Text displayedText = new Text("Tap the center deck to play!");
    private Text winnerText = new Text("This should never be shown");
    private Rectangle rect = new Rectangle(0, 40, 400, 250);
    PathTransition transition = new PathTransition();
    private Main main;
    Color[] s = {Color.SKYBLUE, Color.LIGHTGREEN, Color.RED, Color.HOTPINK, Color.DARKCYAN, Color.CORAL, Color.CRIMSON};
    Random random = new Random();

    public GamePane(ScorePane scorePane, Main main) {
        //Constructs the game pane with a new deck and sets correct opacity
        HBox textBox = new HBox();
        displayedText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        winnerText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        winnerText.setOpacity(0);
        textBox.getChildren().addAll(new Text("\t\t\t\t\t"), displayedText);
        this.scorePane = scorePane;
        this.main = main;
        this.deck = new Deck();
        HBox cardPiles = new HBox(90);
        this.MainDeck.setOnMouseClicked(e -> this.playRound());
        player1Deck.setOpacity(0);
        player2Deck.setOpacity(0);
        cardPiles.getChildren().addAll(player1Deck, MainDeck, player2Deck);
        VBox display = new VBox();
        display.getChildren().addAll(cardPiles, textBox, winnerText);
        this.getChildren().addAll(display);
        transition.setDuration(Duration.millis(7000));
        transition.setPath(rect);
        transition.setNode(winnerText);
    }

    public void playRound() {
        //Plays one round of the game and calculates if there is a winner
        if(deck.getCards().size() == 0) {
            return;
        }
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();
        int roundsRemaining = deck.getCards().size() / 2;
        player1Deck.setImage(card1.getImage());
        player2Deck.setImage(card2.getImage());
        player1Deck.setOpacity(100);
        player2Deck.setOpacity(100);
        if(deck.getCards().size() == 0) {
            MainDeck.setOnMouseClicked(e -> {});
            MainDeck.setOpacity(0);
            if(scorePane.getPlayerOneScore() > scorePane.getPlayerTwoScore()) {
                winnerText.setText("PLAYER ONE HAS WON!!!");
                displayedText.setText("Player One wins! Click anywhere to play again!");
            }
            else if(scorePane.getPlayerOneScore() < scorePane.getPlayerTwoScore()) {
                winnerText.setText("PLAYER TWO HAS WON!!!");
                displayedText.setText("Player Two wins! Click anywhere to play again!");
            }
            else if(scorePane.getPlayerOneScore() == scorePane.getPlayerTwoScore()) {
                winnerText.setText("NO VICTOR IS DECIDED");
                displayedText.setText("It's a tie! Click anywhere to play again!");
            }
            playAnimation();
            System.out.println("Game has ended -> deck size = 0");
            setOnMouseClicked(e -> main.endGame());
        }
        else if(card1.getBattleValue() > card2.getBattleValue()) {
            scorePane.addScore(1);
            displayedText.setText("Player One wins this round!\n   Rounds Remaining: " + roundsRemaining);
        }
        else if(card1.getBattleValue() < card2.getBattleValue()) {
            scorePane.addScore(2);
            displayedText.setText("Player Two wins this round\n   Rounds Remaining: " + roundsRemaining);
        }
    }

    public void resetDeck() {
        // resets the deck and sets images to starting position
        this.deck = new Deck();
        setOnMouseClicked(e -> {});
        this.MainDeck.setOnMouseClicked(e -> this.playRound());
        player1Deck.setOpacity(0);
        player2Deck.setOpacity(0);
        winnerText.setOpacity(0);
        MainDeck.setOpacity(100);
        displayedText.setText("Tap the center deck to play!");
    }

    private void playAnimation() {
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> { int index = random.nextInt(s.length);
        winnerText.setFill(s[index]);
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        winnerText.setOpacity(100);
        transition.play();
    }
}
