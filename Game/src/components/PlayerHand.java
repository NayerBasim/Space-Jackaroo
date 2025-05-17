package components;



import java.util.ArrayList;

import cardImages.CardImage;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
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
    		
    		currView.setOnMouseEntered(e -> {currView.setScaleX(1.1);
    		currView.setTranslateX(10);
    		currView.setTranslateY(-10);
    		currView.setCursor(Cursor.HAND);
    		DropShadow shadow = new DropShadow();
    		currView.setEffect(shadow);
    		});
    		currView.setOnMouseExited(e -> {currView.setScaleX(1.0);
    		currView.setTranslateX(0);
    		currView.setTranslateY(0);
    		currView.setCursor(Cursor.DEFAULT);
    		currView.setEffect(null);
    		});

    		
            this.getChildren().add(currView);
    	}
    	
    	


        
        
        
    }
}
