# Finch Project

Finch Robot Individual &amp; Group Project Task (GUI, Search Object, Navigation, Zig-Zag, Light Guidance, Draw Objects).

The project integrates several mini-projects using the Finch robot.

## [Task 1: Search for Light](https://github.com/Goswami-Pratik/Finch/tree/main/Individual%20Tasks/src/searchLight)&nbsp;🔦

The purpose of this program is to make your finch robot wander around and search for a light source.

To start the program, the finch should be placed level on the floor. This should make the finch, first, turn the
LED colour in its beak to yellow, and, then, start moving forward at a low speed. When the finch encounters
light, it should change the LED colour to red and it should start following the light.

The brighter the light is, the more red the beak should be.

If the finch has not encountered a light source for 4 seconds, it should stop for half a second and change
direction 90 degrees to either left or right. Then, it should start searching again.

The program should stop when the user picks the finch up and places it on its tail.

Before the program terminates it should ask the user whether it should display the log of the execution. If the
user responds ‘Yes’, the program should display the following information:

- The left and right light sensor values at the beginning of the execution.
- The highest light sensor value recorded during the execution.
- The lowest light sensor value recorded during the execution.
- The average light sensor value recorded.
- The duration of the execution.
- The number of times the finch detected light.

This program can be implemented using a Graphical User Interface (for example, windows, buttons, text boxes,
drop-down menu, etc.) or a console user interface (input and output printed in the console).

## [Task 2: Draw Shape](https://github.com/Goswami-Pratik/Finch/tree/main/Individual%20Tasks/src/drawShape)&nbsp;✏️

The purpose of this program is to make your finch robot draw a shape. The finch should be able to draw squares
and triangles.

It is possible to attach a pencil to the finch, preferably close to the wheel that is not moving, and get a decent looking drawing.

To start the program, the finch should be placed level on the floor.

When the program starts, it should ask the user to enter a shape for the robot to draw (square or triangle), or
to quit the program, using one of the following three commands:

- S (to draw a Square)
- T (to draw a Triangle)
- Q (to Quit and write log file)

If ‘S’ is entered, the user should be asked to enter the length of the square side. The length of the side should
be a positive integer number (between 15 and 85 cm). If the user enters invalid values, the program should
inform the user about the type of error and ask them to re-enter the value.

If ‘T’ is entered, the user should be asked to enter the length of the three sides of the triangle (between 15 and
85 cm). The side values should be positive integer numbers. If the user enters invalid values, the program should
inform the user about the type of error and ask them to re-enter the values. Once valid values have been
entered, the program should also check whether the entered numbers for the length of the sides could form a
triangle. Again, if a triangle cannot be formed with the given values, the program should let the user know and
ask them to re-enter the values. Before attempting to draw the triangle, the program should determine triangle
angles.

Once valid values have been entered, the wheels of the finch should start moving at a low speed.

Given that the user enters the length of the sides of the shape (‘distance’) in centimetres, you need to calculate
the seconds/milliseconds that the finch should move for – with that speed – in order to cover the distance that
the user has input. You can calculate the time that the finch should move for by experimenting with your finch
and using simple mathematics.

When the drawing of a shape is completed the finch should stop and beep once. The program should carry on
prompting the user to select a shape to draw until the user enters ‘Q’. When ‘Q’ is entered, the program should
write the following information to a text file.

- The names and the sizes of all drawn shapes as well as the angles of the triangles, in the order they
were drawn. For example: ‘Square: 60, Triangle: 60, 45, 75 (angles: 53.13, 36.87, 90), Square: 30
- The ‘largest’ shape that was drawn, and its size. For example: ‘Square: 60’
- The shape (triangle or square) which was drawn most frequently, and how many times it was drawn.
For example, ‘Square: 2 times’.
- The average time it took to draw the shapes. For example, if Finch had to draw 3 shapes, which took
20, 35 and 19 seconds, then in the file you should write: 24.6 seconds.

The program can then terminate.
This program can be implemented using a Graphical User Interface (for example, windows, buttons, textboxes,
drop-down menu, etc.) or a console user interface (input and output printed in the console).

If the user enters any other letter besides ‘S’, ‘T’, and ‘Q’, the program should inform the user about the type
of error and ask them to re-enter their command.

## [Task 3: Navigate](https://github.com/Goswami-Pratik/Finch/tree/main/Individual%20Tasks/src/navigate)&nbsp;🚗

The purpose of this program is to navigate the finch robot using commands entered from the computer.
The available commands are:

- F (for Forward movement)
- B (for Backward movement)
- R (for Right turn)
- L (for Left turn)
- T (for reTracing previous movement(s))
- W (for Writing the log of the commands to a text file)
- X (for eXecuting commands from a text file)
- Q (to Quit the program)

For the commands ‘F’ and ‘B’, the user should also enter two integer values. The first value should set the
duration of the move in seconds and the second value should set the speed of the finch. No movement should
last more than 6 seconds. The speed should not exceed 200. For example, F 4 100 should be interpreted as a
command to move the finch forward for 4 seconds at a speed of 100; B 6 200 should be interpreted as a
command to move the finch backwards for 6 seconds at a speed of 200.

For the commands ‘R’ and ‘L’ the user should enter two integer values. The first value should set the duration
of the move in seconds and the second value should set the speed of the finch after the turn is made. No right
or left movement should last more than 6 seconds. The speed should not exceed 200. Assume that the right/left
turn is always orthogonal to the current course. You should work out what values you should set the speeds of
each wheel to, so that the finch turns orthogonally.

Your program should make appropriate checks in order to ensure that the input values are within the valid
range.

For command ‘T’, the user should enter an integer value. This value should determine how many previous
movements the finch needs to retrace. For example, if the finch has so far executed ‘F’, ‘B’, ‘L’, ‘R’, ‘F’, and the
user enters ‘T 3’, the finch should execute again ‘F’, then ‘R’ and then ‘L’ (i.e., the previous 3 movement
commands starting from the last one). Of course, the number of movements to retrace cannot exceed the
number of the movements that the finch has executed so far. In the example above, if the user enters ‘T 6’, the
program should let the user know that ‘6’ exceeds the number of executed movements. Please note that when
‘T’ is entered, only previous movement commands should be repeated and not any previous ‘T’ commands.

For command ‘W’, the program should write the current time in HH:MM:SS format and all the commands
(including the ‘T’ commands) that the finch has received to a text file.

For command ‘X’, the program should open and read an existing text file that contains at least three commands
(for example, ‘F 4 100, B 6 200, T 2, L 2 170’) and have the finch executing these commands one by one.

When command ‘Q’ is entered the program should terminate.

This program can be implemented using a Graphical User Interface (for example, windows, buttons, textboxes,
drop-down menu, etc.) or a console user interface (input and output printed in the console).

If the user enters a letter that does not correspond to a command (e.g., ‘Z’), the program should inform the
user about the type of error and ask them to re-enter their command.

## [Task 4: Zigzag](https://github.com/Goswami-Pratik/Finch/tree/main/Individual%20Tasks/src/zigZag)

The purpose of this program is to make your finch robot move in a regular zigzag fashion.

It is possible to attach a pencil to the finch, preferably close to the wheel that will not be moving, and get a
decent-looking drawing.

To start the program, the finch should be placed level on the floor.

For this task, we will assume that a zigzag path consists of lines of equal length that are orthogonal to each
other. At the beginning of the program the user should be asked to enter the length of a zigzag section in
centimetres and the number of zigzag sections (all sections are of the same length). For example, the zigzag in
the illustration below consists of 8 zigzag sections.

The length of a zigzag section should be at least 15 centimetres and no more than 85 centimetres. The number
of zigzag sections should be even and should not exceed 12. Your program should check that valid values are
entered. If this is not the case, it should generate appropriate error messages and should ask the user to reenter valid values. 

The speed of the wheels of the robot should be randomly generated. Given that the user enters the length of
each zig zag section (‘distance’) in centimetres, you need to calculate the seconds/milliseconds that the robot
should move for – with that randomly generated wheel speed – in order to cover the distance that the user has
input. You can calculate the time that the robot should move for by experimenting with your robot and using
simple mathematics.

Once the length and the number of zigzag sections have been given, the finch should set the LED in its beak to
green and start moving forward until it moves for the given zigzag section length. The finch should then stop
for one second, turn orthogonally to the path travelled and start moving in the new direction for the length of
the second zigzag line. As it is traversing the second section, its LED should change to blue. At the third section,
it should change its LED back to green (like in the first section of the zigzag). In the fourth section, the finch
should go back to blue, and so on. 

Once the finch has completed traversing the given number of zigzag sections, it should turn around and retrace
its movements until it reaches the original starting position (displaying the same LED colour as when it first
traversed each section). When the finch robot reaches the initial starting position, it should stop and turn off
the LED and the sound. Next, it should write the following information to a text file:

- The user inputs (that is, length of each zig zag section and number of zig zag sections).
- The randomly generated wheel speed.
- The length of the traversed path (start to the end position of the zigzag, but not including traveling
back).
- The duration (in seconds) that the robot took to complete the move from START to END.
- The distance traveled (‘straight line’ distance) from the START to the END of the zigzag.

This program can be implemented using a Graphical User Interface (for example, windows, buttons, textboxes,
drop-down menu, etc.) or a console user interface (input and output printed in the console).

## [Task 5: Detect Object](https://github.com/Goswami-Pratik/Finch/tree/main/Individual%20Tasks/src/detectObject)

The purpose of this program is to make your finch robot either follow or avoid an object.

To start the program, the finch should be placed level on the floor.

The program should ask the user to select between the ‘Curious Finch’ mode, the ‘Scaredy Finch’ mode or the
‘Any’ mode.

Then, the finch should start wandering around at a low speed until it encounters an object.
If the user has selected the ‘Curious Finch’ mode, the LED should turn blue, when it encounters an object. If the
object starts moving, the finch should turn the LED to green and start moving behind the object. Every time the
object stops moving, the finch should also stop, and it should turn its LED from green to blue. Every time the
object starts moving again, the finch should change the LED back to green, and start following the object again.
If the finch has not encountered an object for five seconds, it should wait for a second, and start moving again
in a slightly different direction.

If the user has selected the ‘Scaredy Finch’ mode, when the finch encounters an object, it should beep once,
back up and turn in the opposite direction and move away from it for three seconds. The beak should be set to
green when the finch is moving around, and it should change to red when the finch sees an object and is moving
away from it.

If the user has selected the ‘Any’ mode, then the program should randomly choose and execute either the
‘Curious’ or the ‘Scaredy’ mode.

The program stops when the finch is picked up and placed on its tail.

Before it terminates, it should ask the user whether they would like to view the log of the execution. If the user
responds ‘Yes’, the program should display the following information:

- The mode it ran (‘Scaredy Finch’ or ‘Curious Finch’).
- The duration of the execution.
- The number of times the Finch encountered an object.

This program can be implemented using a Graphical User Interface (for example, windows, buttons, textboxes,
drop-down menu, etc.) or a console user interface (input and output printed in the console).

## [License](https://opensource.org/licenses/MIT)

By contributing, you agree that your contributions will be licensed under the MIT license.
