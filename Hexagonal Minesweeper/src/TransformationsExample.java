import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class TransformationsExample extends Application {

    @Override
    public void start(Stage stage) {
        Rectangle rectangle = new Rectangle(50, 50, 100, 75);
        rectangle.setFill(Color.BURLYWOOD);
        rectangle.setStroke(Color.BLACK);
        Rotate rotate = new Rotate();
        rotate.setPivotX(500);
        rotate.setPivotY(500);
        rectangle.getTransforms().addAll(rotate);
        Group root = new Group(rectangle);
        Scene scene = new Scene(root, 1400, 780);
        stage.setTitle("Transformations");
        stage.setScene(scene);
        stage.show();

        // Show pivot point
        Circle dot = new Circle(5, Color.RED);
        dot.centerXProperty().bind(rotate.pivotXProperty());
        dot.centerYProperty().bind(rotate.pivotYProperty());
        root.getChildren().add(dot);

        // Animate rotation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0.0, Interpolator.DISCRETE)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90.0, Interpolator.DISCRETE)),
                new KeyFrame(Duration.seconds(2), new KeyValue(rotate.angleProperty(), 180.0, Interpolator.DISCRETE)),
                new KeyFrame(Duration.seconds(3), new KeyValue(rotate.angleProperty(), 270.0, Interpolator.DISCRETE)),
                new KeyFrame(Duration.seconds(4))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();
    }

}