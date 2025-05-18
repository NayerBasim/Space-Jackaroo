package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.card.Card;
import model.player.Player;
import view.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import engine.Game;

public class GameController {
	Game game;
	String name;
	
	public GameController() throws IOException{
		this.game = new Game();
		this.name = "";
	}
	
	
	public void SetName(Main app,Label message,TextField NameField){
		String userInput = NameField.getText();
		if(!NameField.getText().equals(""))
			try {
				app.showSceneTwo(userInput);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			message.setText("Input your name");
		
	}
	/*
	public void updateCards(Pane hand){
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Card> cards = players.get(0).getHand();
		hand.setHand(cards);
		
		
		
	}
	*/
	
}
