package components;



import java.util.ArrayList;

import cardImages.CardImage;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.card.Card;

public class PlayerHand extends HBox {

    public PlayerHand(ArrayList<Card> cards) {
    	
    	this.setSpacing(-40); // overlap
    	
    	
    	for(Card currCard : cards) {
    		Image currImage=new CardImage(currCard).getImage();
    		CardView currView=new CardView(currImage);
    		
    		currView.setOnMouseClicked(event -> {
                System.out.println("Card clicked: " + currCard); // Debug
            });
    		
    		currView.setOnMouseEntered(e -> currView.setScaleX(1.1));
    		currView.setOnMouseExited(e -> currView.setScaleX(1.0));

    		
            this.getChildren().add(currView);
    	}
    	
    	


        
        
        
    }
}
