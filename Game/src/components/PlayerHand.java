package components;



import java.util.ArrayList;

import cardImages.CardImage;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.card.Card;

public class PlayerHand extends HBox {

    public PlayerHand(ArrayList<Card> cards) {
    	ArrayList<CardView> cardViews= new ArrayList<>();
    	for(Card currCard : cards) {
    		Image currImage=new CardImage(currCard).getImage();
    		CardView currView=new CardView(currImage);
    		cardViews.add(currView);
    	}
    	
    	this.setSpacing(-40); // overlap
        for (CardView cardView : cardViews) {
            this.getChildren().add(cardView);
        }
    }
}
