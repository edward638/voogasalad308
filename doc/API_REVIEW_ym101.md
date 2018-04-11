## [VOOGASalad](https://www2.cs.duke.edu/courses/compsci308/spring18/assign/04_voogasalad/index.php) : API Review

### Part 1

1.  What about your API/design is intended to be flexible?
    Our overall design is intended to be both flexible from a program standpoint as well as a user standpoint. From a design standpoint we want each of the different sections of out program (Engine, Authoring, and Player) to be independent and self-contained, but also be flexible to both add more features to each of the different sections, as well as adding more features the user can use/see in the game they create and play. 
2.  How is your API/design encapsulating your implementation decisions?
Specifically, the Engine API is designed to be flexible by both allowing for the playing on any game authored by a user, by simply taking in the file for the game and loading/populating the game from the structured game data in that file. Also the Engine is flexible in that we want to allow the objects in the game to have multiple behaviours (like move, shoot bullets, etc) and these implementations live in the engine.
3.  How is your part linked to other parts of the project?
The Engine will be called in the Player once a game is selected (and the file path for the game data is passed to the engine) and return a JavaFX object to be displayed in the player. The Engine populates the game with game objects by reading information from the game data created by the authoring side.
4.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
One exception we might run into is the Engine trying to instantiate objects with bad data when reading from the game data file.
5.  Why do you think your API/design is _good_ (also define what your measure of good is)?
I think we have good design because each section is encapsulated and open to the implementation of more features.

### Part 2

1.  Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
The use cases are pretty straightforward and reasonable of a game engine, but translating that into a code for abstract objects might be harder that it seems.
2.  Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
Again, the fact that we will have to make sure our Engine works for a variety of objects with vastly different behaviors will make this a challenge.
4.  What feature/design problem are you most excited to work on?
Making sure our engine works correctly for all different types of behaviours, aka game testing.
6.  What feature/design problem are you most worried about working on?
The same thing as mentioned above, because it will be time consuming to test that everything works properly and debugging edge cases will be difficult.
8.  What major feature do you plan to implement this weekend?
Displaying the active game elements to the JavaFX object and returning it to the player.