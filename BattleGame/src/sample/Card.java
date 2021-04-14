package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Card extends Pane {
    private int battleValue;
    private Image image;

    public Card(int value) {
        //Creates a card with a battle value and a corresponding image
        this.battleValue = value % 13;
        if(value % 13 == 0) {
            this.battleValue = 13;
        }
        Integer[] ints = {1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> singles = new ArrayList<Integer>(Arrays.asList(ints));
        String imagePath;
        if(singles.contains(value)) {
            imagePath = ("10" + value + ".gif");
        }
        else {
            imagePath = ("1" + value + ".gif");
        }
        this.image = new Image(imagePath);
    }

    public Image getImage() {
        return this.image;
    }

    public int getBattleValue() {
        return battleValue;
    }
}
