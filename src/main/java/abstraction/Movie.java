package abstraction;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

public class Movie
{
    private static final int WORD_WRAP = 70;
    private String           plot;
    private String           fullplot;
    private List<String>     genres;
    private int              year;
    private int              runtime;
    private String           title;

    public String getPlot()
    {
        return plot;
    }

    public void setPlot(String plot)
    {
        this.plot = plot;
    }    
    
    public String getFullplot()
    {
        return fullplot;
    }

    public void setFullplot(String plot)
    {
        this.fullplot = plot;
    }

    public List<String> getGenres()
    {
        return genres;
    }

    public void setGenres(List<String> genres)
    {
        this.genres = genres;
    }
    
    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getRuntime()
    {
        return runtime;
    }

    public void setRuntime(int runtime)
    {
        this.runtime = runtime;
    }
    
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    } 

    @Override
    public String toString()
    {
        return "Movie [\n  plot=" + WordUtils.wrap(fullplot, WORD_WRAP) + ",\n  genres=" + genres + ",\n  runtime=" + runtime + " mins,\n  year=" + year + "\n]";
    }
}
