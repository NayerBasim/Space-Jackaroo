package scene;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.Main;
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

public class StartScreen {

    private Scene startScene;

    public StartScreen(Main app) throws IOException {
        StackPane root = new StackPane();
        VBox content = new VBox(20);

        // Main Title
        Label mainTitle = new Label("Welcome to Jackaroo");
        mainTitle.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setPadding(new Insets(10));

        // Subtitle
        Label subtitle = new Label("Space Edition");
        subtitle.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 30));
        subtitle.setTextFill(Color.LIGHTGRAY);
        subtitle.setPadding(new Insets(10));

        // Optional: Start Button
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        startButton.setCursor(Cursor.HAND);
        startButton.setPadding(new Insets(10, 30, 10, 30));
        
        Button aboutButton = new Button("About");
        aboutButton.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        aboutButton.setCursor(Cursor.HAND);
        aboutButton.setPadding(new Insets(10, 30, 10, 30));
        
        aboutButton.setOnAction(e -> {
            try {
                app.showAboutScene(); // You can adjust this method to go to next scene
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

        content.getChildren().addAll(mainTitle, subtitle, startButton, aboutButton);
        content.setAlignment(Pos.CENTER);

        // Background video
        String videoPath = getClass().getResource("/WelcomeScreenVid.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1400);
        mediaView.setFitHeight(780);

        // Layering
        root.getChildren().addAll(mediaView, content);

        this.startScene = new Scene(root, 1400, 780);
    }

    public Scene getScene() {
        return this.startScene;
    }
}
