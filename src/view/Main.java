package view;



import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import scene.AboutScreen;
import scene.GameScreen;
import scene.StartScreen;
import scene.WelcomeScreen;

public class Main extends Application {
    private Stage primaryStage;


	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		showSceneThree();
		
//		Main.showAlert("Wrong Code", " Error while playing player \n        turn: Invalid Size");


	}
	
	
    public void showSceneOne() throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
        WelcomeScreen sceneOne = new WelcomeScreen(this);
        
        String fileName="Space Craft Passing Pulse Stutter.wav";
        URL resource = getClass().getResource(fileName);
        Media media=new Media(resource.toURI().toString());
        MediaPlayer mP= new MediaPlayer(media);
        mP.setCycleCount(0);
        mP.play();
        
        
        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setWidth(1100);
        primaryStage.setFullScreen(true);

        primaryStage.setTitle("Enter Name");
        primaryStage.show();

    }
    
    
    public void showSceneTwo(String userInput) throws IOException {
    	GameScreen sceneTwo = new GameScreen(this,userInput);
    	Scene scene=sceneTwo.getScene();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Scene Two");
        primaryStage.show();
        scene.getRoot().requestFocus(); // e.g., after stage.show()

    }
    
    
    
    public void showSceneThree() throws IOException {
        StartScreen sceneOne = new StartScreen(this);
//        primaryStage.setMinWidth(1100);
//        primaryStage.setMinHeight(750);
        primaryStage.setWidth(1100);
        
        primaryStage.setFullScreen(true);

        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setTitle("Start Screen");
        primaryStage.show();
        
    }
    
    
    public void showAboutScene() throws IOException {
		AboutScreen sceneOne = new AboutScreen(this);
        primaryStage.setScene(sceneOne.getScene());
        primaryStage.setWidth(1100);
        primaryStage.setMinHeight(750);
        primaryStage.setTitle("About Screen");
        primaryStage.show();
		
	}

    
    
	public static void main(String[] args){
		
		launch(args);
	}
	
	public static void showError(String title, String message) throws URISyntaxException {
		Stage alertStage = new Stage();
        alertStage.setTitle(title);
        
       
        
        
        Image backgroundImage;
        StackPane label = createGlitchLabel(message);
   
        Pane pane = new Pane();
        Button closeButton = new Button("Continue");

        Image buttonBG = new Image(Main.class.getResourceAsStream("buttonWP.png"));
        
        
        
        
//        if(title.equalsIgnoreCase("Trap Door")) {
//        	backgroundImage = new Image(Main.class.getResourceAsStream("futureBrig.jpg"));
//        }else if(title.equalsIgnoreCase("Wrong Code")) {
////        	backgroundImage = new Image(Main.class.getResourceAsStream("onesZeroes.gif"));
////        	backgroundImage = new Image(new File("onesZeroes.gif").toURI().toString());
//        
//
//        	
//        }else {
//        	backgroundImage = new Image(Main.class.getResourceAsStream("futureBrig.jpg"));
//        }
//        
        
        
    	String fileName="onesZeroes2.mp4";
        URL resource = Main.class.getResource(fileName);
        Media media=new Media(resource.toURI().toString());
        MediaPlayer mP= new MediaPlayer(media);
        mP.play();
        mP.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mView=new MediaView(mP);
        mView.setFitWidth(1100);
        mView.setFitHeight(1100);
        
        
//        ImageView bgImage = new ImageView(backgroundImage);
//        
//        bgImage.setFitHeight(400);
//        bgImage.setFitWidth(600);
        

//        BackgroundImage bgImage = new BackgroundImage(
//            backgroundImage,
//            BackgroundRepeat.NO_REPEAT,
//            BackgroundRepeat.NO_REPEAT,
//            BackgroundPosition.DEFAULT,
//            new BackgroundSize(
//                100, 100, true, true, true, true
//            )
//        );
//        pane.setBackground(new Background(bgImage));
        
        closeButton.setOnAction(event -> alertStage.close());
        closeButton.setOnKeyPressed(e->{
        	if (e.getCode() == KeyCode.ENTER) {
        		alertStage.close();
        	}
        	
        	
        });
        
//        BackgroundImage buttonBg= new BackgroundImage(
//	            buttonBG,
//	            BackgroundRepeat.NO_REPEAT,
//	            BackgroundRepeat.NO_REPEAT,
//	            BackgroundPosition.DEFAULT,
//	            new BackgroundSize(
//	                100, 100, true, true, true, true
//	            )
//	        );
        
//        String imageUrl = Main.class.getResource("buttonWP2.png").toExternalForm();
        closeButton.setStyle(
            "-fx-background-color: linear-gradient(to right, #0f0c29, #302b63, #24243e);" +
            "-fx-text-fill: #00fff7;" +  // neon cyan text
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-border-color: #00fff7;" +
            "-fx-border-width: 2px;" +
            "-fx-effect: dropshadow(gaussian, #00fff7, 10, 0.5, 0, 0);"
        );
        
        
        closeButton.setOnMouseEntered(e -> closeButton.setStyle(
        	    "-fx-background-color: linear-gradient(to right, #302b63, #24243e);" +
        	    "-fx-text-fill: #ffffff;" +
        	    "-fx-font-size: 16px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-padding: 10 20 10 20;" +
        	    "-fx-background-radius: 10;" +
        	    "-fx-border-radius: 10;" +
        	    "-fx-border-color: #ffffff;" +
        	    "-fx-border-width: 2px;" +
        	    "-fx-effect: dropshadow(gaussian, #ffffff, 15, 0.6, 0, 0);"
        	));

        	closeButton.setOnMouseExited(e -> closeButton.setStyle(
        	    "-fx-background-color: linear-gradient(to right, #0f0c29, #302b63, #24243e);" +
        	    "-fx-text-fill: #00fff7;" +
        	    "-fx-font-size: 16px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-padding: 10 20 10 20;" +
        	    "-fx-background-radius: 10;" +
        	    "-fx-border-radius: 10;" +
        	    "-fx-border-color: #00fff7;" +
        	    "-fx-border-width: 2px;" +
        	    "-fx-effect: dropshadow(gaussian, #00fff7, 10, 0.5, 0, 0);"
        	));
        

        pane.getChildren().addAll(mView,label,closeButton);
        label.setLayoutX(250);
        label.setLayoutY(175);

        closeButton.setLayoutX(345);
        closeButton.setLayoutY(350);
        pane.setMaxHeight(600);
        pane.setMaxWidth(800);
        
        
        
        
        
        

        Scene scene = new Scene(pane,800,600);
        alertStage.setScene(scene);
        alertStage.show();
	}
	
	
	
	
	public static StackPane createGlitchLabel(String message) {
		
	    Random random = new Random();
		
		Label tmp=new Label(message);
		tmp.setStyle(
				"-fx-text-fill: #00fff7;" +  // neon cyan
				"-fx-font-size: 18px;" +
	    	    "-fx-font-weight: bold;" +
	    	    "-fx-background-color: transparent;" +
	    	    "-fx-alignment: center;" +
	    	    "-fx-border-color: #00fff7;" +
	    	    "-fx-border-width: 2px;" +
	    	    "-fx-border-radius: 10;" +
	    	    "-fx-background-radius: 0;" +
	    	    "-fx-padding: 20;" 
//	    	    "-fx-effect: dropshadow(gaussian, #00fff7, 10, 0.6, 0, 0);"
	    	);
		
		
		Timeline flicker = new Timeline(new KeyFrame(
	    	    Duration.millis(150 + random.nextInt(200)), // 150–350ms interval
	    	    e -> {
	    	        double newOpacity = 0.4 + random.nextDouble() * 0.3; // 0.7–1.0 range
	    	        tmp.setOpacity(newOpacity);
	    	    }
	    	));
	    	flicker.setCycleCount(Animation.INDEFINITE);
	    	flicker.play();

		tmp.setWrapText(true);
		
	    tmp.setMinWidth(300);
	    tmp.setMinHeight(150);
		
	    Label label = new Label(message);

	    label.setMinHeight(150);
	    label.setMinWidth(300);

	    label.setWrapText(true);
	    
	    
	    label.setStyle(
	    	    "-fx-text-fill: #00fff7;" +  // neon cyan
	    	    "-fx-font-size: 18px;" +
	    	    "-fx-font-weight: bold;" +
	    	    "-fx-background-color: linear-gradient(to bottom, #0f0c29, #302b63, #24243e);" +
	    	    "-fx-alignment: center;" +
	    	    "-fx-border-color: #00fff7;" +
	    	    "-fx-border-width: 2px;" +
	    	    "-fx-border-radius: 10;" +
	    	    "-fx-background-radius: 10;" +
	    	    "-fx-padding: 20;" +
	    	    "-fx-effect: dropshadow(gaussian, #00fff7, 10, 0.6, 0, 0);"
	    	);
	    

	    
	    Timeline flicker2 = new Timeline(new KeyFrame(
	    	    Duration.millis(150 + random.nextInt(200)), // 150–350ms interval
	    	    e -> {
	    	        double newOpacity = 0.4 + random.nextDouble() * 0.3; // 0.7–1.0 range
	    	        label.setOpacity(newOpacity);
	    	    }
	    	));
	    	flicker2.setCycleCount(Animation.INDEFINITE);
	    	flicker2.play();

	    StackPane glitchPane = new StackPane();
	    glitchPane.setMinSize(300, 150);
	    glitchPane.setMaxSize(300, 150);

	    glitchPane.getChildren().add(label);
	    
	    // Create multiple vertical glitch streaks
	    int streakCount = 42;
	    
	    

	    for (int i = 0; i < streakCount; i++) {

	    	double streakWidth = 2 + random.nextDouble() * 5;
	        double streakHeight = 20 + random.nextDouble() * 150;
	        double spacing = 300.0 / streakCount; // total width / count
//	        double spacing = 300.0 / streakCount; // total width / count
	        double leftZone = -10 + random.nextDouble() * 200; // left 0–80 px
	        double rightZone = 200 + random.nextDouble() * 110; // right 220–300 px

	        double xPosition = random.nextBoolean() ? leftZone : rightZone;
	        xPosition = -150 + spacing / 2 + i * spacing;
	        final double xFinal = xPosition;






	        double yOffset;
	        if (random.nextBoolean()) { // Randomly place above or below the text area
	            yOffset = -80 + random.nextDouble() * 90;
	        } else {
	            yOffset = -20 + random.nextDouble() * 80; // Below the label, roughly 50 to 100
	        }
	        final double yFinal = yOffset;


	        Color color = i%2==0 ?
	                Color.rgb(26, 26, 255,0.5) :  // blue
	                Color.rgb(255, 26, 26,0.5);     // red

	        Rectangle streak = new Rectangle(streakWidth, streakHeight, color);
	        streak.setTranslateX(xPosition);
	        streak.setTranslateY(yOffset);

	        GaussianBlur blur = new GaussianBlur(2 + random.nextDouble() * 2);
	        streak.setEffect(blur);

	        Timeline glitchAnim = new Timeline(new KeyFrame(
	        	    Duration.millis(100 + random.nextInt(100)), // 100–200ms jitter
	        	    e -> {
	        	        double offsetX = -5 + random.nextDouble() * 10; // -5 to +5
	        	        double offsetY = -3 + random.nextDouble() * 6;  // small vertical shake
	        	        streak.setTranslateX(xFinal + offsetX);
	        	        streak.setTranslateY(yOffset + offsetY);
	        	    }
	        	));
	        	glitchAnim.setCycleCount(Animation.INDEFINITE);
	        	glitchAnim.play();

	        glitchPane.getChildren().add(streak);
	    }

	    glitchPane.getChildren().add(tmp);
	    glitchPane.setAlignment(label, Pos.CENTER);
	    

	    return glitchPane;
	}
	
	
	
	
}
