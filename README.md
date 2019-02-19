# FooBall
Year 11 Digital Technologies assignment

The raw code for this project can be found in FooBall/src with FooBall_App.class being the main class.



# Storyboard/Roadmap

The objective of this application is to recreate the classic Doccy Jo bouncing balls tutorial, but expand by instead of having a game, make more of a sanbox application where users can mess with the balls and change things like the colours, physics, size etc.

(?) = possibility but not necessary

- <s>Have balls bouncing around,</s>
- <s>Make mouse interactions: Accelerate towards mouse,</s>
- <s>Have balls react to gravity,</s>
- Have a UI for the user to interact with the balls from
- Add pause and continue functionality
- Select individual balls while paused - edit individual ball's settings' (?)
- Dynamically alter number of balls (?)
- <s>Ball collisions</s>
- <s>Ball bouncing off each other</s>

## Details of game
Balls will react to gravity and manipulate themselves in terms of movement

## controls
Click to apply a force of acceleration towards the mouse on all balls

Mess with settings as you please










<s>
Overly detailed and unnecessary ramble about project
I chose to do this as my computing project for a few different reasons, the first being that in my time that I have been programming, I have done a lot of projects that are simply recreations of famous/popular games. While I believe this is a very good idea to do every once in a while, as it allows you to practice problem solving strategies and gives you a very specific goal that you will need to work towards, I decided that I would like to make something original, and I am not aware that I have seen anything else with quite this concept. Another reason is that I wanted to create something with a degree of physics that I was comfortable I would be able to sit down and figure out how to implement. I have not done anything really with physics before, the most I have done is pretty much just manipulating spirals (Using the unit circle, Sin, Cos, etc) and some simple systems that influence each other (Conway's Game of Life, a cellular automaton. Yet I did not want to do something too complicated, for example, I am not familiar enough with the physics of gravity to be able to program meaningful gravity (something that resembles gravity instead of a simple jump up and down with constant velocity and no acceleration), and while I would like to do that, I feel that if I were to make something with gravity, I would most likely copy the code from elsewhere on the Internet, which is not very good problem solving, so I will save the for a slightly later date.

At the point of adding the graphics so that the game actually did stuff that the user would see, I realised how simple it would be to change the Legend's own classic bouncing boxes tutorial into the original idea I had for this project (to have the boxes move around dependent on the mouse position) which was just to add an acceleration vector that was a fraction of the difference between the location of the mouse and ball. I wasn't going to leave it at that, so I experimented a bit and added a constant downwards acceleration to act as a sort of gravity, which worked perfectly, I tweaked it to how I wanted it and devised a (good enough) fix for the bug where the ball would accelerate through the ground.

Now the game side of the program was pretty much finished, I needed to move onto the customization part of it, the right panel which holds all of the settings the user can change while playing.
I believe in people being able to have as much control over what they do with my programs as they want because everyone is different, they should be able to customize it to how they want it themselves, I would certainly want that. From my perspective, I can customize the game as much as I wish by tweaking things in the code, so usually it wouldn't matter, but what if this were to become something that others used? They would still want that, so I want to develop my projects as accessible to everyone as possible, allowing them to easily be able to change anything they like, the gravity, the speed, the slowness, number of balls, colour of balls, everything, so to make that functionality of the program easy to access by everyone, I place all the settings that I would want to have control of in a sidebar, or in some other programs, on another frame altogether.

# Structure of development <15/2/19
To start with, I did my usual program setup that I use for any programs that have a graphical interface:
I create a main class with a main method,
I create a class for the JFrame object for the window that will be the game,
I create a couple classes to hold the JPanel objects which will hold the graphics and any features that the user interacts with.

From the main method, I create an object of the JFRame class to create the window, I then get that to Create the two JPanels, that will make up the graphical experience,and add them to the JFrame so I can configure the size and layout to be how I want it to be.

# Update (Sat) 16/2/19
Finished setting up the frame and windows as these will be an essential part of this program

Set up changelistener for UserPanel object - this will be an important part of having the program customisable by the user while it is running

Set up multi-threading for my program - 
A known "issue" with java is that when calling the repaint() method in a loop, the action to repaint the screen is queued on the stack however does not actually get run as if it is only running on a single thread, the time taken to repaint may hold up the program so it is skipped. The solution to this is to multi-thread to the process and run the gameloop in a separate thread, this is something that I have had to do any time I want to repaint on a timer and have had to multi-thread most graphical programs.

Created the ball object to make interacting with the ball easier - also allows program to be scaleable to have multiple balls with the simple addition of a for loop and array.

Added some aesthetic changes to the panels
</s>


