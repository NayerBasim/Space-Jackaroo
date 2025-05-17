package controller;

import javafx.scene.control.TextField;
import engine.Game;

public class WelcomeController {
	
	public void SetName(TextField NameField){
		String userInput = NameField.getText();
		if(!NameField.getText().equals(""))
			app.showSceneTwo((Game) gameManager);

		else
			message.setText("Input your name");
		
	}
}
