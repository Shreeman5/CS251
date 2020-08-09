/**
 * This class forms the internal
 * logic of minesweeper. Non-traditionally,
 * the grids are in hexagonal form. So, 
 * every cell has 6 instead of eight neighbors.
 * Author: Shreeman Gautam
 * Date: Nov 27, 2018
 */
import java.util.ArrayList;
import java.util.Random;

public class HexMineManager{
    
    /**
     * global variables used in the class
     */
    public static char compHex[][];
    static char dispHex[][];
    static int doubleRadius;
    
    /**
     * Constructor that reads the radius given, 
     * number of mines and fixed seed and also
     * calls other methods.
     * @param radius
     * @param mines
     * @param randomSeed
     */
    public HexMineManager(int radius, int mines, long randomSeed) {
        HexMineManager.doubleRadius = radius;
        fillArrays(radius);
        putMines(mines, randomSeed);
        addNumbersToMineArray();   
    }

    /**
     * the two arrays used in the program
     * are filled with 'C' characters
     * @param radius
     */
    public static void fillArrays(int radius) {
        compHex = new char[doubleRadius][doubleRadius];
        dispHex = new char[doubleRadius][doubleRadius];
        for (int i = 0; i < doubleRadius; i++) {
            for (int j = 0; j < doubleRadius; j++) {
                compHex[i][j] = 'C';
                dispHex[i][j] = 'C';
            }
        }
    }
    
    /**
     * This method generates the same random numbers
     * and using the counter variable, places the mines
     * in certain positions of the compHex array.
     * @param mines
     * @param randomSeed
     */
    public static void putMines(int mines, long randomSeed) {
        Random randNum = new Random(randomSeed);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (nums.size() < mines) {
            int num = randNum.nextInt((doubleRadius * doubleRadius));
            if (!nums.contains(num)) {
                nums.add(num);
            }   
        }
        int counter = 0;
        for (int i = 0; i < doubleRadius; i++) {
            for (int j = 0; j < doubleRadius; j++) {
                for (int k = 0; k < nums.size(); k++) {
                    if (counter == nums.get(k)) {
                        compHex[i][j] = 'M';
                    }
                }
                counter++;
            }
        }
    }
    
    /**
     * Any character that is not a 'M'
     * is sent to the numberOfNeighboringMines
     * method to be assigned a numeric character
     */
    public static void addNumbersToMineArray() {
        System.out.println();
        for (int i = 0 ; i < doubleRadius; i++) {
            for (int j = 0; j < doubleRadius; j++) {
                if (compHex[i][j] != 'M') {
                    numberOfNeighboringMines(i, j);
                }
            }
        }
        /*for (int i = 0; i < doubleRadius; i++) {
            for (int j = 0; j < doubleRadius; j++) {
                System.out.print(compHex[i][j]);
            }
            System.out.println();
        }
        System.out.println();*/
    }
    
    /**
     * Depending on the number of neighbors,
     * 'C' is changed to a numeric character,
     * in the compHex array.
     * @param i
     * @param j
     */
    public static void numberOfNeighboringMines(int i, int j) {
        int value = 0;
        
        if (i - 1 >= 0) {
            if (compHex[i - 1][j] == 'M') {
                value++;
            }
        }
        
        if (i - 1 >= 0 && j + 1 < doubleRadius) {
            if (compHex[i - 1][j + 1] == 'M') {
                value++;
            }
        }
        
        if (j - 1 >= 0) {
            if (compHex[i][j - 1] == 'M'){
                value++;
            }
        }
        
        if (j + 1 < doubleRadius) {
            if (compHex[i][j + 1] == 'M') {
                value++;
            }
        }
        
        if (i + 1 < doubleRadius && j - 1 >= 0) {
            if (compHex[i + 1][j - 1] == 'M') {
                value++;
            }
        }
        
        if (i + 1 < doubleRadius && j >= 0) {
            if (compHex[i + 1][j] == 'M') {
                value++;
            }
        }
        
        char c = (char)(value + '0');
        compHex[i][j] = c;    
    }
    
    /**
     * Called from the testing class,
     * if uncovered position is a mine, 
     * the final string state of the board is shown.
     * Otherwise, the board is recursively solved
     * and the string state is shown.
     * @param i
     * @param j
     */
    public static void solveGame(int i , int j) {
        if (compHex[i][j] == 'M') {
         //   System.out.println("Game over, here is the uncovered board:");
            getFinalStringRep(i, j);
        }
        else {
            recursivelySolveBoard(i, j);
          //  getStringRep();
        }
    }
    
    /**
     * shown after mine is uncovered.
     * @param i
     * @param j
     */
    public static void getFinalStringRep(int i, int j) {
        String board = "";
        for(int k = 0; k < doubleRadius; k++) {
            for (int l = 0; l < k; l++) {
                board = board + " ";
            }
            for (int l = 0; l < doubleRadius; l++) {
                if (compHex[k][l] == 'M') {
                    MineSweeperGame.board[k][l] = 'M';
                }
                if (dispHex[k][l] == 'F') {
                    compHex[k][l] = 'F';
                }
                board = board + compHex[k][l] + " ";
            }
            board = board + '\n';
        }
       // System.out.print(board);     
    }
    
    /**
     * Checks first to toggle or untoggle.
     * dispHex takes the value of 'F'.
     * Checks next to see if compHex at this point is a number,
     * dispHex changes at that point to become a number.
     * Otherwise, this is probably an empty spot where 
     * dispHex becomes '.'. Also, check all of the
     * neighbors recursively if that neighbor has 
     * not been checked yet. If a neighbor is a mine, 
     * dipsHex changes it to a 'C' so that it's not seen
     * in the string states.
     * @param i
     * @param j
     */
    public static void recursivelySolveBoard(int i, int j) {
        if (MineSweeperGame.board[i][j] == (int)'F') {
            MineSweeperGame.board[i][j] = (int)'F';
        }
        else if (compHex[i][j] == '1' || compHex[i][j] == '2' || compHex[i][j] == '3'
                || compHex[i][j] == '4' || compHex[i][j] == '5' || compHex[i][j] == '6') {           
            dispHex[i][j] = compHex[i][j];
            MineSweeperGame.board[i][j] = (int)dispHex[i][j];
        }
        else {
            dispHex[i][j] = '.';
            MineSweeperGame.board[i][j] = (int)dispHex[i][j];           
            if (i - 1 >= 0 && dispHex[i - 1][j] != '.') {        
                if (compHex[i - 1][j] == 'M'){
                    dispHex[i - 1][j] = 'C'; 
                    MineSweeperGame.board[i - 1][j] = (int)dispHex[i - 1][j];
                }
                else {
                    recursivelySolveBoard(i - 1 , j);
                }
            }
        
            if (i - 1 >= 0 && j + 1 < doubleRadius && dispHex[i - 1][j + 1] != '.') {
                if (compHex[i - 1][j + 1] == 'M'){
                    dispHex[i - 1][j + 1] = 'C'; 
                    MineSweeperGame.board[i - 1][j + 1] = (int)dispHex[i - 1][j + 1];
                }
                else {
                    recursivelySolveBoard(i - 1 , j + 1);
                }
            }
        
            if (j - 1 >= 0 && dispHex[i][j - 1] != '.') {
                if (compHex[i][j - 1] == 'M'){
                    dispHex[i][j - 1] = 'C'; 
                    MineSweeperGame.board[i][j - 1] = (int)dispHex[i][j - 1];
                }    
                else {
                    recursivelySolveBoard(i , j - 1);
                }
            }
        
        
            if (j + 1 < doubleRadius && dispHex[i][j + 1] != '.') {
                if (compHex[i][j + 1] == 'M'){
                    dispHex[i][j + 1] = 'C'; 
                    MineSweeperGame.board[i][j + 1] = (int)dispHex[i][j + 1];
                }
                else {
                    recursivelySolveBoard(i , j + 1);
                }
            }
        
            if (i + 1 < doubleRadius && j - 1 >= 0 && dispHex[i + 1][j - 1] != '.') {
                if (compHex[i + 1][j - 1] == 'M'){
                    dispHex[i + 1][j - 1] = 'C'; 
                    MineSweeperGame.board[i + 1][j - 1] = (int)dispHex[i + 1][j - 1];
                }
                else {
                    recursivelySolveBoard(i + 1, j - 1);
                }
            }
        
            if (i + 1 < doubleRadius && j >= 0 && dispHex[i + 1][j] != '.') {
                if (compHex[i + 1][j] == 'M'){
                    dispHex[i + 1][j] = 'C'; 
                    MineSweeperGame.board[i + 1][j] = (int)dispHex[i + 1][j];
                }
                else {
                    recursivelySolveBoard(i + 1, j);
                }
            }   
        }     
    }
    
    /**
     * following all of the changes done by recursion, 
     * show the string state of dispHex to the user
     */
  /*  public static void getStringRep() {
        String board = "";
        for(int i = 0; i < doubleRadius; i++) {
            for (int j = 0; j < i; j++) {
                board = board + " ";
            }
            for (int j = 0; j < doubleRadius; j++) {
                board = board + dispHex[i][j] + " ";
            }
            board = board + '\n';
        }
        System.out.print(board);
    } */
    
    /**
     * toggle flag at this point
     * @param i
     * @param j
     */
    public static void toggleFlag(int i, int j) {
        if (dispHex[i][j] == 'C') {
            dispHex[i][j] = 'F';
            MineSweeperGame.board[i][j] = (int)dispHex[i][j];
            MineSweeperGame.flagged++;
        }
        else if (dispHex[i][j] == 'F'){
            dispHex[i][j] = 'C';
            MineSweeperGame.board[i][j] = (int) ',';
            MineSweeperGame.flagged--;
        }
       // getStringRep();
    }

    /**
     * resets the internal logic after every finished game
     * @param boardSize
     * @param mines
     * @param seed
     */
    public static void reset(int boardSize, int mines, int seed) {
        HexMineManager.doubleRadius = boardSize;
        fillArrays(mines);
        putMines(mines, seed);
        addNumbersToMineArray();   
    }
    
}
