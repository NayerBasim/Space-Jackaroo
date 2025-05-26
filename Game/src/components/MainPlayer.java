package components;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.card.Card;
import view.Main;

public class MainPlayer {

	private HBox ProfileSection;
	private final String PlayerName;
	VBox prof;
	PlayerHand hand;
	ImageView Profile;
	Label Name ;
	
	public MainPlayer(Main app,String PlayerName, PlayerHand hand) {
		
		HBox section = new HBox();
		this.hand=hand;
		section.setAlignment(Pos.CENTER);
		hand.setAlignment(Pos.CENTER);
		this.PlayerName=PlayerName;
		
		Image profilePic = new Image(getClass().getResource("profil.jpg").toExternalForm());
		Profile = new ImageView(profilePic);
		Profile.setFitWidth(75);
		Profile.setFitHeight(75);
		Profile.preserveRatioProperty();



			
		
		Name= new Label(PlayerName);
		Name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		Name.setStyle("-fx-text-fill: darkblue; -fx-effect: dropshadow(gaussian, grey, 2, 0.9, 0, 0); ; -fx-font-size: 24px; -fx-font-weight: bold; -fx-font-style: italic;");
		
		
		section.setSpacing(40);
		
		prof=new VBox();
		prof.getChildren().addAll(Profile,Name);
		
		section.getChildren().addAll(prof,hand);
		

		Name.setPadding(new Insets(5,5,5,5));
		section.setMaxHeight(100);
		

		this.ProfileSection = section;
		
	}
	public HBox getPlayer(){
		return this.ProfileSection;
	}
	
	
	public void setActive(boolean isActive) {
		

	    if (isActive) {
	    	this.Name.setStyle("-fx-text-fill: darkblue; -fx-effect: dropshadow(gaussian, grey, 2, 0.9, 0, 0); -fx-font-weight: bold; -fx-font-style: italic;");
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
