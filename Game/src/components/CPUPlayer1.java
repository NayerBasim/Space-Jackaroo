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
public class CPUPlayer1 {
	private HBox ProfileSection;
	
	public CPUPlayer1(Main app, ArrayList<Card> cards){
		HBox section = new HBox();
		section.setAlignment(Pos.CENTER);
		HBox hand = new CPUHand(cards);
		VBox ProfileDiv = new VBox();
		hand.setAlignment(Pos.CENTER);
		ProfileDiv.setAlignment(Pos.CENTER);
		
		Image profilePic = new Image(getClass().getResource("profil.jpg").toExternalForm());
		ImageView Profile = new ImageView(profilePic);
		Profile.setFitWidth(75);
		Profile.setFitHeight(75);
		Profile.preserveRatioProperty();
		
//		Image card = new Image(getClass().getResource("cards_back.png").toExternalForm());
//		ImageView cardHolder1 = new ImageView(card);
//		ImageView cardHolder2 = new ImageView(card);
//		ImageView cardHolder3 = new ImageView(card);
//		ImageView cardHolder4 = new ImageView(card);
//		
//		cardHolder1.setFitWidth(50);
//		cardHolder1.setFitHeight(70);
//		cardHolder1.setPreserveRatio(true);
//		cardHolder1.setRotate(-90);
//		cardHolder2.setFitWidth(50);
//		cardHolder2.setFitHeight(70);
//		cardHolder2.setPreserveRatio(true);
//		cardHolder2.setRotate(-90);
//		cardHolder3.setFitWidth(50);
//		cardHolder3.setFitHeight(70);
//		cardHolder3.setPreserveRatio(true);
//		cardHolder3.setRotate(-90);
//		cardHolder4.setFitWidth(50);
//		cardHolder4.setFitHeight(70);
//		cardHolder4.setPreserveRatio(true);
//		cardHolder4.setRotate(-90);
		
		hand.setRotate(90);
		
		Label Name = new Label("CPU 1");
		Name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		ProfileDiv.getChildren().addAll(Profile,Name);
		ProfileDiv.setPadding(new Insets(10,10,10,10));
		section.getChildren().addAll(hand,ProfileDiv);
//		cards.getChildren().addAll(cardHolder1, cardHolder2, cardHolder3, cardHolder4);
		
//		cards.setPadding(new Insets(5,5,5,5));
		ProfileDiv.setPadding(new Insets(10,10,10,10));
//		cards.setSpacing(5);
		Name.setPadding(new Insets(5,5,5,5));
		section.setMaxHeight(200);
		section.setPadding(new Insets(20));
	
		this.ProfileSection = section;
		
		
		
	}
	public HBox getPlayer(){
		return this.ProfileSection;
	}
}
