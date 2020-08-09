/**
 * In this program, you play the Othello game between
 * 2 human players. This program is not fully complete.
 * Let's say that we are near the end of the game and a certain
 * color cannot make a move, turn does not change to a color 
 * that can make a move. If the board is populated fully,
 * you have to click on any piece to pop up the end-of-the-game
 * message: Dark Wins, White Wins or Draw. Those are 2 errors 
 * that I have not resolved. Finally, I haven't made a
 * computer player.
 * Date: Sept 14, 2018
 * Author: Shreeman Gautam
 */

import cs251.lab2.*;

/**
 * Through the methods given in this class,
 * we can play Othello.
 */

public class Othello implements OthelloModel {
    
    /** this variable allows us to switch players*/
    public static int globalCounters = 0;
    
    /** this variable allows us to change a certain number of pieces*/
    public static int piecesToBeChanged = 0;
    
    /**  global variable of the board's string*/
    public static String board = "";

    /**char array representation of board */
    public static final char[][] reversi = {
        { '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', 'W', 'B', '.', '.', '.' },
        { '.', '.', '.', 'B', 'W', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.' },
    };
    
    /** 
     * string representation of board that is returned after every click
     * 
     */
    public String getBoardString() {
        board = "";
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                board += reversi[i][j];
                board += "";
            }
            board += '\n';
        }
        System.out.println(board);
        return board;
    }
    /**
     * returns which colour's turn it is to make a move
     */
    public Piece getCurrentTurn() {
        if (globalCounters % 2 == 0) {
            return OthelloModel.Piece.DARK;
        }
        else if (globalCounters % 2 == 1) {
            return OthelloModel.Piece.LIGHT;
        }
        else{
            return OthelloModel.Piece.EMPTY;
        }
    }

    /**
     * return's integer value of row and column
     */
    public int getSize() {
        return 8;
    }

    /**
     * arg0 is clicked row, arg1 is clicked column.
     * From this method, through the ifelse statements,
     * results of the game are published after
     * every click: Light Wins, Dark Wins,
     * Draw or Game not Over.
     */
    public Result handleClickAt(int arg0, int arg1) {
        boolean spacesLeft = findEmptySpaces(); 
        if (spacesLeft == true) {
            int white = countWhiteColor();
            int black = countBlackColor();
            
            if (white > black) {
                return OthelloModel.Result.LIGHT_WINS;
            }
            else if (white < black) {
                return OthelloModel.Result.DARK_WINS;
            }
            else {
                return OthelloModel.Result.DRAW;
            }
        }
        else {
            if (reversi[arg0][arg1] == 'B' || reversi[arg0][arg1] == 'W') {
                System.out.println("Illegal Move");
            }
            else {
                identifyColor(arg0, arg1);
            }
            return OthelloModel.Result.GAME_NOT_OVER;
        }
    }
    
    /** Every time handleClickAt is called,
     * the program tries to find an instance of 
     * an empty slot. If all the slots
     *are filled, true is returned else,
     *false is returned.
     */
    public boolean findEmptySpaces(){
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (reversi[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * if findEmptySpaces() returns true,
     * the number of white pieces is counted
     * and is compared to number of black pieces
     * in handleClickAt()
     */
    public int countWhiteColor() {
        int counter = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (reversi[i][j] == 'W') {
                    counter++;
                }
            }
        }
        return counter;
    }
    
    /**
     * if findEmptySpaces() returns true,
     * the number of black pieces is counted
     * and is compared to number of white pieces
     * in handleClickAt()
     */
    public int countBlackColor() {
        int counter = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (reversi[i][j] == 'B') {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Here, the identity of the player
     * is revealed and sent to
     * the findNeighbors method.
     */
    public void identifyColor(int arg0, int arg1) {
        char currentColor = ' ';
        char oppositeColor = ' ';
        if (getCurrentTurn() == OthelloModel.Piece.DARK) {
            currentColor = 'B';
            oppositeColor = 'W';
        }
        else if (getCurrentTurn() == OthelloModel.Piece.LIGHT) {
            currentColor = 'W';
            oppositeColor = 'B';
        }
        findNeighbors(currentColor, oppositeColor, arg0, arg1);
    }
    
    /**
     * Here, it is determined whether a certain
     * spot is eligible to play or not. If eligible,
     * to play in any of the 8 directions,
     * lookForPiece is called.
     * If it is eligible to play, player's turn
     * is changed else, the terminal prints
     * out an error message (if legality counter
     * is greater than or equals to 8).
     */
    public void findNeighbors(char currentColor, char oppositeColor, int arg0, int arg1) {
        boolean legality = false;
        boolean exception = true;
        int legalityCounter = 0;
        
        exception = ExceptionHandling(arg0 - 1, arg1 - 1);
        if (exception == false) {
            if (reversi[arg0 - 1][arg1 - 1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, -1, -1);
                if (legality == true) {
                    changeColor(arg0, arg1, -1, -1);
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0 - 1, arg1);
        if (exception == false) {
            if (reversi[arg0 - 1][arg1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, -1, 0);
                if (legality == true) {
                    changeColor(arg0, arg1, -1, 0); 
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0 - 1, arg1 + 1);
        if (exception == false) {
            if (reversi[arg0 - 1][arg1 + 1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, -1, 1);
                if (legality == true) {
                    changeColor(arg0, arg1, -1, 1); 
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0, arg1 - 1);
        if (exception == false) {
            if (reversi[arg0][arg1 - 1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, 0, -1);
                if (legality == true) {
                    changeColor(arg0, arg1, 0, -1);
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0, arg1 + 1);
        if (exception == false) {
            if (reversi[arg0][arg1 + 1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, 0, 1);
                if (legality == true) {
                    changeColor(arg0, arg1, 0, 1);
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0 + 1, arg1 - 1);
        if (exception == false) {
            if (reversi[arg0 + 1][arg1 - 1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, 1, -1);
                if (legality == true) {
                    changeColor(arg0, arg1, 1, -1);
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0 + 1, arg1);
        if (exception == false) {
            if (reversi[arg0 + 1][arg1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, 1, 0);
                if (legality == true) {
                    changeColor(arg0, arg1, 1, 0);  
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        exception = ExceptionHandling(arg0 + 1, arg1 + 1);
        if (exception == false) {
            if (reversi[arg0 + 1][arg1 + 1] == oppositeColor) {
                legality = false;
                legality = lookForPiece(arg0, arg1, 1, 1);
                if (legality == true) {
                    changeColor(arg0, arg1, 1, 1);      
                }
                else {
                    legalityCounter++;
                }
            }
            else {
                legalityCounter++;
            }
        }
        else {
            legalityCounter++;
        }
        
        if (legalityCounter >= 8) {
            globalCounters = globalCounters + 0;
            System.out.println("Please make a legal move");
        }
        else {
            globalCounters++;
        }
    }
    
    /**
     * If this method returns false, method lookForPiece
     * is called. If true, legalityCounter variable in findNeighbors
     * method increases by 1.
     */
    public boolean ExceptionHandling(int num, int num1) {
        if (num < 0 || num >= 8 || num1 < 0 || num1 >= 8) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Depending on the player's color, a certain
     * direction is checked for the opposite color.
     * If there is a piece of the same color at the end 
     * of the direction, method returns true. Else,
     * it returns false.
     */
    public boolean lookForPiece(int row, int col, int rowOffset, int colOffset) {
        char currentColor = ' ';
        char oppositeColor = ' ';
        if (getCurrentTurn() == OthelloModel.Piece.DARK) {
            currentColor = 'B';
            oppositeColor = 'W';
        }
        else if (getCurrentTurn() == OthelloModel.Piece.LIGHT) {
            currentColor = 'W';
            oppositeColor = 'B';
        }
        piecesToBeChanged = 0;
        int row1 = 0;
        int col1 = 0;
        int counter = 1;
        do {
               piecesToBeChanged++;
               row1 = row + (rowOffset * counter);
               col1 = col + (colOffset * counter);
            
               if(row1 < 0 || row1 >= 8 || col1 < 0 || col1 >= 8) {
                   return false;
               }
            
               if (reversi[row1][col1] == currentColor) {
                   return true;
               }
               counter++;
        } while ((reversi[row1][col1] != currentColor) || 
                (reversi[row1][col1] != '.')
                ||(row1 <= getSize() - 1 && row1 >= 0) || 
                (col1 <= getSize() - 1 && col1 >= 0));
        return false;
    }
    
    /**
     * If lookForPiece returned true,
     * the same direction is accessed again
     * and the places that need flipping (including the 
     * empty clicked spot) are changed to the desirable
     * color.
     */
    public void changeColor(int row, int col, int rowOffset, int colOffset) {
        char currentColor = ' ';
        char oppositeColor = ' ';
        if (getCurrentTurn() == OthelloModel.Piece.DARK) {
            currentColor = 'B';
            oppositeColor = 'W';
        }
        else if (getCurrentTurn() == OthelloModel.Piece.LIGHT) {
            currentColor = 'W';
            oppositeColor = 'B';
        }
        reversi[row][col] = currentColor;
        int row1 = 0;
        int col1 = 0;
        int counter = 1;
        int groundZero = 1;
        do{
              row1 = row + (rowOffset * counter);
              col1 = col + (colOffset * counter);
              reversi[row1][col1] = currentColor;
              counter++;
              groundZero++;
        } while (groundZero < piecesToBeChanged);
    }
    
    /**  Nothing here*/
    public void setComputerPlayer(String arg0) {
        
    }

    /**
     * This restores the original placement
     * of the pieces.
     * 
     */
    public void startNewGame() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                reversi[i][j] = '.';
            }
        }
        reversi[3][3] = 'W';
        reversi[3][4] = 'B';
        reversi[4][3] = 'B';
        reversi[4][4] = 'W';
    } 

    public static void main(String[] args) {
        Othello game = new Othello();
        if (args.length > 0) {
            game.setComputerPlayer(args[0]);
        }
        OthelloGUI.showGUI(game);
    }

}
