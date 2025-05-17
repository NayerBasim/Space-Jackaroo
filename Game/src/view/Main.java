package view;



import engine.GameManager;
import scene.GameScreen;
import scene.WelcomeScreen;
import engine.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private Game game;

	@Override
	public void start(Stage primaryStage) throws Exception {
		game=new Game();
		this.primaryStage = primaryStage;
		showSceneOne(game);
	
	
		

	}
    public void showSceneOne(Game game) {
        WelcomeScreen sceneOne = new WelcomeScreen(this , game.getBoard() , (GameManager)game);
        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setTitle("Scene One");
        primaryStage.show();
    }
    public void showSceneTwo(Game game) {
    	GameScreen sceneTwo = new GameScreen(this, game.getBoard(),game);
        primaryStage.setScene(sceneTwo.getScene());
        primaryStage.setTitle("Scene Two");
        primaryStage.show();
    }
	public static void main(String[] args){
		
		launch(args);
	}
}
