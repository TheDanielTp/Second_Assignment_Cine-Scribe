import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;
import org.json.JSONArray;

public class Actors
{
    public static final String API_KEY = "6naYoRCtkPYmFCvTbFSHvyV9QYFjxflblctZYdDq"; //api key received from api-ninjas
    double netWorth;
    Boolean isAlive;
    String dateOfDeath;
    String nationality;
    ArrayList<String> occupations;

    public Actors(double netWorth, boolean isAlive, String nationality) { //constructor function for alive actors
        this.netWorth = netWorth;
        this.isAlive = isAlive;
        this.nationality = nationality;
    }

    public Actors(double netWorth, boolean isAlive, String nationality, String dateOfDeath) { //constructor function for dead actors
        this.netWorth = netWorth;
        this.isAlive = isAlive;
        this.nationality = nationality;
        this.dateOfDeath = dateOfDeath;
    }

    public Actors(double netWorth, boolean isAlive, String nationality, String dateOfDeath, ArrayList<String> occupations) { //constructor function for actors with occupation
        this.netWorth = netWorth;
        this.isAlive = isAlive;
        this.nationality = nationality;
        this.dateOfDeath = dateOfDeath;
        this.occupations = occupations;
    }

    /**
     * retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the actors info or null if an error occurred
    */

    public String getActorData(String name)
    {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name=" + name.replace(" ", "+") + "&apikey=" + API_KEY);

            HttpURLConnection Url = (HttpURLConnection) url.openConnection(); //opens a connection to the url created using the actor name and the api
            Url.setRequestProperty("X-Api-Key", API_KEY); //sets the authorization request property of the url connection

            BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream())); //creates a reader to read the input stream from the url connection

            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) { //reads each line of the response and appends it to the stringbuilder
                    stringBuilder.append(line);
            }
            reader.close(); //closes the reader once all lines have been read

            stringBuilder.deleteCharAt(0);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            String response = stringBuilder.toString(); //converts the stringbuilder to a string and assigns it to response

            if (response == "") { //check if the response is empty
                return "Error: Actor not found.";
            }
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getNetWorthViaApi(String actorsInfoJson)
    {
        JSONObject netWorthJsonObject = new JSONObject(actorsInfoJson); //parses the json string into a json object
        double netWorthStr = netWorthJsonObject.getDouble("net_worth"); //creates a double and gets the value of net-worth field

        try { //returns the net-worth amount
            return netWorthStr;
        }
        catch (Exception e) { //handles any possible errors
            System.err.println("Error: " + e.getMessage());
        }
        return 0; //returns 0 if net-worth field is not found or cannot be parsed
    }

    public boolean isAlive(String actorsInfoJson)
    {
        JSONObject isAliveJsonObject = new JSONObject(actorsInfoJson); //parses the json string into a json object
        boolean isAlive = isAliveJsonObject.getBoolean("is_alive"); //creates a boolean and gets the content of is-alive field

        try { //return the is-alive content
            return isAlive;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return true; //returns true if is-alive field is not found
    }

    public String getDateOfDeathViaApi(String actorsInfoJson)
    {
        JSONObject deathJsonObject = new JSONObject(actorsInfoJson); //parses the json string into a json object
        String dateOfDeath = deathJsonObject.getString("death"); //creates a string and gets the content of death field

        try { //return the date of death string
            return dateOfDeath;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getNationalityViaApi(String actorsInfoJson)
    {
        JSONObject nationalityJsonObject = new JSONObject(actorsInfoJson); //parses the json string into a json object
        String nationality = nationalityJsonObject.getString("nationality"); //creates a string and gets the content of nationality field

        try { //return the nationality string
            return nationality;
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; //returns null if death field is not found
    }

    public String getOccupationsViaApi(String movieInfoJson)
    {
        JSONObject occupationsJsonObject = new JSONObject(movieInfoJson); //parses the json string into a json object

        JSONArray occupationsArray = occupationsJsonObject.getJSONArray("occupation");
        String[] occupationsList = new String[occupationsArray.length()];

        for (int i = 0; i < occupationsArray.length(); i++) { //extract each rating from the jsonarray and put it into the ratings array
            occupationsList[i] = occupationsArray.getString(i); //extracts strings one by one and adds them to the string array

            occupationsList[i].replaceAll("_", " "); //replaces underline characters with space
        }
        return Arrays.toString(occupationsList); //converts the array to string and returns it
    }
}
