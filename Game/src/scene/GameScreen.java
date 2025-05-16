package scene;

import java.util.ArrayList;
import java.util.List;

import view.Main;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameScreen {
	  private Scene GameScene;
	  private static final int GRID_SIZE = 51;      // must be odd
	  private static final int TOTAL_TRACKS = 100;  // we’ll skip the center to get 100
	  private static final int CELL_SIZE = 12;      // px
	  private static final double CIRCLE_RADIUS = 4;
	public GameScreen(Main app){

		 GridPane grid = new GridPane();
	        grid.setGridLinesVisible(false);
	        // fix each row/col to CELL_SIZE
	        for (int i = 0; i < GRID_SIZE; i++) {
	            ColumnConstraints cc = new ColumnConstraints(CELL_SIZE);
	            RowConstraints    rc = new RowConstraints(CELL_SIZE);
	            grid.getColumnConstraints().add(cc);
	            grid.getRowConstraints().add(rc);
	        }
	        //hello

	        // build the “X” path: main diagonal, then anti‑diagonal (skip center)
	        List<int[]> path = new ArrayList<>(TOTAL_TRACKS);
	        for (int i = 0; i < GRID_SIZE; i++) {
	            path.add(new int[]{ i, i });  // main diag
	        }
	        for (int i = 0; i < GRID_SIZE; i++) {
	            if (i == GRID_SIZE/2) continue;   // skip center overlap
	            path.add(new int[]{ i, GRID_SIZE-1 - i });  // anti‑diag
	        }
	        // now place only the first 100 circles
	        for (int idx = 0; idx < TOTAL_TRACKS; idx++) {
	            int r = path.get(idx)[0];
	            int c = path.get(idx)[1];
	            Circle circle = new Circle(CIRCLE_RADIUS, Color.LIGHTGREEN);
	            circle.setStroke(Color.DARKGREEN);

	            StackPane cell = new StackPane(circle);
	            // optional: show numbering
	            // Text num = new Text(String.valueOf(idx+1));
	            // cell.getChildren().add(num);

	            grid.add(cell, c, r);
	            GridPane.setHalignment(circle, HPos.CENTER);
	            GridPane.setValignment (circle, VPos.CENTER);
	        }

	        Scene scene = new Scene(new StackPane(grid), 
	                                GRID_SIZE*CELL_SIZE, GRID_SIZE*CELL_SIZE);
		     this.GameScene = scene;
		    




	}
    private StackPane createCircle(int number) {
        Circle circle = new Circle(15);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.DARKBLUE);

        StackPane pane = new StackPane();
        pane.getChildren().add(circle);
        pane.setPrefSize(CELL_SIZE, CELL_SIZE);

        return pane;
    }

	
	public Scene getScene(){
		return this.GameScene;
	}
}
