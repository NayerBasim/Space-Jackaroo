package components;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import model.card.Card;

public class CPUHand extends HBox {
	
	
public CPUHand(ArrayList<Card> cards) {
    	
    	this.setSpacing(-40); // overlap
    	
    	
    	for(Card currCard : cards) {
    		Image currImage=new Image(getClass().getResource("cards_back.png").toExternalForm());
    		CardView currView=new CardView(currImage);
    		
    		
    		
            this.getChildren().add(currView);
    	}
    	
    	


        
        
        
    }

}
