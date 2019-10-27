package week09_part02;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Example demonstrates resizing a Circle shape object via handlers that
 * call Circle methods.
 *
 * @author Liang, Introduction to Java Programming
 */
public class ControlCircle extends Application
{
    protected static final boolean ENABLE_DEBUG = true;
    private CirclePane circlePane = new CirclePane();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        // Hold two buttons in an HBox
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hBox.getChildren().add(btEnlarge);
        hBox.getChildren().add(btShrink);

        // Create and register the handler
        btEnlarge.setOnAction(new EnlargeHandler());

        //OPTION 1: Inner Class Verison of EventHandler
        //btShrink.setOnAction(new ShrinkHandler()); //ShrinkHandler

        //OPTION 2: Anonymous Inner Class Version of EventHandler
//        btShrink.setOnAction(
//                new EventHandler<ActionEvent>()
//                {
//                    @Override
//                    public void handle(ActionEvent e)
//                    {
//                        circlePane.shrink();
//                    }
//                }
//        );


        //OPTION 3: Anonymous Inner Class Version of EventHandler
//        btShrink.setOnAction(new EventHandler()
//        {
//            @Override
//            public void handle(ActionEvent e)
//            {
//                circlePane.shrink();
//            }
//        });

        //OPTION 4: Lambda Expression Version of EventHandler
        btShrink.setOnAction(e ->
        {
            circlePane.shrink();
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 400, 350);
        primaryStage.setTitle("ControlCircle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    class EnlargeHandler implements EventHandler<ActionEvent>
    {
        @Override // Override the handle method
        public void handle(ActionEvent e)
        {    circlePane.enlarge(); }
    }

//    //OPTION 1: Inner Class Verison of EventHandler
//    class ShrinkHandler implements EventHandler<ActionEvent>
//    {
//        @Override // Overrides the handle method
//        public void handle(ActionEvent a)
//        {
//            circlePane.shrink();
//        }
//    }
}

