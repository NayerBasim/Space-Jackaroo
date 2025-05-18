package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Colour;
import model.card.Card;
import model.player.Marble;
import model.player.Player;
import view.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import engine.Game;
import engine.board.Cell;
import exception.CannotFieldException;
import exception.IllegalDestroyException;
import exception.InvalidCardException;
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
		public void selectMarble(ArrayList<ArrayList<Circle>> safeZones ,ArrayList<Circle> trackCircles, Circle circle){
			Marble m ;
			int indexTrack = -1;
			int indexSafe = -1;
			for(int i = 0 ; i < trackCircles.size(); i++){
				if(trackCircles.get(i).equals(circle)){
					indexTrack = i;
				}
			}
			for(int i = 0 ; i < safeZones.get(game.getcurrentPlayerIndex()).size(); i++){
				if(safeZones.get(i).equals(circle)){
					indexSafe = i;
				}
			}
			if(indexTrack != -1)
				m = this.game.board.getTrack().get(indexTrack).getMarble(); //to get the corrosponding marble if it is on the track
				
			else{
				m = this.game.board.getSafeZones().get(game.getcurrentPlayerIndex()).getCells().get(indexTrack).getMarble();//to get the corrosponding marble if it is on the player's safezone
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
	
	public ArrayList<ArrayList<Card>> getHands(){
		ArrayList<ArrayList<Card>> hands=new ArrayList<ArrayList<Card>>();
		
		ArrayList<Player> players=game.getPlayers();
		
		for(Player curr : players) {
			hands.add(curr.getHand());
		}
		
		
		return hands;
	}
	
	public boolean handleClick(Card card){
		game.deselectAll();
		
		try{
			
			game.selectCard(card);
			
		}catch(InvalidCardException e) { //can only access the player's hand aslan. whats the use?? 
			return false;
		}
		
		return true;
	}
	
	//public void filedmarble
		// takes as argument all the base cells and the track from the GUI and removes one , and adds it to the track ( updating it also in the game class )
		public void fieldMarble(ArrayList<ArrayList<Circle>> baseZones, ArrayList<Circle> trackCircles){
			try {
				game.fieldMarble();
				updateBase(baseZones);
				updateBoard(trackCircles);
				//baseZones.get(game.getcurrentPlayerIndex())
			} catch (CannotFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalDestroyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		// public void updateBoard
		// create method updateBoard , which takes the board circles and updates them according to the game instance
		public void updateBoard(ArrayList<Circle> trackCircles){
			ArrayList<Cell> trackCells = game.board.getTrack();
			// GREEN, RED, YELLOW, BLUE
			for(int i = 0 ; i < trackCells.size(); i++){
				if(trackCells.get(i).getMarble() != null){
					Colour c = trackCells.get(i).getMarble().getColour();
					Color GUIColor = translatecolortocolor(c);
					trackCircles.get(i).setFill(GUIColor);
				}
			}
		}
		
		public void updateBase(ArrayList<ArrayList<Circle>> baseZones){
			int homesize = game.getPlayers().get(game.getcurrentPlayerIndex()).getMarbles().size();
			for(int i=0; i<4;i++){
				if(i > homesize-1)
					baseZones.get(game.getcurrentPlayerIndex()).get(i).setVisible(false);
					
				}
				
			}
		
		//to translate engine color to the GUI color format
		public static Color translatecolortocolor(Colour playerColor){
				if(playerColor.equals(Colour.GREEN))
					return Color.GREEN;
				else if (playerColor.equals(Colour.RED))
					return Color.RED;
				else if (playerColor.equals(Colour.YELLOW))
					return Color.YELLOW;
				else if (playerColor.equals(Colour.BLUE))
					return Color.BLUE;
			return Color.BLACK;
		}
		
		public ArrayList<Colour> getPlayerColours(){
			ArrayList<Player> players= game.getPlayers();
			ArrayList<Colour> playerColors=new ArrayList<Colour>();
			for(Player player : players){
				Colour currColor=player.getColour();
				playerColors.add(currColor);
			}
			
			return playerColors;
		}
	
	

	
}