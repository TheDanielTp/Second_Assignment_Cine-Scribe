# Cinema Info Finder

- This project is a Java application designed to provide information about movies and actors.

It fetches data from external APIs to retrieve details such as movie plot, ratings, actors, director, box office, net worth of actors, etc.

## Description 
- The Movie and Actors Info System is a Java-based application designed to provide users with comprehensive information about movies and actors.

It utilizes external APIs such as OMDB API for movie data and API Ninjas for actor information.
The program consists of three main classes: Movie, Actors, and Main.

- The Movie class handles movie-related functionalities.

This class allows users to search for details about movies including:<br>
title, release year, runtime, genre, director, writer, actors, plot, country, language, ratings, IMDb votes, and box office information.

- The Actors class is responsible for actor-related functionalities.

This class allows users to search for actors and retrieve information such as:<br>
net worth, nationality, occupations, height, birthday, and date of death (if applicable).

- The system enhances user experience by handling invalid inputs gracefully, prompting users to re-enter valid information.

## Getting Started

### Dependencies

- Java Development Kit (JDK) installed on your system.
- Gradle installed on your system as a package manager.

### Installing

- Clone or download the Java files from the repository.

### Executing program

- Compile the Java files using the following command:
```
javac Main.java
```
- Run the compiled program using the following command:
```
java Main
```


## Help

- Ensure a stable internet connection.
- Verify the correctness of the movie or actor name entered.

## Authors

Danial Taghipour
 
Email: [prof.danial4@gmail.com](https://twitter.com/dompizzie)

## Version History
* 0.6
    * fixed some small bugs with test cases
    * added error handling for invalid inputs
* 0.5
    * added get height function
    * edited error handling function
    * added get plot function
    * some minor edits to other functions
    * added get title function
    * fixed some small bugs
    * added get language function
    * added get run time function
    * display for new fields added
    * added get box-office function
    * added get writer function
    * some minor edits to other functions
    * added get director function
    * fixed some minor bugs
    * added get release date function
    * added get genre function
    * changed the display of imdb votes
    * added get country function
    * added get birthday function
    * removed some small mistakes
    * added a new get ratings function
* 0.4
    * actors api key added to the class
    * completed get actor data function
    * fixed some minor mistakes
    * comments and explanations added
    * completed get net-worth function
    * error handling when actor's not found
    * completed is alive function
    * new constructor added for dead actors
    * completed get date of death function
    * added interface to run menu function
    * completed main function
    * added search for movie function
    * added error handling when movie is not found
    * comments and explanations added
    * menu added with user inputs
    * added search for actor function
    * added error handling when actor is not found
    * exit option created
    * handling when user inputs invalid character
    * fixed some minor mistakes
    * added another get rating function to manage test cases
    * some minor edits to other functions
    * some comments and explanations added
    * added a bonus get nationality function
    * constructor added to manage nationality
    * some minor edits to other functions
    * added a bonus get occupations function
    * constructor added to manage occupations
    * some comments and explanations added
    * fixed constructor inputs to include nationality
* 0.3
    * completed get imdb votes function
    * new constructor added for strings array
    * completed get ratings function
    * some minor edits to other functions
    * completed get actors list function
* 0.2
    * movie api key added to the class
    * fixed some minor mistakes
    * some comments and explanations added
    * error handling when movie's not found
* 0.1
    * initial release

## License

This project is licensed under the [MIT](https://opensource.org/licenses/MIT) License. See the LICENSE file for details.

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)
