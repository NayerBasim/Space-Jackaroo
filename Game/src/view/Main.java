package view;



import controller.GameController;
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
    private GameController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new GameController();
		this.primaryStage = primaryStage;
		showSceneOne();
	
	
		

	}
    public void showSceneOne() {
        WelcomeScreen sceneOne = new WelcomeScreen(this);
        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setTitle("Scene One");
        primaryStage.show();
    }
    public void showSceneTwo(String userInput) {
    	GameScreen sceneTwo = new GameScreen(this, userInput);
        primaryStage.setScene(sceneTwo.getScene());
        primaryStage.setTitle("Scene Two");
        primaryStage.show();
    }
    
    
	public static void main(String[] args){
		
		launch(args);
	}
}
