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
        JFXDecorator decorator = new JFXDecorator(stage, new StackPane(new MainController().getPresentation()), false, true, true);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add("style.css");
        
        stage.setTitle("MongoDB Practice Application");        
        stage.setScene(scene);        
        stage.show();
        stage.sizeToScene();
    }
    
    @Override
    public void stop()
    {
        Platform.exit();
    }
}
