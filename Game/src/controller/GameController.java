package controller;

import java.io.IOException;

import view.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import engine.Game;

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
	
	
}
