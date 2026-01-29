package components;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.card.Card;
import view.Main;


public class CPUPlayer3 {

	private HBox ProfileSection;
	public CPUHand hand;
	VBox ProfileDiv;
	Label Name;
	
	public CPUPlayer3(Main app, ArrayList<Card> cards){
		HBox section = new HBox();
		section.setAlignment(Pos.CENTER);
		hand = new CPUHand(cards);
		ProfileDiv= new VBox();
		hand.setAlignment(Pos.CENTER);
		ProfileDiv.setAlignment(Pos.CENTER);
		
		Image profilePic = new Image(getClass().getResource("alien3.png").toExternalForm());
		ImageView Profile = new ImageView(profilePic);
		Profile.setFitWidth(75);
		Profile.setFitHeight(75);
		Profile.preserveRatioProperty();
		
		
		
		
		hand.setRotate(-90);
		
		Name = new Label("CPU 3");
		Name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		ProfileDiv.getChildren().addAll(Profile,Name);
		section.getChildren().addAll(ProfileDiv,hand);
		
		ProfileDiv.setPadding(new Insets(10,10,10,10));
		Name.setPadding(new Insets(5,5,5,5));
		section.setMaxHeight(200);
		section.setPadding(new Insets(20));
	
		this.ProfileSection = section;
		
		
		
	}
	public HBox getPlayer(){
		return this.ProfileSection;
	}
	
	public void updateHand(ArrayList<Card> hands) {
		this.hand.updatCPUHand(hands);
	}
	
	
	public void setActive(boolean isActive) {

		if (isActive) {
	    	this.Name.setStyle("-fx-text-fill: darkblue; -fx-effect: dropshadow(gaussian, grey, 2, 0.9, 0, 0); ;-fx-font-weight: bold; -fx-font-style: italic;");
	    } else {
	        this.Name.setStyle(""); // Remove border
	    }
	}
	
public void setNext(boolean isNext) {
		

	    if (isNext) {
	    	this.Name.setStyle("-fx-text-fill: mediumspringgreen; -fx-effect: dropshadow(gaussian, grey, 2, 0.9, 0, 0); -fx-font-weight: bold; -fx-font-style: italic;");
	    } else {
	        this.Name.setStyle(""); // Remove border
	    }

	}

		
	
}
