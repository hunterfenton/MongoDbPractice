package presentation;

import javafx.application.Platform;

public class JavaFxModel
{    
    public void update(Runnable r)
    {
        if(Platform.isFxApplicationThread())
        {
            r.run();
        }
        else
        {
            Platform.runLater(r);
        }
    }
}
