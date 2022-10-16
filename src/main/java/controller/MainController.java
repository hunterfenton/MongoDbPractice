package controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abstaction.immutable.User;
import abstraction.Database;
import presentation.MainModel;
import presentation.MainPresentation;

public class MainController
{
    private MainPresentation presentation;
    private MainModel model = new MainModel();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    
    public MainController()
    {        
        presentation = new MainPresentation(this);
    }
    
    public MainModel getModel()
    {        
        return model;
    }
    
    public void queryMovieByTitle(String title)
    {        
        executor.submit(() -> model.setMovieResult(Database.getInstance().findMovieFromTitle(title)));
    }
    
    public void queryMovieByDirector(String director)
    {        
        executor.submit(() -> model.setMovieResult(Database.getInstance().findMoviesFromDirector(director)));
    }
    
    public void setUser(User user)
    {        
        Database.getInstance().setUser(user);
    }
    
    void shutdown()
    {
        Database.getInstance().disconnect();
        executor.shutdownNow();
    }
    
    MainPresentation getPresentation()
    {
        return presentation;
    }
}
