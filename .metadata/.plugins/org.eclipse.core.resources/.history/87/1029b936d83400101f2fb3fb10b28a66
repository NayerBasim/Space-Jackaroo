package view;



import java.io.IOException;

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
import javafx.scene.layout.BorderPane;
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
		showSceneTwo("zoz");
	
	
		

	}
    public void showSceneOne() throws IOException {
        WelcomeScreen sceneOne = new WelcomeScreen(this);
        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setTitle("Scene One");
        primaryStage.show();
    }
    public void showSceneTwo(String userInput) throws IOException {
    	GameScreen sceneTwo = new GameScreen(this,userInput, this.controller);
        primaryStage.setScene(sceneTwo.getScene());
        primaryStage.setTitle("Scene Two");
        primaryStage.show();
    }
    
    
	public static void main(String[] args){
		
		launch(args);
	}
	
	public static void showAlert(String title, String message) {
		Stage alertStage = new Stage();
        alertStage.setTitle(title);

        Label label = new Label(message);
        Button closeButton = new Button("Continue ");
        closeButton.setOnAction(event -> alertStage.close());

        BorderPane pane = new BorderPane();
        pane.setTop(label);
        pane.setCenter(closeButton);

        Scene scene = new Scene(pane, 500, 100);
        alertStage.setScene(scene);
        alertStage.show();
	}
	
}
