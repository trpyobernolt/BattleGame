package sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GamePane extends VBox {
    private Deck deck;
    private Image cardBack = new Image("155.gif");
    private ImageView player1Deck = new ImageView(cardBack);
    private ImageView player2Deck = new ImageView(cardBack);
    private ImageView imageViewDeck = new ImageView(cardBack);
    private ScorePane scorePane;
    private Text displayedText = new Text("Tap the center deck to play!");
    private Main main;

    public GamePane(ScorePane scorePane, Main main) {
        HBox textBox = new HBox();
        displayedText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        textBox.getChildren().addAll(new Text("\t\t\t\t\t"), displayedText);
        this.scorePane = scorePane;
        this.main = main;
        this.deck = new Deck();
        HBox cardPiles = new HBox(90);
        this.imageViewDeck.setOnMouseClicked(e -> this.playRound());
        player1Deck.setOpacity(0);
        player2Deck.setOpacity(0);
        cardPiles.getChildren().addAll(player1Deck, imageViewDeck, player2Deck);
        VBox display = new VBox();
        display.getChildren().addAll(cardPiles, textBox);
        this.getChildren().addAll(display);
    }

    public void playRound() {
        //Plays one round of the game
        if(deck.cards.size() == 0) {
            return;
        }
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();
        player1Deck.setImage(card1.getImage());
        player2Deck.setImage(card2.getImage());
        player1Deck.setOpacity(100);
        player2Deck.setOpacity(100);
        if(deck.cards.size() == 0) {
            imageViewDeck.setOpacity(0);
            if(scorePane.getPlayerOneScore() > scorePane.getPlayerTwoScore()) {
                displayedText.setText("Player One wins! Play again?");
            }
            else if(scorePane.getPlayerOneScore() < scorePane.getPlayerTwoScore()) {
                displayedText.setText("Player Two wins! Play again?");
            }
            main.endGame();
        }
        else if(card1.getBattleValue() > card2.getBattleValue()) {
            scorePane.addScore(1);
            displayedText.setText("Player One wins this round!");
        }
        else if(card1.getBattleValue() < card2.getBattleValue()) {
            scorePane.addScore(2);
            displayedText.setText("Player Two wins this round!");
        }
    }

    public void resetDeck() {
        // resets the deck and sets images to starting position
        this.deck = new Deck();
        player1Deck.setOpacity(0);
        player2Deck.setOpacity(0);
        imageViewDeck.setOpacity(100);
        displayedText.setText("Tap the center deck to play!");
    }
}
