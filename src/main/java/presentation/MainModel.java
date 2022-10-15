package presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainModel extends JavaFxModel
{
    private StringProperty movieResult = new SimpleStringProperty();
      
    public void setMovieResult(String result)
    {
        update(() -> movieResult.set(result));
    }
    
    String getMovieResult(String result)
    {
        return movieResult.get();
    }
    
    StringProperty movieResultProperty()
    {
        return movieResult;
    }
}
