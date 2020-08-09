import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class RotationExample extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Sample");
    stage.setWidth(300);
    stage.setHeight(190);

    VBox vbox = new VBox();
    vbox.setLayoutX(20);
    vbox.setLayoutY(20);
    
    Rectangle rect = new Rectangle(50,50, Color.RED);
    rect.getTransforms().add(new Rotate(45,0,0)); //

    vbox.getChildren().add(rect);
    vbox.setSpacing(10);
    ((Group) scene.getRoot()).getChildren().add(vbox);

    stage.setScene(scene);
    stage.show();
  }
}