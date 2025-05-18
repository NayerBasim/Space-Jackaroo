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
	
	public MainPlayer(Main app,String PlayerName,ArrayList<Card> cards) {
		
		VBox section = new VBox();
		section.setAlignment(Pos.CENTER);
		HBox hand = new PlayerHand(cards);
		hand.setAlignment(Pos.CENTER);
		this.PlayerName=PlayerName;
		
		Image profilePic = new Image(getClass().getResource("profil.jpg").toExternalForm());
		ImageView Profile = new ImageView(profilePic);
		Profile.setFitWidth(100);
		Profile.setFitHeight(100);
		Profile.preserveRatioProperty();



		
//		Image card = new Image(getClass().getResource("game_cards.png").toExternalForm());
//		ImageView cardHolder1 = new ImageView(card);
//		ImageView cardHolder2 = new ImageView(card);
//		ImageView cardHolder3 = new ImageView(card);
//		ImageView cardHolder4 = new ImageView(card);
//		
//		cardHolder1.setFitWidth(50);
//		cardHolder1.setFitHeight(70);
//		cardHolder1.setPreserveRatio(true);
//		cardHolder2.setFitWidth(50);
//		cardHolder2.setFitHeight(70);
//		cardHolder2.setPreserveRatio(true);
//		cardHolder3.setFitWidth(50);
//		cardHolder3.setFitHeight(70);
//		cardHolder3.setPreserveRatio(true);
//		cardHolder4.setFitWidth(50);
//		cardHolder4.setFitHeight(70);
//		cardHolder4.setPreserveRatio(true);
		
		
		
		
		
		Label Name = new Label(PlayerName);
		Name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		
		
		
		
		section.getChildren().addAll(hand,Profile,Name);
		
		/*
		 * cards.setPadding(new Insets(5,5,5,5)); cards.setSpacing(5);
		 */
		Name.setPadding(new Insets(5,5,5,5));
		section.setMaxHeight(100);
		

		this.ProfileSection = section;
		
	}
	public VBox getPlayer(){
		return this.ProfileSection;
	}
}
