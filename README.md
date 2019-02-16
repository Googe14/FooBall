# FooBall
Year 11 Digital Technologies assignment

The raw code for this project can be found in FooBall/src with FooBall_App.class being the main class.

# Writeup
I chose to do this as my computing project for a few different reasons, the first being that in my time that I have been programming, I have done a lot of projects that are simply recreations of famous/popular games. While I believe this is a very good idea to do every once in a while, as it allows you to practice problem solving strategies and gives you a very specific goal that you will need to work towards, I decided that I would like to make something original, and I am not aware that I have seen anything else with quite this concept. Another reason is that I wanted to create something with a degree of physics that I was comfortable I would be able to sit down and figure out how to implement. I have not done anything really with physics before, the most I have done is pretty much just manipulating spirals (Using the unit circle, Sin, Cos, etc) and some simple systems that influence each other (Conway's Game of Life, a cellular automaton. Yet I did not want to do something too complicated, for example, I am not familiar enough with the physics of gravity to be able to program meaningful gravity (something that resembles gravity instead of a simple jump up and down with constant velocity and no acceleration), and while I would like to do that, I feel that if I were to make something with gravity, I would most likely copy the code from elsewhere on the Internet, which is not very good problem solving, so I will save the for a slightly later date.

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



