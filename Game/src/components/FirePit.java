package components;

import cardImages.CardImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.card.Card;

public class FirePit extends ImageView{
	
	
	public FirePit() {
		
		
        this.setFitWidth(70);
        this.setFitHeight(80);
        this.setPreserveRatio(true);
		
		
	}
	
	public void updateCard(Card card) {
		
		if(card==null) {this.setImage(null);}
		else{
		CardImage img = new CardImage(card);
		this.setImage(img.getImage());
		}
		
	}
	
	
	
	
	

}
