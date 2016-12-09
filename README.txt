CS 3050 Group Project

TA Meetup needed first week after break!
Nov 28 - Dec 2

Due: 12/8/16 at 11:59:59 PM

Group Members:

Michael Esker - mte8bd
Jay Whang     - jw8n5
Zhi Zhang     - zzxh5

This project is about planning the motion of a robot in a grid from a start point to a finish point
while avoiding the paths of two moving obstacles.  Otherwise, the robot dies!

How to run our program:

Open our project in the NetBeans IDE and click Run.
Wait for the file chooser to open, then continue.
Locate the test file to run, in this case "room*.txt" which is located in src/project/room.txt from the initial directory.
Click Open or press the Enter key on your keyboard.
Enjoy the show.

Please run the following files to see their outputs:
room_good1.txt
room_good2.txt
room_good3.txt
room_bad_size.txt
room_bad_location.txt
room_bad_format_parentheses.txt
room_bad_format_signs.txt
room_bad_direction.txt
room_trapped_end.txt

How to interpret the results:

If the room is not properly formatted, or if the robot cannot find a path, such will be output to the system terminal.
If the room is valid, the following will occur.
On the graphical user interface (GUI), the room will be drawn as a set of squares, each with colors representing open squares, the robot, its path, the two obstacles.
The robot's path is traced as the robot moves and is gold, and it's end point is green.
The two obstacles are blue, and their current location is shown.
The system terminal shows the current position of the robot as it moves.
The movement and terminal cease to update once the robot successfully reaches its end point.
