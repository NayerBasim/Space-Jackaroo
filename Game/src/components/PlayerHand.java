package components;



import java.util.ArrayList;

import cardImages.CardImage;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import model.card.Card;

public class PlayerHand extends Pane {

    public PlayerHand(ArrayList<Card> cards) {
    	ArrayList<CardView> cardViews= new ArrayList<>();
    	for(int i=0; i<cards.size() ; i++) {
    		Card currCard=cards.get(i);
    		Image currImage=new CardImage(currCard).getImage();
    		CardView currView=new CardView(currImage);
    		cardViews.add(currView);
    	}
    	
        for (int i = 0; i < cardViews.size(); i++) {
            CardView card = cardViews.get(i);
            card.setLayoutX(i * 65); // overlap
            this.getChildren().add(card);
        }
    }
}
