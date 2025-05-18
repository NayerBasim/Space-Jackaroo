package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.card.Card;
import model.player.Marble;
import model.player.Player;
import view.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import engine.Game;
import exception.InvalidMarbleException;


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
	//public void updateCards
	// create method updateCards , which takes the hand of the player in the GUI and changes its value to match with the value in game
	
	/*
	public void updateCards(Pane hand){
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Card> cards = players.get(0).getHand();
		hand.setHand(cards);	
	}
	*/
	
	// public void updateBoard
	// create method updateBoard , which takes the board circles and updates them according to the game instance
	
	
	//public void selectMarble
	// create method selectMarble, which takes as the parameter an index number and the circle clicked and sets the selected marble in the game 
	public void selectMarble(int indexTrack,int indexSafe, Circle circle){
		Marble m ;
		if(indexTrack != -1)
			m = this.game.board.getTrack().get(indexTrack).getMarble(); //to get the corrosponding marble if it is on the track
		else{
			m = this.game.board.getSafeZones().get(0).getCells().get(indexTrack).getMarble();//to get the corrosponding marble if it is on the player's safezone
		}
		if(m!= null){
			try {
				game.selectMarble(m);
			} catch (InvalidMarbleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	//public void selectCard
	// create method selectMarble, which takes as the parameter an index number and the Card clicked and sets the selected marble in the game 
	
	
	//
	
}
