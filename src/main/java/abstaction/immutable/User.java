package abstaction.immutable;

public enum User
{
    HUNTER("Hunter", "mongodb+srv://hunter:pfg3AemvP9ymznE3@cluster0.a5o2cku.mongodb.net/?retryWrites=true&w=majority"),
    GUEST("Guest", "mongodb+srv://guest:jSqmlLjJjrIuUeRb@cluster0.a5o2cku.mongodb.net/?retryWrites=true&w=majority");
    
    private String name;
    private String uri;
    
    private User(String prettyName, String uri)
    {
        this.name = prettyName;
        this.uri = uri;
    }
    
    public String getUri()
    {
        return uri;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
