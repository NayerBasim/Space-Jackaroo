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

public class MainPlayer {

	private VBox ProfileSection;
	private final String PlayerName;
	
	public MainPlayer(Main app,String PlayerName, PlayerHand hand) {
		
		VBox section = new VBox();
		section.setAlignment(Pos.CENTER);
		hand.setAlignment(Pos.CENTER);
		this.PlayerName=PlayerName;
		
		Image profilePic = new Image(getClass().getResource("profil.jpg").toExternalForm());
		ImageView Profile = new ImageView(profilePic);
		Profile.setFitWidth(75);
		Profile.setFitHeight(75);
		Profile.preserveRatioProperty();



			
		
		Label Name = new Label(PlayerName);
		Name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		
		
		
		
		section.getChildren().addAll(hand,Profile,Name);
		

		Name.setPadding(new Insets(5,5,5,5));
		section.setMaxHeight(100);
		

		this.ProfileSection = section;
		
	}
	public VBox getPlayer(){
		return this.ProfileSection;
	}
}
