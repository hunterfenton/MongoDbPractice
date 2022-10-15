package presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPresentation extends StackPane
{
    private static final double RESPONSE_WIDTH = 400.0;
    private static final double RESPONSE_HEIGHT = 300.0;
    
    public MainPresentation(MainController controller)
    {
        setPadding(new Insets(5.0));
        JFXTextField titleField = new JFXTextField();
        titleField.setPromptText("Movie Title");
        titleField.setLabelFloat(true);
        JFXButton queryTitleButton = new JFXButton("Query Title");
        queryTitleButton.setDefaultButton(true);
        queryTitleButton.getStyleClass().add("button-raised");
        queryTitleButton.setOnAction(e -> controller.queryMovieByTitle(titleField.getText().trim()));
        
        JFXTextField directorField = new JFXTextField();
        directorField.setPromptText("Movie Director");
        directorField.setLabelFloat(true);
        JFXButton queryDirectorButton = new JFXButton("Query Director");
        queryDirectorButton.setDefaultButton(true);
        queryDirectorButton.getStyleClass().add("button-raised");
        queryDirectorButton.setOnAction(e -> controller.queryMovieByDirector(directorField.getText().trim()));
        
        JFXTextArea responseArea = new JFXTextArea();
        responseArea.setPromptText("Response from MongoDB");
        responseArea.setLabelFloat(true);
        responseArea.setPrefSize(RESPONSE_WIDTH, RESPONSE_HEIGHT);
        responseArea.textProperty().bind(controller.getModel().movieResultProperty());
        
        HBox.setHgrow(titleField, Priority.ALWAYS);
        HBox.setHgrow(directorField, Priority.ALWAYS);
        HBox titleQueryBox = new HBox(titleField, queryTitleButton);
        HBox directoryQueryBox = new HBox(directorField, queryDirectorButton);
        VBox mainBox = new VBox(20.0, new Text(""), titleQueryBox, directoryQueryBox, responseArea);
        
        getChildren().add(mainBox);
    }
}
