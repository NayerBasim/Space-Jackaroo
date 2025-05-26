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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;


	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		showSceneOne();


	}
    public void showSceneOne() throws IOException {
        WelcomeScreen sceneOne = new WelcomeScreen(this);
        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setTitle("Scene One");
        primaryStage.show();
    }
    public void showSceneTwo(String userInput) throws IOException {
    	GameScreen sceneTwo = new GameScreen(this,userInput);
    	Scene scene=sceneTwo.getScene();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setTitle("Scene Two");
        primaryStage.show();
        scene.getRoot().requestFocus(); // e.g., after stage.show()

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
        closeButton.setOnKeyPressed(e->{
        	if (e.getCode() == KeyCode.ENTER) {
        		alertStage.close();
        	}
        	
        	
        });;

        BorderPane pane = new BorderPane();
        pane.setTop(label);
        pane.setCenter(closeButton);

        Scene scene = new Scene(pane, 500, 100);
        alertStage.setScene(scene);
        alertStage.show();
	}
	
}
