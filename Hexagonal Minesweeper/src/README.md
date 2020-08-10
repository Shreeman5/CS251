MineSweeper                                                                                                                                                              by: Shreeman Gautam

1. HOW TO PLAY

In the command line use : java -jar MineSweeperGame.jar HARD highscores.txt or  : java -jar MineSweeperGame.jar EASY highscores.txt to start the game. The game is played by clicking 
on a cell within the rhombus structure. The game is solved using recursion. If you left click on an empty cell, that cell will be revealed. If it's neighbors are empty, those will be 
revealed as well. That goes on until the recursion reaches a number or a mine. If you hit a number, that number will be revealed but further recursion won't happen. If you hit a mine, 
nothing happens because you did not click on a mine. The numbers indicate the presence of mines.  If a cell contains the number '1', it means that 1 of the neighboring cells is a mine. 
'2' means 2 of the neighbors are mines etc. If you left click on a cell containing a number, only that cell will be revealed. If you left click on a mine, the game is over. My program 
accommodates flagging as well. Let's say that you unlock some cells. While scanning the board you see that a cell has the number '1', which means that 1 of the neighbors is a mine. 
Let's also assume that only 1 neighbor of that cell is left to be unlocked (assuming here that the other 5 neighbors are empty). So, we can guarantee that, that locked cell is a mine. 
So, to keep track of your mines, whose number is displayed from the start on a label on the other side of the screen, you can flag it using the right click. Let's say you have 13 mines; 
flagging 1 means you have 12 mines left to be found. The positive of this is that, you can see visually where the mines are at instead of jotting it down on a paper. If you right click, 
a big 'F' will show up. If you left click on it accidentally, nothing will happen. Only right clicking that cell will remove the flag. 

2. WIN/LOSS/SCORE

To win, you must unlock every non-cell mine. To lose, you can simply click on a mine. My code detects that using a loop that scans the board after every click.  So, let's say you have 
unlocked all the non-mined cells but may/may not have flagged all the mined cells. Nothing to worry about, because the loop recognizes that you have completed the board regardless of 
the flags you have put down. So, let's assume now that you have won. Only then, will your score count. That score comes from a timer, which starts the moment you click on a cell. 
When you finish the game or click on a mine and lose, the timer stops. If your time is less than what is displayed on the label on the right side of the screen, your time will be 
displayed there. If not, you can try again to solve it faster. After you win a game/lose a game, you will be asked to play again using this method: "JOptionPane.showConfirmDialog" (1). 
If you want to, you can, and a new board will appear with a different mine placement, same number of mines. If not, you can exit the game. Also, if you feel that you are good at the 
EASY level, you can always specify in the command line that you want to play the HARD level. While the EASY level has less mines in proportion to the size of the board, the HARD level 
has more mines in proportion to the size of the board.

3. GAME LOGIC
At the start of the game, the board is set up according to the difficulty level. A HexMineManager object is called which specifies the size, number of mines and random Seed of the game. 
Then the GUI is made in the hexGUI class. The playing board (using the double array board) is made using drawAllHex method of the class hexGUI and the labels, put in white rectangles, 
are made as well. Now, let’s say that you click at a point in the board. That point is converted into standard coordinates using the pixToHex method of the HexGUI class (2). 
If pixToHex returns (-1, -1), it means that the player has clicked outside the board which means that the GUI remains unchanged after the method repaint is called. In the case that 
pixToHex generates viable coordinates, the recursive method/toggleFlag method within HexMineManager is called. In the event of a left click, the recursion is done using the help of 
two double arrays that encompass the board. Array compHex is the array under the hood. If the coordinates generated are the coordinates of a mine containing coordinate of compHex, 
the game is over. If the coordinates generated are the coordinates of a non-mine containing coordinate of compHex, changes are made (according to the emptiness) to the double array 
called dispHex.  The change to dispHex is recorded by the double array called ‘board’ of class MineSweeperGame. Now, board is important because it is the one making the changes 
to the GUI(using repaint) by calling the methods: drawAllHex and finalHexBoard. drawAllHex draws the board again (unchanged) but finalHexBoard reflects the change in the double array 
board and shows the newly uncovered cells. In the event of a right click, toggleFlag is called, dispHex changes, which is documented by the double array board, and using the two methods:  
drawAllHex and finalHexBoard, is repainted again to reflect the changes. Now, you click left/right again, and you repeat the process. This happens until you click on a mine and lose or 
unlock all unmined cells and win. If you want to play again, the double array board is reset, and mines are placed randomly but in differen spots from the previous game. That brings 
me to algorithm. 

4. ALGORITHM

Let’s examine drawAllHex and finalHexBoard. Using the variable boardSize, paint component  generates coordinates like (0,0), (0,1), (0,2) etc. which is sent to drawAllHex and using 
some mathematics, is converted to pixel values. Those pixel values use helper variables like radius, shortSide and length to generate hexagons. After that, the hexagons are filled 
with colors and given borders to separate themselves using the fillPolygon and drawPolygon method respectively. finalHexBoard changes/does not change the GUI everytime a button is clicked.
If the click is viable, the number (or in a flag’s case, F) of the dispHex array is displayed in the screen using the double array board (with 0 being left as a blank). 

Let’s examine pixToHex. Coordinates generation is almost perfect because let's loosely assume that if one cell has 60 pixels, 58 of them will point to the correct cell and 2 of those 
will not. This is achieved using the arraylist that contains points. At the start of the game, the center of every cells is collected. When you click on any pixel, the pixel's distance 
to every cell center is calculated using the Pythagorean theorem. If a distance is less than 35, it means that you are inside of a cell(my hexagon’s maximum radius is 34.3).  
Then, my program sends the center, that is closest, to be evaluated as hexagonal coordinates. If none of the distances are less than 35, it means that you have probably clicked 
outside the board (because if you are inside a cell, you will get at least one distance that is less than 35)and that is dealt with by doing no change to the board. The positive of 
this methodology is that before using it, half of my pixels would point to the wrong cell (because of my erroneous mathematics while constructing the hexagons), but the center pixels 
would always give the correct coordinate. So now, viable pixels will pick the nearest center and that center is evaluated in pixToHex which gives the correct coordinates (If I had sent 
the non-center pixels, there would be error). 

Since my board uses a rhombus structure, arrays match up nicely. So, the first cell is (0,0), the next one is (0,1), the one below it is (1,0) and so on. Placing random mines is another 
algorithm that I want to touch upon. Using the fixed Seed, it is ensured that every game will stick to the same formation of mines it had at the start. However, for a new game, the 
seed is changed which ensures that the number of mines won't change but the placement will. So, for my HARD game, across sessions, I will have 55 mines every time but a different 
placement each time. Now, the labels that indicate time and number of flags will reset after every game. Number of mines will reset depending on the difficulty of the game. The score, 
be it for EASY or HARD, will reset depending whether a high score is reached or not. I also want to indicate that, instead of revealing every non-mined cell at the loss of a game, 
I opted to reveal only the mines and the Professor agreed that it was alright. Please check 1. and 2. to see how I uncover mines and detect end of game.

5. EXTRAS
I have saved high scores to a file. This ensures that, across sessions, data will not be lost, and high scores will be updated through time. I have chosen to show the 3 best high scores 
for both the EASY and the HARD level. Now, I only get to submit the JAR file. So, at the start of the game, there will be nothing to display (since there is no accompanying external file)
(error is handled by checking to see if a file exists) (3). If you win a game, a file called “highscores” will be made in the same directory. Depending on what level of difficulty you 
have played, it will be reflected in highscores. If it’s an EASY game that you have won, it will be shown as “E60”, for example(60 being seconds). For a HARD game, the score will be 
reflected as “H700”, for example. Now, let’s say you have won one game. Immediately after you win, your score is reflected on the label. If you play, and win several games, you can see 
the label update accordingly. Eventually, after a point, if you have won enough, high scores replace one another (using the collections.sort() method that put the shortest times in the 
front). Even if you close the game window, the scores stay there, given that the highscores.txt file exists in the same directory. 

6.BUGS
I chose to make a rhombus, thinking that it qualifies as a hexagonal board. I should have asked the Professor about this and I did not, so I apologize for not following the instructions. 
As I have alluded to earlier, my only problem is with erratic pixels which exist  at the extremities of a cell. That is also true for some pixels outside the cell, because of the 
limiting nature of my distance formula. The least radius from the center is 30 and my  Pythagorean distance is 35. That means some pixels will be viable, even though they are outside 
of the board. Now, what do I have in mind to improve this? Since a hexagon is two triangles and a rectangle, I would make formulas that would deal with the triangles and the rectangle 
separately. I'm sure the formulas would be hard to make but I would take on the challenge. As for now, this is the best I can come up with, thank you for giving me the opportunity
to make this project!

7.REFERENCES
(1) : https://stackoverflow.com/questions/10716828/joptionpane-showconfirmdialog
(2): Me and Amun Kharel worked together to generate the algorithm for the method pixToHex.
(3):  https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java

