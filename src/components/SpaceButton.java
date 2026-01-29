package components;

import java.net.URL;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpaceButton extends Button {
    private static final String FONT_PATH = "Orbitron-Regular.ttf";
    private static final double DEFAULT_FONT_SIZE = 18;

    private DropShadow glowEffect;

    public SpaceButton(String text) {
        super(text);
        initialize();
    }

    private void initialize() {
        // Load the Orbitron font
        Font orbitron = Font.loadFont(getClass().getResourceAsStream(FONT_PATH), DEFAULT_FONT_SIZE);
        if (orbitron != null) {
            this.setFont(orbitron);
        } else {
            System.err.println("Could not load font from " + FONT_PATH);
            // fallback font
            this.setStyle("-fx-font-family: 'Arial';");
        }
        
        URL soundUrl = getClass().getResource("click.wav");
    	MediaPlayer clickSoundPlayer = null;

    	if (soundUrl != null) {
    	    Media clickSound = new Media(soundUrl.toExternalForm());
    	    clickSoundPlayer = new MediaPlayer(clickSound);
    	} else {
    	    System.err.println("Sound file not found!");
    	}
    	
    	MediaPlayer finalClickSoundPlayer = clickSoundPlayer;  // for use inside lambda

    	this.setOnAction(event -> {
    	    if (finalClickSoundPlayer != null) {
    	        finalClickSoundPlayer.stop();   // reset if already playing
    	        finalClickSoundPlayer.play();   // play the sound effect
    	    }
    	    
    	    // Your existing button logic here
    	    System.out.println("Button clicked!");  // placeholder
    	});

        // Set text color and base style
        this.setTextFill(Color.CYAN);
        this.setStyle(
            "-fx-background-color: #111;" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: cyan;" +
            "-fx-border-width: 2px;" +
            "-fx-padding: 10 20;" +
            "-fx-cursor: hand;"
        );

        // Create glow effect
        glowEffect = new DropShadow();
        glowEffect.setColor(Color.CYAN);
        glowEffect.setRadius(15);
        glowEffect.setOffsetX(0);
        glowEffect.setOffsetY(0);

        this.setEffect(glowEffect);

        // Hover effects to intensify glow
        this.setOnMouseEntered(e -> glowEffect.setRadius(25));
        this.setOnMouseExited(e -> glowEffect.setRadius(15));
    }
}
