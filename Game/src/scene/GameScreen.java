package scene;

import model.card.standard.Five;

import java.util.ArrayList;
import java.util.List;

import components.Board;
import components.CPUPlayer1;
import components.CPUPlayer2;
import components.CPUPlayer3;
import components.MainPlayer;
import components.PlayerHand;
import engine.GameManager;
import engine.board.BoardManager;
import view.Main;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.card.Card;
import model.card.standard.Ace;
import model.card.standard.Four;
import model.card.standard.Suit;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameScreen {
	  private Scene GameScene;
	  private static final int GRID_SIZE = 51;      // must be odd
	  private static final int TOTAL_TRACKS = 100;  // we’ll skip the center to get 100
	  private static final int CELL_SIZE = 12;      // px
	  private static final double CIRCLE_RADIUS = 4;
	  private final String PlayerName;
	public GameScreen(Main app, String PlayerName){
			BorderPane root = new BorderPane();
			this.PlayerName = PlayerName;
			
			
	  	    StackPane grid = new Board().boardRoot;
	  	    grid.setMaxSize(100, 100);
	  	    




	        root.setBottom(new MainPlayer(app, PlayerName).getPlayer());
	        root.setRight(new CPUPlayer1(app).getPlayer());
	        root.setTop(new CPUPlayer2(app).getPlayer());
	        root.setLeft(new CPUPlayer3(app).getPlayer());
	        root.setCenter(grid);
	        root.setPadding(new Insets(0,0,100,0));
	        
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
	        root.setBackground(new Background(bgImage));
	        
	        Scene scene = new Scene(root,900,900);
		     this.GameScene = scene;
		    




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