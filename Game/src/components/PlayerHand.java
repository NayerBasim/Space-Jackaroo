package components;



import cardImages.CardImage;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.card.Card;

public class PlayerHand extends HBox {
	
	ToggleGroup handGroup;
	private Object getChildren;
	private ToggleButton lastSelected;

    public ToggleButton getLastSelected() {
		return lastSelected;
	}


	public void setLastSelected(ToggleButton lastSelected) {
		this.lastSelected = lastSelected;
	}


	public PlayerHand() {
    	
    	this.setSpacing(-40); // overlap  
    	handGroup=new ToggleGroup();
 
    }
    
    
    public ToggleButton addCard(Card card) {
    	Image currImage=new CardImage(card).getImage();
		CardView currView=new CardView(currImage);
		ToggleButton btn=new ToggleButton();
		btn.setPrefSize(70, 102); // Match the image size
		btn.setMaxSize(80, 110);
		btn.setMinSize(70, 102);
		btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		btn.setStyle("-fx-padding: 0;");
		btn.setGraphic(currView);
		btn.setToggleGroup(handGroup);
		btn.setUserData(card);
		this.getChildren().add(btn);
		return btn;
    }
    

}
