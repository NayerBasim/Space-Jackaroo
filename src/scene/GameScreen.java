package scene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import components.Board;
import components.CPUPlayer1;
import components.CPUPlayer2;
import components.CPUPlayer3;
import components.FirePit;
import components.MainPlayer;
import components.PlayerHand;
import components.SpaceButton;
import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
	  private MediaPlayer mediaPlayer;

	  
	  GameController controller;
	public GameScreen(Main app, String PlayerName) throws IOException{
			Label descriptions = new Label();
	        Label descriptiontital = new Label("Cards Description");
		    GameController controller = new GameController(PlayerName,descriptions);
//		    AnchorPane root = new AnchorPane();
			Pane holder = new Pane();
//			AnchorPane.setTopAnchor(holder, 0.0);
//			AnchorPane.setBottomAnchor(holder, 0.0);
//			AnchorPane.setLeftAnchor(holder, 0.0);
//			AnchorPane.setRightAnchor(holder, 0.0);
			this.PlayerName = PlayerName;
			this.app=app;
			cpus=new ArrayList<Object>();
			
			
			try {
		        URL resource = getClass().getResource("/Signal To Noise (CC-BY).mp3");
		        if (resource != null) {
		            Media media = new Media(resource.toExternalForm());
		           mediaPlayer = new MediaPlayer(media);
		            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		            mediaPlayer.setVolume(0.3); // Adjust volume as needed
		            mediaPlayer.play();
		        } else {
		            System.err.println("Could not find soundtrack file.");
		        }
		    } catch (Exception e) {
		        System.err.println("Failed to play background music: " + e.getMessage());
		    }
			
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

		                if(!alert) {try {
							app.showError("Invalid Card Exception", "Chosen card is not in your hand");
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
	        			
	        		}

	        		
	            });
	    		
	    		currToggle.setOnMouseEntered(e -> {currToggle.setScaleX(1.1);
	    		currToggle.setTranslateX(10);
	    		currToggle.setTranslateY(-10);
	    		currToggle.setCursor(Cursor.HAND);
	    		DropShadow shadow = new DropShadow();
	    		currToggle.setEffect(shadow); 
	    		descriptiontital.setText("Cards Description");
                descriptions.setText("                                      " + card.getName() + "\n" + card.getDescription());
	    		});
	    		
	    		
	    		
	    		currToggle.setOnMouseExited(e -> {currToggle.setScaleX(1.0);
	    		currToggle.setTranslateX(0);
	    		currToggle.setTranslateY(0);
	    		currToggle.setCursor(Cursor.DEFAULT);
	    		currToggle.setEffect(null);
	    		descriptiontital.setText("");
                descriptions.setText("");
	    		});

	    		
	            
	    	
	        }
	        
	        
	        
	        
	        
	        
	        SpaceButton play = new SpaceButton("Play");
	        
	    
	        MainPlayer mainplayer = new MainPlayer(app, PlayerName , playerHandPane);
	        HBox playerProf= mainplayer.getPlayer();
	        

	        VBox descriptionsbox = new VBox();

	        descriptionsbox.getChildren().add(descriptiontital);
	        descriptionsbox.getChildren().add(descriptions);
	        
	        descriptionsbox.setAlignment(Pos.CENTER);
	        descriptiontital.setFont(Font.font("System", FontWeight.BOLD, 14)); // 14 is font size
	        descriptionsbox.setLayoutX(playerProf.getLayoutX() +10);
	        descriptionsbox.setLayoutY(playerProf.getLayoutY()+ 500);
	        
	        descriptions.setMaxWidth(300);
	        descriptions.setMaxHeight(150);
	        descriptions.setWrapText(true);

	        descriptions.setStyle("-fx-text-fill: white;" +     // Outline color
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
	        
	        
	       
	        
	       
	        
	        Image backgroundImage = new Image("Background1.jpg");
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
	        
	        HBox cp1 =cpu1.getPlayer();
	        HBox cp2=cpu2.getPlayer();
	        HBox cp3=cpu3.getPlayer();
	        
	        Rectangle deck1 = createMetallicBlackDeckHolder(300, 150);
	        holder.getChildren().add(deck1);
	        
//	        Rectangle deck2 = createMetallicBlackDeckHolder(200, 250);
//	        holder.getChildren().add(deck2);
//	        
//	        Rectangle deck3 = createMetallicBlackDeckHolder(300, 150);
//	        holder.getChildren().add(deck3);
//	        
//	        Rectangle deck4 = createMetallicBlackDeckHolder(300, 150);
//	        holder.getChildren().add(deck4);
	        
	        
	        holder.getChildren().addAll(playerProf,cp1,cp2,cp3,boardPit);
	        holder.getChildren().add(play);
	        holder.getChildren().add(descriptionsbox);
	        
	        
	        deck1.setLayoutX(550);
	        deck1.setLayoutY(580);
//	        
//	        deck2.setRotate(90);
//	        deck2.setLayoutX(1040);
//	        deck2.setLayoutY(235);
//	        
	        
	        boardPit.setLayoutY(175);
	        boardPit.setLayoutX(500);
	        
	        playerProf.setLayoutX(560);
	        playerProf.setLayoutY(600);
	        
	        cp2.setLayoutX(560);
	        cp2.setLayoutY(30);
	        
	        cp3.setLayoutX(90);
	        cp3.setLayoutY(275);
	        
	        cp1.setLayoutX(975);
	        cp1.setLayoutY(275);
	        



	        

	        holder.setPadding(new Insets(0, 0, 150, 0));
	        
	        
	        
	        
	        
	        play.setLayoutX(playerProf.getLayoutX() +350);
	        play.setLayoutY(playerProf.getLayoutY()+ 50);
	       
	        
	        
	        
	        
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
	        
	        
	        
	        Scene scene = new Scene(holder);
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
	
	public Rectangle createMetallicBlackDeckHolder(double width, double height) {
	    Rectangle deckHolder = new Rectangle(width, height);

	    // Main fill: metallic black gradient
	    Stop[] stops = new Stop[] {
	        new Stop(0, Color.web("#1a1a1a")),
	        new Stop(0.3, Color.web("#2b2b2b")),
	        new Stop(0.5, Color.web("#3a3a3a")),
	        new Stop(0.7, Color.web("#2b2b2b")),
	        new Stop(1, Color.web("#1a1a1a"))
	    };
	    LinearGradient metallicGradient = new LinearGradient(
	        0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
	    deckHolder.setFill(metallicGradient);

	    // Rounded edges
	    deckHolder.setArcWidth(12);
	    deckHolder.setArcHeight(12);

	    // Subtle glowing stroke (blending border)
	    deckHolder.setStroke(new LinearGradient(
	        0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
	        new Stop[] {
	            new Stop(0, Color.rgb(150, 150, 150, 0.3)),
	            new Stop(1, Color.rgb(255, 255, 255, 0.15))
	        }));
	    deckHolder.setStrokeWidth(2.5);

	    // Soft drop shadow for blending and lift
	    DropShadow softGlow = new DropShadow();
	    softGlow.setOffsetX(0);
	    softGlow.setOffsetY(0);
	    softGlow.setRadius(10);
	    softGlow.setColor(Color.rgb(255, 255, 255, 0.08));
	    deckHolder.setEffect(softGlow);

	    return deckHolder;
	}

}