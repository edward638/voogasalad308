# voogasalad - 2 Dessertz

### names of all people who worked on the project

Gouttham Chandraekar, Jeffrey Li, Calvin Ma, Yashas Manjunatha, Martin Muenster, Trishul Nagenalli, August Ning, Summer Smith, Maddie Wilkinson, Edward Zhuang

### date you started, date you finished, and an estimate of the number of hours worked on the project

Started: March 20th

Ended: April 29th

Hours worked: 10^3

### each person's role in developing the project

Gouttham: Worked with Trishul, Martin and Yashas to create a game engine. Helped design the engine's code structure as well as implemented specific behaviors, and actions that GameElements can inherit.
Worked on coding the game-flow allowing user to travel from one gameLevel to another. 

Jeffrey: Worked with Calvin to make entire front end and back end of GamePlayer which manages selecting games, loading games, saving games, high scores, HUD,
changing key bindings dynamically, handling username, loading games from online.

Calvin: Worked with Jeffrey on the gameplayer. The gameplayer was used in order to initialize our engine and give the user some flexibility 
in terms of what games they want and how they wanted to interact with a game outside of the game itself such as keybindings and volume. Worked
the backend and frontend of this part, but mostly frontend as gameplayer was mainly a frontend thing. Also created two utilities for our group, 
one of which could not be reviewed by Facebook so we can't use it. Did like a couple things for the frontend for authoring. 

Yashas: Worked with Gouttham, Trishul, and Martin on the game engine component of the project. Helped design the inital structure for the Engine. Worked on implementing the display and audio aspects of the Engine. Also worked on impelemnting behaviors and game events related to audio playing, and level flow/transition. Also, worked with Gouttham to do a major refactoring of the Engine design to be more flexible for level flow and better understandable. Also, worked with Player team to integrate Engine display and Player's HUD and with Game Data team to integrate game data with Engine to be able to load and save games in Engine.

Martin: Worked on developing game engine. Developed collision logic, experimented with potential collision utility, created initial testing scenarios, integrated shape behaviors and hitboxes, worked on designing and writing code for game and element events, created old GameMetaData with Gouttham, made design decisions related to integrating game player with engine.

Trishul: Worked with Gouttham, Trishul, and Martin to build the GameEngine part of the project. We collaborated to come up with the original design for composition style back end we have for the Engine. I built the GameElement class and many of the behaviors like Movable, Shootable, Movable Character, Time Routine, etc. I also worked with authoring to build the conversion tool from authoring environment objects into their engine equivalent. 

August: Worked with Edward to make game data to save and load various authoring and frontend elements to XML using XStream. Made various Authoring GUI frontend components such as the Event pop up window, Duplicate button, GameObject resizing, etc.

Summer: Maddie and I worked together on the authoring environment. We decided how the back-end should be set up, whether it should use the back-end engine or not, and how the GUIs should look. This would shape the user's game creating experience. In addition, I worked on integrating Groovy into the authoring environment and the engine.

Maddie: Worked with Summer to design the authoring backend; designed and implemented the initial authoring frontend before major refactorings midway through the project; made various authoring frontend components and popup windows

Edward: Works with August Ning to create the game data component of the project. Used XStream based serialization to store data files for game authoring and game engine analysis. Also worked extensively
on authoring component of project with August, Summer, and Maddie.
### demo video link
[Demo video](https://drive.google.com/file/d/1wH8nZ2e_FIRgZW0fB2MYkwMlT3A_LPkR/view)


### any books, papers, online, or human resources that you used in developing the project

Java doc resources, XStream, Dropbox API, stack overflow, webpage resources

### files used to start the project (the class(es) containing main)

Main class within the display package

### files used to test the project and errors you expect your program to handle without crashing

You can try out the game by loading enginetestmario in Authoring, or playing Demo308 in Player.

### any data or resource files required by the project (including format of non-standard files)

Remember to include all necessary .jar files to run the game, such as XStream, reflection packages, and Dropbox API.

### any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)

It is intuitive and has a lot of click and drag. It also uses Groovy for handling behaviors!

### any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements

We took out a lot of possible flexible features from the Authoring environment to prevent user error. This includes things such as using non letter character keybindings, only one main character, etc. When a game is created, the necessary image and music files are always generated within the game folder. Therefore we assume that these images and music files always exist. (the only reason this wouldn't be the case is if the user goes into the game folder and deletes these files).

### any known bugs, crashes, or problems with the project's functionality

Ocasionally, the hitbox for elements that contain the boxlike behavior will malfunction, and other elements will fall through the box. In the authoring environment, the user must set audio for each scene or else loading the game will cause the program to crash. There is also no error checking for the Groovy Scripts, because we assume that the user will follow the instructions for the auto generated scripts. The game will also not function if there is not a GameObject with the MainCharacter behavior.

TimeRoutines occasionally do not execute on GameElement in GameState likely due to the references passed into groovy actions not being the GameElement in the GameSatate.
Improper conversion from GameScene to GamePart will sometimes introduce new GameElements that contain only a mandatory behavior - origin unknown.
Display and Hitbox Height and Width default to 1 pixel across and are sometimes not overridden by Library GameObjects
Must manually transfer images from GameObject library folder "default images" into Game specific folder
ShapeRoutine Behavior causes limitations on movement of other GameElements, seemingly by creating aformentioned random objects Object with ShapeRoutine also doesn't appear

When you add an object to the GameViewWindow for the first time, the size may be distorted. This can be fixed by moving the object and the size will correct. There are also issues with deleting and duplicating GameObjects, as you have to move a GameObject to force the frontend to update. Whenever you open a level, the level pane is set to 1000x1000 by default, and if you want to see all the items on your GameScene, you will have to resize each time. If you have too many GameObjects, the authoring will run very slowly.




### any extra features included in the project

You can upload and download games to and from Dropbox to share with your friends. Possibility for shapes that trace the outside of images, given a super fast computer.

### your impressions of the assignment to help improve it in the future

Gouttham:

Jeffrey: It was a fun and useful project that gave good exposure to what it's like working in a large team.

Calvin: I think the hardest part of this project was integration, which makes a lot of sense. When we split up into our own groups, we will
naturally have conflicts between parts of the project that we end up having to spend a lot of time fixing. This integration also speaks to
the flexibility of the code. If the code is flexible, then integration shouldn't be a huge issue. Also, I think there could be more strict
guideline for what each group could have accomplished by the demo. Looking back now, I feel like we were behind at the first demo point 
even though I had thought we were pretty on track at the time of the demo. 

Yashas: This project was both very challenging and very rewarding. The aspect of designing the project to be both flexible and follow good design principles, while also 
maintaining practicality and functionalty proved to be especially challenging because of the difficulty with integrating all parts of the project. Also, working with a 
large group like this was a great learning experience because with so many voices and opinions in the conversation, communication was probably the most important aspect 
of the project. Overall, I learned a lot about making the decisions to balance design and functionality, as well as having meaningful but also efficient conversations
about design and goals with a large group of people.

Martin: I really enjoyed planning this project, and figuring out how each team would do its part to create a finished product. It really helped solidify my understanding of internal/external APIs, as we had 4 distinct parts of the project that needed to communicate with each other through private channels. Overall, it was fun but very tough.

Trishul: This project was a very interesting challenge with a completely open-ended goal that allowed us to be as creative as possible in maintaining quality flexible code. It was certainly a huge challenge staying tied together to a large group of 10 people and communicating effectively between us but I am very happy with the team I worked in and enjoyed the experience of learning how to work in such a large team. I feel learning how to use Git has been an especially important feature of this project that I was not expecting to learn so much about.

August: Project was really hard, and learned a lot about designing good and flexible code. It does take a lot of time and lots of debugging to get external jar files to work and getting consistent classpaths. Overall a worthy endeavor that made me learn that I don't like programming. However I feel like this project is seminal to the legend of the CS308, so I would be sad if future students got it easier. 

Summer: I liked how the project mirrored working on projects in the real world (at least this is how it felt). The large groups and large task mirrored a software-engineering on-the-job project. The most difficult part was keeping up with the changes that everyone had made and maintaining good communication throughout the project. It was easy to lose track on the progress of the parts of the project that you were not working on. I think some stronger guidlines as to where teams should be at the midway demo would be helpful, as we were unsure how much progress was a good amount. However, this unsureness could be an important part of the assignment as well.

Maddie: This project was obviously very difficult, but I feel like I learned a lot not only about designing good code, but also about teamwork and project management. The open-endedness of the project was pretty intimidating, but I think that it forced us to think harder about how our design would fit together and about the many nuances of the functionality, which was a good learning experience.

Edward: I think that this was a very fun yet extremely challenging project. After working in small groups for both Cell Society and SLogo,
I found it much more difficult to stay organized with a larger team, especially given the magnitude of the task. For this project, good communication is incredibly valuable. 
While I did appreciate the complete flexibility of this project, I think that having suggested goals to achieve by specific times would have been helpful 
(ex. suggested dates by which to complete general authoring, etc.). Furthermore, it may be useful to have students give feedback about the project 
for future students to take advantage of. Code-wise, I think the most difficult aspect was integrating the authoring and engine components. Overall, though, I really enjoyed this project.


