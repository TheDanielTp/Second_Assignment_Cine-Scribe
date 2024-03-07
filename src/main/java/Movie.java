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
    String ImdbVotes;
    ArrayList<String> actorsList;
    String rating;
    String[] ratings;

    public Movie(ArrayList<String> actorsList, String rating, String ImdbVotes) { //constructor function for initializing the object
        this.actorsList = actorsList;
        this.rating = rating; //inputs a single string
        this.ImdbVotes = ImdbVotes;
    }

    public Movie(ArrayList<String> actorsList, String[] ratings, String ImdbVotes) { //constructor function for finalizing the object
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

    public String getTitleViaApi(String movieInfoJson)
    {
        JSONObject titleJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String title = titleJsonObject.getString("Title"); //creates a string and gets the content of title field

        try { //return the title string
            return title;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    //this function is created to pass the test cases and is not used within the main code
    public int getImdbVotesViaApi(String moviesInfoJson)
    {
        JSONObject imdbJsonObject = new JSONObject(moviesInfoJson); //parses the json string into a json object
        String imdbVotesStr = imdbJsonObject.getString("imdbVotes"); //creates a string and gets the content of imdbvotes field

        imdbVotesStr = imdbVotesStr.replaceAll("[^\\d]", ""); //removes characters which are not numbers

        try { //parse the imdbvotes string to an integer
            int imdbVotesInt = Integer.parseInt(imdbVotesStr);
            return imdbVotesInt;
        }
        catch (NumberFormatException e) { //handles any possible errors
            System.err.println("Error parsing IMDB votes: " + e.getMessage());
        }
        return 0; //returns 0 if imdbvotes field is not found or cannot be parsed
    }

    public String getFullImdbVotesViaApi(String movieInfoJson)
    {
        JSONObject imdbJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String imdbVotes = imdbJsonObject.getString("imdbVotes"); //creates a string and gets the content of box-office field

        try { //return the box-office string
            return imdbVotes;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getBoxOfficeViaApi(String movieInfoJson)
    {
        JSONObject boxOfficeJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String boxOffice = boxOfficeJsonObject.getString("BoxOffice"); //creates a string and gets the content of box-office field

        try { //return the box-office string
            return boxOffice;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
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

    public String getCountryViaApi(String movieInfoJson)
    {
        JSONObject countryJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String country = countryJsonObject.getString("Country"); //creates a string and gets the content of country field

        try { //return the country string
            return country;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getLanguageViaApi(String movieInfoJson)
    {
        JSONObject languageJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String language = languageJsonObject.getString("Language"); //creates a string and gets the content of language field

        try { //return the language string
            return language;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
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

    public String getDirectorViaApi(String movieInfoJson)
    {
        JSONObject directorJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String director = directorJsonObject.getString("Director"); //creates a string and gets the content of director field

        try { //return the director string
            return director;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getWriterViaApi(String movieInfoJson)
    {
        JSONObject writerJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String writer = writerJsonObject.getString("Writer"); //creates a string and gets the content of writer field

        try { //return the writer string
            return writer;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getPlotViaApi(String movieInfoJson)
    {
        JSONObject plotJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String plot = plotJsonObject.getString("Plot"); //creates a string and gets the content of plot field

        try { //return the plot string
            return plot;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getGenreViaApi(String movieInfoJson)
    {
        JSONObject genreJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String genre = genreJsonObject.getString("Genre"); //creates a string and gets the content of genre field

        try { //return the genre string
            return genre;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getYearViaApi(String movieInfoJson)
    {
        JSONObject yearJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String year = yearJsonObject.getString("Released"); //creates a string and gets the content of year field

        try { //return the year string
            return year;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getRunTimeViaApi(String movieInfoJson)
    {
        JSONObject runTimeJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object
        String runTime = runTimeJsonObject.getString("Runtime"); //creates a string and gets the content of runTime field

        try { //return the runTime string
            return runTime;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }
}
