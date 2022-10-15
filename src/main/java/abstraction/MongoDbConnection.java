package abstraction;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class MongoDbConnection
{
    private static String uri = "mongodb+srv://hunter:pfg3AemvP9ymznE3@cluster0.a5o2cku.mongodb.net/?retryWrites=true&w=majority";
    private static CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private static CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    
    public static String findMovieFromTitle(String title)
    {
        String detailString = "";
        try (MongoClient mongoClient = MongoClients.create(uri)) 
        {
            Movie movie =  mongoClient.getDatabase("sample_mflix").withCodecRegistry(pojoCodecRegistry).getCollection("movies", Movie.class).find(eq("title", title)).first();
            detailString = movie.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            detailString = "no results";
        }
        
        return detailString;
    }
    
    public static String findMoviesFromDirector(String director)
    {
        String detailString = "";
        try (MongoClient mongoClient = MongoClients.create(uri)) 
        {
            MongoCollection<Movie> collection = mongoClient.getDatabase("sample_mflix").withCodecRegistry(pojoCodecRegistry).getCollection("movies", Movie.class);
            FindIterable<Movie> movies = collection.find(eq("directors", director));
            for (Movie movie : movies)
            {
                detailString += movie.getTitle()+"\n";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            detailString = "no results";
        }
        
        return detailString;
    }
}
