package controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abstraction.MongoDbConnection;
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
        executor.submit(() -> model.setMovieResult(MongoDbConnection.findMovieFromTitle(title)));
    }
    public void queryMovieByDirector(String director)
    {        
        executor.submit(() -> model.setMovieResult(MongoDbConnection.findMoviesFromDirector(director)));
    }
    
    MainPresentation getPresentation()
    {
        return presentation;
    }
}
