package scene;



import java.io.IOException;

import components.SpaceButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Main;

public class WelcomeScreen{

	private Scene WelcomeSceneScene;
	
	public WelcomeScreen(Main app) throws IOException {
		StackPane root = new StackPane();
		VBox content = new VBox();

		Label mainLabel = new Label("Enter Your Name");
		mainLabel.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 30));
		mainLabel.setTextFill(Color.WHITE);
		mainLabel.setPadding(new Insets(20));

		Label message = new Label("");
		message.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
		message.setTextFill(Color.WHITE);
		message.setPadding(new Insets(20));

		TextField inputField = new TextField();
		inputField.setPromptText("Enter Your Name");
		inputField.setPrefSize(200, 50);
		inputField.setAlignment(Pos.CENTER);
			
		inputField.setMaxWidth(200);

		SpaceButton button = new SpaceButton("Start");
		button.setCursor(Cursor.HAND);
		button.setPrefSize(200, 10);
		button.setFont(Font.font("Calibri", FontWeight.BOLD, 18));

		button.setOnAction(e -> {
			String userInput = inputField.getText();
			if (!inputField.getText().equals("")) {
				try {
					app.showSceneTwo(userInput);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				message.setText("Input your name");
			}
		});
		button.setPadding(new Insets(20));

		// Video Background
		String videoPath = getClass().getResource("/WelcomeScreenVid.mp4").toExternalForm();
		Media media = new Media(videoPath);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setAutoPlay(true);

		MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1400);
        mediaView.setFitHeight(780);

		// Setup content
		content.getChildren().addAll(mainLabel, inputField, button, message);
		content.setAlignment(Pos.CENTER);

		root.getChildren().addAll(mediaView, content);
//		s.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene s = new Scene(root, 1100, 780);
		this.WelcomeSceneScene = s;
	}

	
	public Scene getScene(){
		return this.WelcomeSceneScene;
	}


}

