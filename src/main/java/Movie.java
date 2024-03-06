import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;
import org.json.JSONArray;

public class Movie
{
    public static final String API_KEY = "520cf7ab"; //api key received from omdbapi
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;
    String[] ratings;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes) { //constructor function for initializing the object
        this.actorsList = actorsList;
        this.rating = rating; //inputs a single string
        this.ImdbVotes = ImdbVotes;
    }

    public Movie(ArrayList<String> actorsList, String[] ratings, int ImdbVotes) { //constructor function for finalizing the object
        this.actorsList = actorsList;
        this.ratings = ratings; //inputs an array of strings
        this.ImdbVotes = ImdbVotes;
    }

    /**
     * retrieves data for the specified movie.
     * @param title the name of the title for which moviedata should be retrieved
     * @return a string representation of the moviedata, or null if an error occurred
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

        while ((line = reader.readLine()) != null) { //reads each line of the response and appends it to the stringbuilder
            stringBuilder.append(line);
        }
        reader.close(); //closes the reader once all lines have been read

        String response = stringBuilder.toString(); //converts the stringbuilder to a string and assigns it to response

        if (response.contains("False")) { //check if the response contains an error message
            return "Error: Movie not found.";
        }
        return response;
    }

    public int getImdbVotesViaApi(String moviesInfoJson)
    {
        JSONObject moviesJsonObject = new JSONObject(moviesInfoJson); //parses the json string into a json object

        if (moviesJsonObject.has("imdbVotes")) { //checks if the imdbvotes exists in the json object
            String imdbVotesStr = moviesJsonObject.getString("imdbVotes"); //creates a string and gets the value of imdbvotes field

            imdbVotesStr = imdbVotesStr.replaceAll("[^\\d]", ""); //removes characters which are not numbers

            try { //parse the imdbvotes string to an integer
                int imdbVotesInt = Integer.parseInt(imdbVotesStr);
                return imdbVotesInt;
            }
            catch (NumberFormatException e) { //handles any possible errors
                System.err.println("Error parsing IMDB votes: " + e.getMessage());
            }
        }
        return 0; //returns 0 if imdbvotes field is not found or cannot be parsed
    }

    //this function is created to pass the test cases and is not used within the main code
    public String getRatingViaApi(String moviesInfoJson)
    {
        JSONObject ratingsJsonObject = new JSONObject(moviesInfoJson); //parses the json string into a json object

        JSONArray ratingsArray = ratingsJsonObject.getJSONArray("Ratings"); //extract ratings jsonarray from the jsonobject
        String[] values = new String[ratingsArray.length()]; //array to hold individual ratings

        for (int i = 0; i < ratingsArray.length(); i++) { //extract each rating from the jsonarray and put it into the ratings array
            JSONObject ratingObject = ratingsArray.getJSONObject(i); //creates a jsonobject for each individual object of the array

            values[i] = ratingObject.getString("Value"); //stores the score which the movie got form the source
        }
        return values[0];
    }

    public String getFullRatingViaApi(String moviesInfoJson)
    {
        JSONObject ratingsJsonObject = new JSONObject(moviesInfoJson); //parses the json string into a json object

        JSONArray ratingsArray = ratingsJsonObject.getJSONArray("Ratings"); //extract ratings jsonarray from the jsonobject
        String[] ratings = new String[ratingsArray.length()]; //array to hold individual ratings

        for (int i = 0; i < ratingsArray.length(); i++) { //extract each rating from the jsonarray and put it into the ratings array
            JSONObject ratingObject = ratingsArray.getJSONObject(i); //creates a jsonobject for each individual object of the array

            String source = ratingObject.getString("Source"); //stores the name of the source
            String value = ratingObject.getString("Value"); //stores the score which the movie got form the source

            ratings[i] = source + ": " + value; //combines the strings to define each array
        }
        return Arrays.toString(ratings);
    }

    public String getActorListViaApi(String movieInfoJson)
    {
        JSONObject actorsJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String[] actorsListArray = actorsList.toArray(new String[actorsList.size()]); //string array to contain the final anwer

        if (actorsJsonObject.has("Actors")) { //checks if the actors field exists in the json data
            String actorsString = actorsJsonObject.getString("Actors"); //extracts the actors string from the json data

            String[] actorsArray = actorsString.split(", "); //splits the actors string into individual actor names

            for (String actor : actorsArray) { //adds each actor name to the actors list
                actorsList.add(actor);
            }

            actorsListArray = actorsList.toArray(new String[actorsList.size()]); //finalize the actors list string
            return Arrays.toString(actorsListArray);
        }
        else { //error message if no actor information is found
            System.out.println("Error: No actor information found.");
        }
        return Arrays.toString(actorsListArray);
    }
}
