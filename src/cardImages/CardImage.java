package cardImages;

import java.util.ArrayList;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Deck;
import model.card.standard.Standard;
import model.card.standard.Suit;

public class CardImage {
	
	private final Card card;
	private Image cardImage;
	
	public CardImage(Card card) {
		this.card=card;
		Suit suit;
		int rank;
		if(card instanceof Standard) {
			rank=((Standard) card).getRank();
			suit=((Standard) card).getSuit();
		}else {
			if(card.getName().equalsIgnoreCase("marbleburner")) {
				rank=14;
				suit=null;
				cardImage=new Image(getClass().getResourceAsStream("MarbleBurner.png"));
			}else {
				rank=15;
				suit=null;
				cardImage=new Image(getClass().getResourceAsStream("MarbleSaver.png"));
				
			}
		}
		if(suit!=null) {
			cardImage=new Image(getClass().getResourceAsStream(suit.name() + "_" + rank + ".png"));
		}
		
		
	}
	
	public Image getImage() {
		return this.cardImage;
	}

}
