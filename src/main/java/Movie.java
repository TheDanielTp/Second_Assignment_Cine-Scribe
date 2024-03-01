import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Movie
{
    public static final String API_KEY = "520cf7ab";
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        this.actorsList = actorsList;
        this.rating = rating;
        this.ImdbVotes = ImdbVotes;
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
    */

    public String getMovieData(String title) throws IOException
    {
        URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY); //constructs a url to the movie information api
        URLConnection Url = url.openConnection(); //opens a connection to the url created using the movie title and the api

        //the following line was edited due to having a small mistake
        Url.setRequestProperty("apikey", API_KEY); //sets the authorization request property of the url connection

        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream())); //creates a reader to read the input stream from the url connection

        String line; //declares a string variable "line" to store each line of the response
        StringBuilder stringBuilder = new StringBuilder(); //creates a stringbuilder to efficiently concatenate the lines of the response

        while ((line = reader.readLine())!=null) { //reads each line of the response and appends it to the stringbuilder
            stringBuilder.append(line);
        }
        reader.close(); //closes the reader once all lines have been read

        String response = stringBuilder.toString(); //converts the stringbuilder to a string and assigns it to response
        System.out.println(response);

        if (response.contains("False")) { // Check if the response contains an error message
            return "Error: Movie not found.";
        }
        return response;
    }

    public int getImdbVotesViaApi(String moviesInfoJson)
    {
        //TODO --> (This function must change and return the "ImdbVotes" as an Integer)
        //NOTICE :: you are not permitted to convert this function to return a String instead of an int !!!
        int ImdbVotes = 0;
        return ImdbVotes;
    }

    public String getRatingViaApi(String moviesInfoJson){
        //TODO --> (This function must return the rating in the "Ratings" part
        //TODO --> where the source is "Internet Movie Database")  -->
        String rating = "";
        return rating;
    }

    public void getActorListViaApi(String movieInfoJson){
        //TODO --> (This function must return the "Actors" in actorsList)
    }
}
