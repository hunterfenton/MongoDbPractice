package abstraction;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.regex.Pattern;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import abstaction.immutable.User;

public class Database
{
    private static Database instance = new Database();
    private CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    private MongoClient mongoClient = MongoClients.create(User.HUNTER.getUri());
    private MongoCollection<Movie> movieCollection;
    
    private Database()
    {
        movieCollection = mongoClient.getDatabase("sample_mflix").withCodecRegistry(pojoCodecRegistry).getCollection("movies", Movie.class);
    }
    
    public static Database getInstance()
    {
        return instance;
    }
    
    public void disconnect()
    {
        mongoClient.close();
    }
    
    public void setUser(User user)
    {
        mongoClient = MongoClients.create(user.getUri());
        movieCollection = mongoClient.getDatabase("sample_mflix").withCodecRegistry(pojoCodecRegistry).getCollection("movies", Movie.class);
    }
    
    public String findMovieFromTitle(String title)
    {
        String detailString = "";
        try
        {
            Pattern pattern = Pattern.compile(new StringBuilder("(?i)^").append(title).append("$").toString(), Pattern.CASE_INSENSITIVE);
            Movie movie =  mongoClient.getDatabase("sample_mflix").withCodecRegistry(pojoCodecRegistry).getCollection("movies", Movie.class).find(Filters.regex("title", pattern)).first();
            detailString = movie.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            detailString = "no results";
        }
        
        return detailString;
    }
    
    public String findMoviesFromDirector(String director)
    {
        String detailString = "";
        FindIterable<Movie> movies = movieCollection.find(Filters.regex("directors", Pattern.compile(new StringBuilder("(?i)^").append(director).append("$").toString(), Pattern.CASE_INSENSITIVE)));
        for (Movie movie : movies)
        {
            detailString += movie.getTitle()+"\n";
        }
        
        return detailString.length() > 0 ? detailString : "no results";
    }
}
