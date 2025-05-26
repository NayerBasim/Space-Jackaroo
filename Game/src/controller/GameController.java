package controller;

import java.io.IOException;
import java.util.ArrayList;

import components.CPUPlayer1;
import components.CPUPlayer2;
import components.CPUPlayer3;
import components.FirePit;
import components.MainPlayer;
import components.PlayerHand;
import engine.Game;
import engine.board.Cell;
import engine.board.SafeZone;
import exception.CannotFieldException;
import exception.GameException;
import exception.IllegalDestroyException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import exception.SplitOutOfRangeException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Colour;
import model.card.Card;
import model.card.standard.Ace;
import model.card.standard.King;
import model.card.standard.Seven;
import model.player.Marble;
import model.player.Player;
import view.Main;


public class GameController {
	Game game;
	String name;
	int splitting;
	TextArea descriptions;
	

	public GameController(String name, TextArea descriptions) throws IOException{
		
		this.game = new Game(name);
		this.descriptions=descriptions;


	}
	
	
	public void SetName(Main app,Label message,TextField NameField){
		String userInput = NameField.getText();
		if(!NameField.getText().equals(""))
			try {
				app.showSceneTwo(userInput);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Main.showAlert("Exception","Exception in setting name: "+ e.getMessage());
			}
		else
			message.setText("Input your name");
		
	}
	//public void updateCards
	// create method updateCards , which takes the hand of the player in the GUI and changes its value to match with the value in game
	
	
	public void updateCards(PlayerHand hand){
		ArrayList<Card> cards = game.getPlayers().get(0).getHand();
		hand.getChildren().clear();
		for(Card card: cards) {
        	ToggleButton currToggle= hand.addCard(card);
        	
        	currToggle.setOnMouseClicked(event -> {
        		
        		        		
        		
        		if(currToggle==hand.getLastSelected()) {
        			currToggle.setSelected(false);
        			currToggle.setPrefSize(70, 102);
                    currToggle.setStyle(""); // Remove visual cue
                    hand.setLastSelected(null);
                    this.deselect();
        		}else {
        			
        			hand.setLastSelected(currToggle);
        			for( Node n : hand.getChildren()){
	        			ToggleButton curr=(ToggleButton) n;
	        			curr.setStyle("");
	        			curr.setPrefSize(70, 102);
	        		}
        			
        			currToggle.setPrefSize(80, 120);
	        		currToggle.setStyle(
	        			    "-fx-border-color: black;" +
	        			    "-fx-border-width: 3;" +
	        			    "-fx-border-radius: 5;" +
	        			    "-fx-background-radius: 5;" +
	        			    "-fx-padding: 5;"
	        			);
	        		Card selected=(Card)currToggle.getUserData();
	        		Boolean alert = this.selectCard(selected);

	                if(!alert) {Main.showAlert("Invalid Card Exception", "Chosen card is not in your hand");}
        			
        		}
        		
        		
        		
            });
    		
    		currToggle.setOnMouseEntered(e -> {currToggle.setScaleX(1.1);
    		currToggle.setTranslateX(10);
    		currToggle.setTranslateY(-10);
    		currToggle.setCursor(Cursor.HAND);
    		DropShadow shadow = new DropShadow();
    		currToggle.setEffect(shadow);
    		this.descriptions.appendText("                                      " + card.getName() + "\n" + card.getDescription());
    		});
    		
    		
    		
    		currToggle.setOnMouseExited(e -> {currToggle.setScaleX(1.0);
    		currToggle.setTranslateX(0);
    		currToggle.setTranslateY(0);
    		currToggle.setCursor(Cursor.DEFAULT);
    		currToggle.setEffect(null);
    		this.descriptions.clear();
    		});

    		
            
    	
        }
		
	}
	
	
	public void updateCardsCPU(Object cpu) {
		if(cpu instanceof CPUPlayer1) {
			CPUPlayer1 cpu1=(CPUPlayer1)cpu;
			ArrayList<Card> handP=game.getPlayers().get(1).getHand();
			cpu1.updateHand(handP);
			
			
		}else if(cpu instanceof CPUPlayer2) {
			CPUPlayer2 cpu2=(CPUPlayer2)cpu;
			ArrayList<Card> handP=game.getPlayers().get(2).getHand();
			cpu2.updateHand(handP);
			
			
			
		}else if(cpu instanceof CPUPlayer3) {
			CPUPlayer3 cpu3=(CPUPlayer3)cpu;
			ArrayList<Card> handP=game.getPlayers().get(3).getHand();
			cpu3.updateHand(handP);
		}
		
	}
	
	

	
	//public void selectMarble
		// create method selectMarble, which takes as the parameter an index number and the circle clicked and sets the selected marble in the game 
		public void selectMarble(ArrayList<ArrayList<Circle>> safeZones ,ArrayList<Circle> trackCircles, Circle circle){
			Marble m = null;
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
				
			else if(indexSafe != -1){
				m = this.game.board.getSafeZones().get(game.getcurrentPlayerIndex()).getCells().get(indexSafe).getMarble();//to get the corrosponding marble if it is on the player's safezone
			}
			if(m!= null){
				try {
					if(game.getPlayers().get(0).getSelectedMarbles().contains(m)){
						game.getPlayers().get(0).getSelectedMarbles().remove(m);
					}else{
						game.selectMarble(m);
					}
					
					updateBoardSelection(trackCircles, safeZones);
				} catch (InvalidMarbleException e) {
					Main.showAlert("Exception","Invalid Marble Exception: " + e.getMessage());
				}
			}
			
		}
		
	
	
	
	//public void selectCard
	// create method selectMarble, which takes as the parameter the Card clicked and sets the selected card in the game 
		public boolean selectCard(Card card){
			
			
			try{
				game.selectCard(card);
				
			}catch(InvalidCardException e) { //can only access the player's hand aslan. whats the use?? 
				return false;
			}
			
			return true;
		}
		
	
	//
	
	public ArrayList<ArrayList<Card>> getHands(){
		ArrayList<ArrayList<Card>> hands=new ArrayList<ArrayList<Card>>();
		
		ArrayList<Player> players=game.getPlayers();
		
		for(Player curr : players) {
			hands.add(curr.getHand());
		}
		
		
		return hands;
	}
	

	//public void fieldmarble
		// takes as argument all the base cells and the track from the GUI and removes one , and adds it to the track ( updating it also in the game class )
		public void fieldMarble(ArrayList<ArrayList<Circle>> baseZones, ArrayList<Circle> trackCircles){
			try {
				game.fieldMarble();
				updateBase(baseZones);
				updateBoard(trackCircles);
				//baseZones.get(game.getcurrentPlayerIndex())
			} catch (CannotFieldException e) {
				// TODO Auto-generated catch block
				Main.showAlert("Exception","Cannot Field Exception: " +  e.getMessage());
			} catch (IllegalDestroyException e) {
				// TODO Auto-generated catch block
				Main.showAlert("Exception","Illegal Destroy Exception: " +  e.getMessage());
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
				}else{
					trackCircles.get(i).setFill(Color.GRAY);
				}
			}
		}
		// public void updateBoardSelction
		// create method updateBoardSelection , which takes the board circles and updates them according to the selected marbles
		public void updateBoardSelection(ArrayList<Circle> trackCircles, ArrayList<ArrayList<Circle>> safeZones){
			ArrayList<Cell> trackCells = game.board.getTrack();
		    ArrayList<SafeZone> safeZonesCells = game.board.getSafeZones() ;
			ArrayList<Marble> playerMarbles = game.getPlayers().get(0).getSelectedMarbles();
			// GREEN, RED, YELLOW, BLUE
			
			for(int i = 0 ; i < trackCells.size(); i++){
				if(trackCells.get(i).getMarble() != null && playerMarbles.contains(trackCells.get(i).getMarble())){
//					trackCircles.get(i).setStrokeWidth(5);
					trackCircles.get(i).setStroke(Color.BLACK);
					trackCircles.get(i).setStrokeWidth(2);
					trackCircles.get(i).setRadius(4);
				}else{
					trackCircles.get(i).setStroke(null);
					trackCircles.get(i).setRadius(6);
				}
			}
			for(int i = 0 ; i < 4; i++){
				for(int v = 0 ; v < safeZones.get(i).size();v++){
					if(safeZonesCells.get(i).getCells().get(v).getMarble() != null && playerMarbles.contains(trackCells.get(i).getMarble())){
//						safeZones.get(i).get(v).setStrokeWidth(5);
						safeZones.get(i).get(v).setStroke(Color.BLACK);
						safeZones.get(i).get(v).setStrokeWidth(2);
						safeZones.get(i).get(v).setRadius(4);
					}else{
					safeZones.get(i).get(v).setStroke(null);
					safeZones.get(i).get(v).setRadius(6);
					}
				}
			}
		}
		
		public void updateBase(ArrayList<ArrayList<Circle>> baseZones){
			for(int homeCount = 0 ; homeCount < 4 ; homeCount ++){
				int homesize = game.getPlayers().get(homeCount).getMarbles().size();
				
				for(int i=0; i < 4;i++){
					baseZones.get(homeCount).get(i).setVisible(true);
					if(i > homesize-1)
						baseZones.get(homeCount).get(i).setVisible(false);
						
					}
			}
				
			}
		
		public void updateFirepit(FirePit firepit, Card card){
			if(firepit != null)
				firepit.updateCard(card);
		}
		
		public void updateSafe(ArrayList<ArrayList<Circle>> safeZones){
			for(int safeCount = 0 ; safeCount < 4 ; safeCount ++){
				ArrayList<Cell> cells = game.board.getSafeZones().get(safeCount).getCells();
				Colour c = game.board.getSafeZones().get(safeCount).getColour();
				for(int i=0; i<4;i++){
					if(cells.get(i).getMarble() != null){
						safeZones.get(safeCount).get(i).setFill(translatecolortocolor(c));
					}else{
						safeZones.get(safeCount).get(i).setFill(Color.GRAY);
					}
					
				}
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
		
		//public void playGUI
		// responsible for playing a players turn
		public void playGUI(
		        ArrayList<ArrayList<Circle>> safeZones,
		        ArrayList<ArrayList<Circle>> baseZones,
		        ArrayList<Circle> trackCircles,
		        PlayerHand hand, Button play, FirePit firepit, Main app,
		        MainPlayer player, CPUPlayer1 cpu1, CPUPlayer2 cpu2, CPUPlayer3 cpu3) {

			
			boolean nullSelected=false;
		    if (game.canPlayTurn()) {
		        
		    	try {
		    		if(game.getPlayers().get(0).getSelectedCard()==null) {
		    			if(!game.canPlayTurn()) {return;}
		    			Main.showAlert("No card selected", "Please select a card");
		    			nullSelected=true;
		    			return;
		    		}
			            if (game.getPlayers().get(0).getSelectedCard() instanceof Seven
			                    && game.getPlayers().get(0).getSelectedMarbles().size() == 2) {
			                getSplittingInput("Input Splitting", "Input the value of splitting for the first marble");
			            }
		    		
		            game.playPlayerTurn();
		            
		            if (game.checkWin() != null) {
		                Main.showAlert("YOU WINNN!!!!","YOU HAVE WON THE GAME!!! \n YOUR COLOUR: " + game.checkWin().toString());
		            }
		        
		            
		            
		            
		        }catch (Exception e) {
		            
		            Main.showAlert("Exception", "Exception when playing player turn: " + e.getMessage());
		        } finally {
		        	
		        	if(!nullSelected) {
		        		
		        		
		        		if(game.isDestroyedByTrap()==true) {Main.showAlert("Trap cell", "Your cell was destroyed");}
		        		
			        	game.endPlayerTurn();  // Only if turn was actually played
			        	
			        	updateBoardSelection(trackCircles, baseZones);
			        	updateBase(baseZones);
			            updateSafe(safeZones);
			            updateBoard(trackCircles);
			            if(game.getFirePit().isEmpty()) {updateFirepit(firepit, null);}else {
			      		updateFirepit(firepit, game.getFirePit().get(game.getFirePit().size()-1));
			            }
			      		
			            player.setActive(false);
			            cpu1.setNext(false);
			            cpu1.setActive(true);
			            cpu2.setNext(true);
			            
			            updateCards(hand);
		                updateCardsCPU(cpu1);
		                updateCardsCPU(cpu2);
		                updateCardsCPU(cpu3);
		                
			            play.setDisable(true);
		        	}
		        	
		        }
		    } else {
		        Main.showAlert("Can't play", "Your turn's been skipped");
		        game.skipPlayerTurn();
		        updateBoardSelection(trackCircles, baseZones);
		    }

		    final int[] i = {0};
		    Timeline timeline = new Timeline();

		    KeyFrame frame = new KeyFrame(Duration.seconds(2), event -> {
		    	
		        if (i[0] < 3) {
		            
		                if (game.canPlayTurn()) {
		                	try {
		                		
		                		
		                    game.playPlayerTurn();

		                    if (game.checkWin() != null) {
		                    	Main.showAlert("YOU LOST :(","CPU HAS WON THE GAME!!! \n CPU COLOUR: " + game.checkWin().toString());
		                    }
		                	} catch (Exception e) {
				                
				                Main.showAlert("Exception", "Exception when playing CPU turn: " + e.getMessage());
				            }finally {
				            	
				            	if(game.isDestroyedByTrap()==true) {Main.showAlert("Trap cell", "CPU " + (i[0] + 1) + "'s marble was destoyed");}
				            	
				            	game.endPlayerTurn(); // Only called if turn was played
				            	
				            	updateBase(baseZones);
			                    updateSafe(safeZones);
			                    updateBoard(trackCircles);         	
			                    if(game.getFirePit().isEmpty()) {updateFirepit(firepit, null);}else {
						      		updateFirepit(firepit, game.getFirePit().get(game.getFirePit().size()-1));
						            }
				            	
				                updateCards(hand);
				                updateCardsCPU(cpu1);
				                updateCardsCPU(cpu2);
				                updateCardsCPU(cpu3);

				                switch (i[0]) {
				                    case 0:
				                        cpu1.setActive(false);
				                        cpu2.setNext(false);
				                        cpu2.setActive(true);
				                        cpu3.setNext(true);
				                        break;
				                    case 1:
				                        cpu2.setActive(false);
				                        cpu3.setNext(false);
				                        cpu3.setActive(true);
				                        player.setNext(true);
				                        break;
				                    case 2:
				                        cpu3.setActive(false);
				                        player.setNext(false);
				                        player.setActive(true);
				                        cpu1.setNext(true);
				                        break;
				                }

				                i[0]++;
				                System.out.println("CPU played");

				                
				            }

		                } else {
		                	
		                    Main.showAlert("Can't play", "CPU " + (i[0] + 1) + " had its turn skipped");
		                    game.skipPlayerTurn();
		                    i[0]++;
		                    
		                }
		             
		        }
		        if (i[0] >= 3) {
		        	
		        	updateBase(baseZones);
                    updateSafe(safeZones);
                    updateBoard(trackCircles);         	
                    if(game.getFirePit().isEmpty()) {updateFirepit(firepit, null);}else {
			      		updateFirepit(firepit, game.getFirePit().get(game.getFirePit().size()-1));
			            }
	            	
	                updateCards(hand);
	                updateCardsCPU(cpu1);
	                updateCardsCPU(cpu2);
	                updateCardsCPU(cpu3);
                	
                    cpu3.setActive(false);
                    player.setNext(false);
                    player.setActive(true);
                    cpu1.setNext(true);
                    play.setDisable(false);
                    game.deselectAll();
                    timeline.stop();
                }
		        
		    });

		    timeline.getKeyFrames().add(frame);
		    timeline.setCycleCount(Animation.INDEFINITE);
		    timeline.play();
		}




		
		//public void setSplitting
		// setSplitting takes in the input from the UI and sets the game split to that
		public void setSplitting(int s){

			try {
				game.editSplitDistance(s);
			} catch (SplitOutOfRangeException e) {
				Main.showAlert("Exception","SplitOutOfRange Exception: " +  e.getMessage());
			}
		}
		
		public void getSplittingInput(String title, String message) {
		    // Create modal stage
		    Stage alertStage = new Stage();
		    alertStage.setTitle(title);
		    alertStage.initModality(Modality.APPLICATION_MODAL); // Make it modal

		    // Message label
		    Label label = new Label(message);

		    // Text field for input
		    TextField inputField = new TextField();
		    inputField.setPromptText("Enter a number");

		    // Continue button
		    Button closeButton = new Button("Continue");
		    closeButton.setOnAction(event -> {
		        try {
		            int value = Integer.parseInt(inputField.getText());
		            this.setSplitting(value);
		            alertStage.close(); // Close only if input is valid
		        } catch (NumberFormatException e) {
		            Main.showAlert("Invalid input", "Please enter a valid number.");
		        }
		    });

		    VBox layout = new VBox(10);
		    layout.setAlignment(Pos.CENTER);
		    layout.setPadding(new Insets(10));
		    layout.getChildren().addAll(label, inputField, closeButton);

		    Scene scene = new Scene(layout, 300, 150);
		    alertStage.setScene(scene);

		    alertStage.showAndWait(); // <-- This is what blocks until input is provided
		}
		
		public void deselect() {
			game.deselectAll();
		}
		
		
		public void playField(
		        ArrayList<ArrayList<Circle>> safeZones,
		        ArrayList<ArrayList<Circle>> baseZones,
		        ArrayList<Circle> trackCircles,
		        PlayerHand hand, Button play, FirePit firepit, Main app,
		        MainPlayer player, CPUPlayer1 cpu1, CPUPlayer2 cpu2, CPUPlayer3 cpu3)
		{
			
			
			if(game.getcurrentPlayerIndex() == 0){
				ArrayList<Card> cards = game.getPlayers().get(0).getHand();
				for(int i = 0 ; i < cards.size(); i++){
					if(cards.get(i) instanceof Ace || cards.get(i) instanceof King){
						
						try {

							game.selectCard(cards.get(i));
							game.getPlayers().get(0).getSelectedMarbles().clear();
							playGUI(
							        safeZones,
							        baseZones,
							        trackCircles,
							        hand, play, firepit, app,
							        player, cpu1, cpu2, cpu3);

							
						} catch (InvalidCardException e) {
							// TODO Auto-generated catch block
							
						} catch (GameException e) {
							// TODO Auto-generated catch block
							
						}
						
						return;
						
					}
					
				}
				Main.showAlert("Problem", "Cannot field");
			}
		}

		
	
	

	
}