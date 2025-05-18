package components;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardView extends StackPane {

    public CardView(Image image) {
        ImageView view = new ImageView(image);
        view.setFitWidth(70);
        view.setFitHeight(100);
        this.getChildren().add(view);
    }
}

