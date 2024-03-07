import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        runMenu(); //directly attends to the runmenu function
    }

    public static void runMenu()
    {
        Scanner scanner = new Scanner(System.in); //defines a scanner to recieve inputs from user

        System.out.println("Welcome to the Movie and Actors Info System!"); //generates the user interface menu
        while (true) {
            System.out.println("\n        Menu");
            System.out.println("1. Search for Movies");
            System.out.println("2. Search for Actors");
            System.out.println("3. Close the Program\n");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); //consumes the next line so the number is approved after pressing enter

            switch (choice) { //recieves input and does one of the following action depending on the value
                case 1:
                    searchForMovie(scanner); //calls the search for movie function
                    break;
                case 2:
                    searchForActor(scanner); //calls the search for actor function
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close(); //closes the scanner
                    System.exit(0); //closes the loop and quits the app
                default:
                    System.out.println("Invalid choice. Please try again."); //response for invalid values
            }
        }
    }

    public static void searchForMovie(Scanner scanner)
    {
        System.out.print("Enter the title of the movie: ");
        String movieTitle = scanner.nextLine(); //recieves the title of the movie

        Movie movie = new Movie(new ArrayList<>(), "", 0); //creates an object of movie class with null inputs
        try {
            String movieInfoJson = movie.getMovieData(movieTitle); //parse json and extract necessary information
            String title = movie.getTitleViaApi(movieInfoJson); //calls the gettitle function from movie class

            String imdbVotes = movie.getFullImdbVotesViaApi(movieInfoJson); //calls the getfullimdbvotes function from movie class
            int imdbVotesInt = movie.getImdbVotesViaApi(movieInfoJson); //calls the getimdbvotes function from movie class

            String year = movie.getYearViaApi(movieInfoJson); //calls the getyear function from movie class
            String runTime = movie.getRunTimeViaApi(movieInfoJson); //calls the getruntime function from movie class

            String ratings = movie.getFullRatingViaApi(movieInfoJson); //calls the getratings function from movie class
            String director = movie.getDirectorViaApi(movieInfoJson); //calls the getdirector function from movie class

            String writer = movie.getWriterViaApi(movieInfoJson); //calls the getwriter function from movie class
            String plot = movie.getPlotViaApi(movieInfoJson); //calls the getplot function from movie class

            String country = movie.getCountryViaApi(movieInfoJson); //calls the getcountry function from movie class
            String language = movie.getLanguageViaApi(movieInfoJson); //calls the getlanguage function from movie class

            ArrayList<String> actorsList = new ArrayList<>(); //creates a string array to store actors names
            String actorList = movie.getActorListViaApi(movieInfoJson); //calls the getactorlist function from movie class

            String boxOffice = movie.getBoxOfficeViaApi(movieInfoJson); //calls the getboxoffice function from movie class
            String genre = movie.getGenreViaApi(movieInfoJson); //calls the getgenre function from movie class

            movie = new Movie(actorsList, ratings, imdbVotesInt); //redefines the movie object with the new information

            System.out.println(); //prints out movie's information
            System.out.println("Movie Title: " + title);
            System.out.println("Released: " + year);
            System.out.println("Runtime: " + runTime);
            System.out.println("Genre: " + genre);
            System.out.println("Director: " + director);
            System.out.println("Writer: " + writer);
            System.out.println("Actors: " + actorList);
            System.out.println("Plot: " + plot);
            System.out.println("Country: " + country);
            System.out.println("Language: " + language);
            System.out.println("Ratings: " + ratings);
            System.out.println("IMDB Votes: " + movie.ImdbVotes);
            System.out.println("BoxOffice: " + boxOffice);
        }
        catch (IOException e) { //handles any possible errors
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchForActor(Scanner scanner)
    {
        System.out.print("Enter the name of the actor: ");
        String actorName = scanner.nextLine(); //recieves the title of the movie

        Actors actor = new Actors(0, false, "", "", new ArrayList<>()); //creates an object of actor class with null inputs
        try {
            String actorsInfoJson = actor.getActorData(actorName);

            double netWorth = actor.getNetWorthViaApi(actorsInfoJson); //calls the getnetworth function from actor class
            boolean isAlive = actor.isAlive(actorsInfoJson); //calls the isalive function from actor class

            String nationality = actor.getNationalityViaApi(actorsInfoJson); //calls the getnationality function from actor class
            String occupations = actor.getOccupationsViaApi(actorsInfoJson); //calls the getoccupations function from actor class

            String birthday = actor.getBirthdayViaApi(actorsInfoJson); //calls the getbirhday function from actor class
            double height = actor.getHeightViaApi(actorsInfoJson); //calls the getheight function from actor class

            String dateOfDeath = ""; //creates a string to store the date of death if the actor is dead

            if (isAlive == false) { //checks the isalive boolean
                dateOfDeath = actor.getDateOfDeathViaApi(actorsInfoJson); //calls the getdateofdeath function from actor class
                actor = new Actors(netWorth, isAlive, nationality, dateOfDeath); //redefines the actor object with the new information
            }
            else {
                actor = new Actors(netWorth, isAlive, nationality, occupations); //redefines the actor object with the new information
            }

            System.out.println(); //prints out movie's information
            System.out.println("Name: " + actorName);
            System.out.println("Net-Worth: " + actor.netWorth);
            System.out.println("Nationality: " + actor.nationality);
            System.out.println("Occupations: " + occupations);
            System.out.println("Height: " + height);
            System.out.println("Birthday: " + birthday);
            System.out.println("Is-Alive: " + actor.isAlive);

            if (isAlive == false) { //prints date of death only if the actor is dead
                System.out.println("Death: " + actor.dateOfDeath);
            }
        }
        catch (Exception e) { //handles any possible errors
            System.out.println("Error: " + e.getMessage());
        }
    }
}
