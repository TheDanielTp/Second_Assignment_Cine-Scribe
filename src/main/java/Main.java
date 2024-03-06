import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        runMenu(); //directly attends to runmenu function
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
            System.out.println(movieInfoJson);

            int imdbVotes = movie.getImdbVotesViaApi(movieInfoJson); //calls the getimdbvotes function from movie class
            String ratings = movie.getFullRatingViaApi(movieInfoJson); //calls the getratings function from movie class

            ArrayList<String> actorsList = new ArrayList<>(); //creates a string array to store actors names
            String actorList = movie.getActorListViaApi(movieInfoJson); //calls the actorlist function from movie class

            movie = new Movie(actorsList, ratings, imdbVotes); //redefines the movie object with the new information

            System.out.println(); //prints out movie's information
            System.out.println("Movie Title: " + movieTitle);
            System.out.println("IMDB Rating: " + ratings);
            System.out.println("IMDB Votes: " + movie.ImdbVotes);
            System.out.println("Actors: " + actorList);
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

            String nationality = actor.getNationalityViaApi(actorsInfoJson);
            String occupations = actor.getOccupationsViaApi(actorsInfoJson);

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
