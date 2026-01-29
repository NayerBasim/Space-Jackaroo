package scene;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import components.SpaceButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import view.Main;

public class AboutScreen {

    private Scene startScene;

    public AboutScreen(Main app) throws IOException {
        StackPane root = new StackPane();
        VBox content = new VBox(20);

        // Main Title
        Label mainTitle = new Label("About This Game");
        mainTitle.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setPadding(new Insets(10));

        // Subtitle
        Label subtitle = new Label(
        	    "Your ship�s being attacked by an adversarial alien race,\n" +
        	    "they�ve kidnapped 4 of your crew and trapped them in their on-ship prison.\n" +
        	    "The aliens challenge you to a game of Jackaroo.\n" +
        	    "If you win, they will let you go.\n" +
        	    "Otherwise, you'll forever be their prisoner.\n" +
        	    "GOOD LUCK"
        	);
        	subtitle.setWrapText(true);
        	subtitle.setMaxWidth(800);
        	subtitle.setTextAlignment(TextAlignment.CENTER);
        	subtitle.setAlignment(Pos.CENTER);
        	subtitle.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        	subtitle.setTextFill(Color.LIGHTGRAY);
        	subtitle.setPadding(new Insets(10));

        // Optional: Start Button
        SpaceButton startButton = new SpaceButton("Start");
        startButton.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        startButton.setCursor(Cursor.HAND);
        startButton.setPadding(new Insets(10, 30, 10, 30));
        
        SpaceButton homeButton = new SpaceButton("Go Back");
        homeButton.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        homeButton.setCursor(Cursor.HAND);
        homeButton.setPadding(new Insets(10, 30, 10, 30));
        
        homeButton.setOnAction(e -> {
            try {
                app.showSceneThree(); // You can adjust this method to go to next scene
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        startButton.setOnAction(e -> {
            try {
                try {
					app.showSceneOne();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // You can adjust this method to go to next scene
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        content.getChildren().addAll(mainTitle, subtitle, startButton, homeButton);
        content.setAlignment(Pos.CENTER);

        // Background video
        String videoPath = getClass().getResource("/vid3.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);

        MediaView mediaView = new MediaView(mediaPlayer);
//        mediaView.setFitWidth(1100);
//        mediaView.setFitHeight(500);

        // Layering
        root.getChildren().addAll(mediaView, content);

        this.startScene = new Scene(root, 1100, 500);
    }

    public Scene getScene() {
        return this.startScene;
    }
}
