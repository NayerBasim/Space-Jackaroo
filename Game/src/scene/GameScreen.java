package scene;

import java.io.IOException;
import java.util.ArrayList;

import components.Board;
import components.CPUPlayer1;
import components.CPUPlayer2;
import components.CPUPlayer3;
import components.FirePit;
import components.MainPlayer;
import components.PlayerHand;
import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.card.Card;
import view.Main;

public class GameScreen {
	  private Scene GameScene;
	  private static final int GRID_SIZE = 51;      // must be odd
	  private static final int TOTAL_TRACKS = 100;  // we’ll skip the center to get 100
	  private static final int CELL_SIZE = 12;      // px
	  private static final double CIRCLE_RADIUS = 4;
	  private final String PlayerName;
	  private final Main app;
	  public static  ArrayList<Object> cpus;
	  
	  GameController controller;
	public GameScreen(Main app, String PlayerName) throws IOException{
			TextArea descriptions = new TextArea();
		    GameController controller = new GameController(PlayerName,descriptions);
		    AnchorPane root = new AnchorPane();
			BorderPane holder = new BorderPane();
			AnchorPane.setTopAnchor(holder, 0.0);
			AnchorPane.setBottomAnchor(holder, 0.0);
			AnchorPane.setLeftAnchor(holder, 0.0);
			AnchorPane.setRightAnchor(holder, 0.0);
			this.PlayerName = PlayerName;
			this.app=app;
			cpus=new ArrayList<Object>();
			
			FirePit firePit=new FirePit();
			
			Board brd = new Board(controller);
	  	    StackPane grid =brd.boardRoot;
	  	    grid.setMaxSize(100, 100);
	  	    
	  	    grid.setRotate(180);
	  	    grid.setScaleX(-1);

	  	    
	        ArrayList<Card> playerHand=controller.getHands().get(0);
	        
	        PlayerHand playerHandPane=new PlayerHand();
	        
	        
	        for(Card card: playerHand) {
	        	ToggleButton currToggle= playerHandPane.addCard(card);
	        	
	        	currToggle.setOnMouseClicked(event -> {

	        		
	        		if(currToggle==playerHandPane.getLastSelected()) {
	        			currToggle.setSelected(false);
	        			currToggle.setPrefSize(70, 100);
	                    currToggle.setStyle(""); // Remove visual cue
	                    playerHandPane.setLastSelected(null);
	                    controller.deselect();
	        		}else {
	        			playerHandPane.setLastSelected(currToggle);
	        			for( Node n : playerHandPane.getChildren()){
		        			ToggleButton curr=(ToggleButton) n;
		        			curr.setStyle("");
		        			curr.setPrefSize(70, 100);
		        		}
	        			
	        			currToggle.setPrefSize(80, 120);
		        		currToggle.setStyle(
		        			    "-fx-border-color: black;" +
		        			    "-fx-border-width: 5;" +
		        			    "-fx-border-radius: 5;" +
		        			    "-fx-background-radius: 5;" +
		        			    "-fx-padding: 5;"
		        			);
		        		Card selected=(Card)currToggle.getUserData();
		        		Boolean alert = controller.selectCard(selected);

		                if(!alert) {app.showAlert("Invalid Card Exception", "Chosen card is not in your hand");}
	        			
	        		}

	        		
	            });
	    		
	    		currToggle.setOnMouseEntered(e -> {currToggle.setScaleX(1.1);
	    		currToggle.setTranslateX(10);
	    		currToggle.setTranslateY(-10);
	    		currToggle.setCursor(Cursor.HAND);
	    		DropShadow shadow = new DropShadow();
	    		currToggle.setEffect(shadow); 
	    		descriptions.appendText("                                      " + card.getName() + "\n" + card.getDescription());
	    		});
	    		
	    		
	    		
	    		currToggle.setOnMouseExited(e -> {currToggle.setScaleX(1.0);
	    		currToggle.setTranslateX(0);
	    		currToggle.setTranslateY(0);
	    		currToggle.setCursor(Cursor.DEFAULT);
	    		currToggle.setEffect(null);
	    		descriptions.clear();
	    		});

	    		
	            
	    	
	        }
	        
	        
	        
	        
	        
	        
	        Button play = new Button("Play");
	        
	    
	        MainPlayer mainplayer = new MainPlayer(app, PlayerName , playerHandPane);
	        HBox playerProf= mainplayer.getPlayer();
	        

	        VBox descriptionsbox = new VBox();
	        Label descriptiontital = new Label("Cards Description");
	        descriptionsbox.getChildren().add(descriptiontital);
	        descriptionsbox.getChildren().add(descriptions);
	        
	        descriptionsbox.setAlignment(Pos.CENTER);
	        descriptiontital.setFont(Font.font("System", FontWeight.BOLD, 14)); // 14 is font size
	        descriptionsbox.setLayoutX(playerProf.getLayoutX() +10);
	        descriptionsbox.setLayoutY(playerProf.getLayoutY()+ 500);
	        
	        descriptions.setMaxWidth(300);
	        descriptions.setMaxHeight(150);
	        descriptions.setWrapText(true);
	        descriptions.setEditable(false);
	        descriptions.setStyle("-fx-border-color: black;" +     // Outline color
	        					  "-fx-border-width: 2;" +         // Outline thickness
	        					  "-fx-border-radius: 5;" +        // Rounded corners (optional)
	        	    			  "-fx-background-radius: 5;" +
	        	    			  "-fx-background-color: transparent;" +   // removes background fill  
	        	    			  "-fx-background-insets: 0;" +
	        	    			  "-fx-background-radius: 0;" 
	        					  );
	      


	       
	    
	        
	        StackPane boardPit=new StackPane();
	        boardPit.getChildren().addAll(grid,firePit);
	        
	        
	        CPUPlayer1 cpu1=new CPUPlayer1(app,controller.getHands().get(1));
	        CPUPlayer2 cpu2=new CPUPlayer2(app,controller.getHands().get(2));
	        CPUPlayer3 cpu3=new CPUPlayer3(app,controller.getHands().get(3));
	        
	        cpus.add(cpu1);
	        cpus.add(cpu2);
	        cpus.add(cpu3);
	        
	        
	       
	        holder.setBottom(playerProf);
	        holder.setRight(cpu1.getPlayer());
	        holder.setTop(cpu2.getPlayer());
	        holder.setLeft(cpu3.getPlayer());
	        holder.setCenter(boardPit);
	        
	        holder.setAlignment(boardPit, Pos.CENTER);
	        holder.setAlignment(playerProf, Pos.CENTER);
	        holder.setAlignment(cpu1.getPlayer(), Pos.CENTER);
	        holder.setAlignment(cpu2.getPlayer(), Pos.CENTER);
	        holder.setAlignment(cpu3.getPlayer(), Pos.CENTER_RIGHT);
	       
	        
	        Image backgroundImage = new Image("gameBackground.png");
	        BackgroundImage bgImage = new BackgroundImage(
	            backgroundImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.DEFAULT,
	            new BackgroundSize(
	                100, 100, true, true, true, true
	            )
	        );
	        holder.setBackground(new Background(bgImage));
	        
	        root.getChildren().add(holder);
	        root.getChildren().add(play);
	        root.getChildren().add(descriptionsbox);
	        holder.setPadding(new Insets(0, 0, 150, 0));
	        
	        
	        
	        
	        
	        play.setLayoutX(playerProf.getLayoutX() +870);
	        play.setLayoutY(playerProf.getLayoutY()+ 610);
	       
	        
	        
	        
	        
	        play.setOnAction(event -> {
	        	try {
					controller.playGUI(brd.getSafeZones(), brd.getBaseZones(), brd.getTrackCircles(), playerHandPane, play, firePit, app, mainplayer, cpu1, cpu2, cpu3);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
	        });
	        

	        
//	        root.setOnKeyPressed(event->{
//	        	if (event.getCode() == KeyCode.ENTER) {
//	        		try {
//						controller.playGUI(brd.getSafeZones(), brd.getBaseZones(), brd.getTrackCircles(), playerHandPane, play, firePit, app, mainplayer, cpu1, cpu2, cpu3);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						
//					}
//	            }
//	
//	        });
	        
	        
	        
	        Scene scene = new Scene(root);
	        scene.setOnKeyPressed(event->{
	        	if (event.getCode() == KeyCode.F) {
	        		try {
						controller.playField(brd.getSafeZones(), brd.getBaseZones(), brd.getTrackCircles(), playerHandPane, play, firePit, app, mainplayer, cpu1, cpu2, cpu3);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
	            }
	
	        });
	   
	     
		     this.GameScene = scene;
		    




	}

	private Insets Insets(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private StackPane createCircle(int number) {
        Circle circle = new Circle(15);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.DARKBLUE);

        StackPane pane = new StackPane();
        pane.getChildren().add(circle);
        pane.setPrefSize(CELL_SIZE, CELL_SIZE);

        return pane;
    }

	
	public Scene getScene(){
		return this.GameScene;
	}
}