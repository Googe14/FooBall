# FooBall
St Philips College - Q1 2019
Year 11 Digital Technologies assignment

The source code for this project can be found in FooBall/src Starting at FooBall_App.class with multiple compiled versions at different points in development.

This project is very heavily commented for your convenience

# Storyboard/Roadmap

The objective of this application is to recreate the classic Doccy Jo bouncing balls tutorial, but expand by instead of having a game, make more of a sanbox application where users can mess with the physics of the balls.

<h1>Objectives:</h1>

- Create frame with balls
- Have a side bar for settings
- Create a number of balls that will bounce and interact with each other
- Have a number of settings for people to play around with

<h4>Interactions between balls</h3>

- Collision detection between circles
- Seperation method to move circles out of each other if they go into each other
- Collision physics (2D elastic collisions between circles)

<h4>Possible Extra features:</h3>

- Add pause and continue functionality
- Select individual balls while paused - edit individual ball's settings'
- <s>Dynamically alter number of balls</s>
- Multiple game modes

# Expected input

<h3>Changing of settings:</h3>

- Gravity (slider)
- Mouse Strength (slider)
- Air resistance (slider)
- Mouse mode (radio button)
- Type of collisions (check boxes)
- Scope of mouse effects (radio buttons)
- Number of balls (spinner)

<h3>Mouse clicks:</h3>

- Attract balls
- Repel balls
- Grab balls
- Slingshot balls

# Expected output

Balls will interact with each other accordingly (bouncing, colliding etc) whilst any mouse input with the user inside the black panel will also affect the balls depending on what mode the user has selected.


# Design Goals/Structure

- Create JFrame/window to hole contents
- Create Panel to display graphics in Frame
- Write game loop to time events of program
- Create objects to hold data for objects 
- Create boundaries and write physics to redirect balls at boundaries
- Write collision detection between balls
- Write physics for colliding balls to bounce off each other

<h3>Boundaries of boxes</h3>
Each PhysicsObj object stores an array of integers indicating the distance from the origin (top left, [0,0]) which marks it's boundary. i.e. how far it is allowed to travel before it bounces, in order from the left side, clockwise around the frame, to the bottom.

```java
//Bounce the ball
		//Bounce on Right side
		if(x >= xBounds-size/2) {
			xVel *= -1;
			x = xBounds-size/2;
		}
		//left side
		if(x <= size/2) {
			xVel *= -1;
			x = size/2;
		}
		//bottom
		if(y >= yBounds-size/2) {
			yVel *= -1;
			y = yBounds-size/2;
		}
		//top
		if(y <= size/2) {
			yVel *= -1;
			y = size/2;
		}
```
[FooBall/src/FooBall_PhysicsObj.java]





# What I <s>leanred</s> learned/gained

- How to spell "learned"
- Elastic collisions between balls
- Conservation of momentum and energy
- Experience on better and more efficient application structure
