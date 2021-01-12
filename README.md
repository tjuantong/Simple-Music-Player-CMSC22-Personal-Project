# Simple Music Player (CMSC22 Personal Project)
The simple music player is a command-line program which is coded through the use of Java. This is done for the fulfillment of my CMSC 22 Personal project, which aims to apply the basics of Java programming, implementation of design patterns, test-driven development, and the best coding practices. The program used one of each design pattern for each category which is the Builder, Facade, and Iterator design pattern. The program also followed the Test-Driven Development cycle in creating the program with the use of unit tests. The organization and code structure also implemented various best Java coding practices.

### External JAR file used for the program:
* JACo MP3 Player

## Project Requirements
1. Design Patterns (atleast one per category)
2. Test-Driven Development
3. Java Best Coding Practices applied

### Simple Music Player Functionalities:
1. Play / resume song
2. Pause song
3. Next song
4. Previous Song
5. Skip to a certain song
6. Also has the option to print the song list and the commands to input for the player’s function.

## Design Patterns used in the project:
1. Creational pattern (Builder design pattern) - it creates the object which contains the song path and songs to add to the playlist. It is also used for cleaner and more comprehensible code in creating the object. Using this pattern enables the user to input the necessary information needed in the code. In this case, both the song path and the list of songs are needed for the program.

2. Structural pattern (Facade design pattern) - It provides the implementation of the object into one class only so that the user need not to know about how everything works. From the name itself, it provides functionalities of the music player without the user knowing how it is being implemented which makes it easier to avoid the                     complexities being done in other classes.

3. Behavioral pattern (Iterator pattern) - It is used to iterate over the set of songs in the list without having to expose its underlying representation. Also, it is used in printing the songs in the list until it wouldn’t return anything and has traversed all elements on the collection.

## Java Best Coding Practices applied:
* Naming conventions are followed: 
      camelCase - naming conventions for methods, or variables
      PascalCase - naming conventions for Class names
* For better readability and organization of the code:
      - Consistency in indention of codes also applies for adding elements in a collection
      - Provided brackets in conditional statements
* Used switch cases and provided a default when user input doesn’t satisfy any of the cases, instead of numerous if-else if statements for commands of the music player which provided neater look and code readability.Used ArrayList to provide more flexibility on the elements that the user will input for the playlist.
* Classified the class variables in terms of visibility.
* Setting variable names in a short but descriptive manner for easier comprehension.
* Handle exceptions that might be thrown and customize its message to prompt the user of the problem.
