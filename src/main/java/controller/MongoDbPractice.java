package controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXDecorator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MongoDbPractice extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws ClassNotFoundException, SQLException
    {
        MainController controller =new MainController();
        JFXDecorator decorator = new JFXDecorator(stage, new StackPane(controller.getPresentation()), false, true, true);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add("style.css");
        
        stage.setTitle("MongoDB Practice Application");        
        stage.setScene(scene);        
        stage.show();
        stage.sizeToScene();
        
        stage.setOnCloseRequest(e -> 
        {
            controller.shutdown();
            Platform.exit();
            System.exit(0);
        });
    }
}
