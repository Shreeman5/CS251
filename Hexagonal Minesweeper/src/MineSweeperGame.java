/**
 * This class runs every instance of minesweeper.
 * Except for the hgihscore file, which is saved
 * across sessions, there is a new game and a reset
 * to go along with it.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MineSweeperGame {
    
    /**
     * global variables used in the game
     */
    static int boardSize;
    int mines;
    int seed = 100;
    int hexSize = 60;
    int separation = 15;  
    static int board[][];
    int labelX = 1101;
    int labelY = 20;
    static int flagged = 0;
    static ArrayList<Integer> highScoresEASY = new ArrayList<>();
    static ArrayList<Integer> highScoresHARD = new ArrayList<>();
    long seconds;
    static String difficulty;
    static BufferedReader fr = null;
    static PrintWriter fw = null;
    static String textFile;
    public JLabel score1;
    public JLabel score2;
    public JLabel score3;
    public JLabel scoreB;
    public JLabel scoreC;
    public JLabel scoreD;
    
    /**
     * the game starts here, with difficulty and
     * highscores file being specified at cmd
     * @param args
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                difficulty = args[0];
                textFile = args[1];
                new MineSweeperGame();       
            }
        });
    }
    
    /**
     * initializes the game
     */
    public MineSweeperGame() {
        startGame();
        makeGUI();
        getFile();
    }
    
    /**
     * get the highscores file
     * at the start of the game
     * only if file is present
     */
    public void getFile(){
        File f = new File(textFile);
        if (f.isFile()) {
            try {
                fr = new BufferedReader(new FileReader(textFile));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line;
            try {
                while ((line = fr.readLine()) != null) {
                    String numLine = line;
                    groupWithDifficulty(numLine);
                }
                initListOfTimes();
            } 
            catch (NumberFormatException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
           
    }


    /**
     * group scores in the highscores '
     * file according to difficulty EASY/HARD
     * @param numLine
     */
    public void groupWithDifficulty(String numLine) {
        String here = "";
        for (int i = 1; i < numLine.length(); i++) {
            here = here + numLine.charAt(i);
        }
        int number = Integer.parseInt(here);
        
        if (numLine.startsWith("E")) {
            highScoresEASY.add(number);
        }
        else if (numLine.startsWith("H")) {
            highScoresHARD.add(number);
        }
        
    }

    /**
     * call hexMineManager here according to difficulty. 
     * specify the height of the hexagon.
     */
    public void startGame(){
        HexGUI.HexSpecification(hexSize);
        Random rand = new Random();
        seed = seed + rand.nextInt(10);
        if (difficulty.equals("EASY")) {
            boardSize = 9;
            mines = 13;
            board = new int[boardSize][boardSize];
            HexMineManager newClassObj = new HexMineManager(boardSize, mines, seed);
        }
        else if (difficulty.equals("HARD")) {
            boardSize = 15;
            mines = 55;
            board = new int[boardSize][boardSize];
            HexMineManager newClassObj = new HexMineManager(boardSize, mines, seed);
        }
        else {
            System.out.println("Please enter EASY/HARD");
        }
        makeBoard();   
  /*      BOARDSIZE = Integer.parseInt(size);
        MINES = Integer.parseInt(mines);
        SEED = Integer.parseInt(seed); */
    }
    
    /**
     * start the game with the cells as 0
     */
    public void makeBoard() {
        flagged = 0;
        for (int i=0;i<boardSize;i++) {
            for (int j=0;j<boardSize;j++) {
                board[i][j]=0;
            }
        }
    }
    
    /**
     * add the panel to the frame
     */
    public void makeGUI(){
        DrawingPanel panel = new DrawingPanel();
        panel.setLayout(null);
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container content = frame.getContentPane();
        content.add(panel);
        frame.setSize(1400, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * the GUI is made here
     */
    class DrawingPanel extends JPanel{ 
        /**
         * variables used in this class
         */
        public LocalDateTime startTime;
        public JLabel label;
        public JLabel score;
        public JLabel scoreA;
        public JLabel miner;
        public JLabel flag;
        public Timer timer;
        private static final long serialVersionUID = 1L;
        /**
         * give background, add mouse, start timer, setup labels
         */
        public DrawingPanel(){   
            setBackground(Color.CYAN);
            MyMouseListener ml = new MyMouseListener();            
            addMouseListener(ml);
            startingLabels();
            startTimer(); 
        }

        /**
         * called after every click and subsequent repaint.
         * ensures the existence of the board
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            for (int i=0;i<boardSize;i++) {
                for (int j=0;j<boardSize;j++) {
                    HexGUI.drawAllHex(i,j,g);
                }
            }
            for (int i=0;i<boardSize;i++) {
                for (int j=0;j<boardSize;j++) {                 
                    HexGUI.finalHexBoard(i,j,board[i][j],g);
                }
            }
            HexGUI.additionalGUI(labelX, labelY, g);
        }

        /**
         * get the coordinates from pixToHex, send 
         * to HexMineManager according to the nature of the click.
         * check for end of game
         */
        class MyMouseListener extends MouseAdapter  {
            public void mouseClicked(MouseEvent e) { 
                if (!timer.isRunning()) {
                    startTime = LocalDateTime.now();
                    timer.start(); 
                }
                int X = e.getX();
                int Y = e.getY();
                Point p = new Point(HexGUI.pixToHex(X, Y));
                if (p.x != -1 && p.y != -1) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (board[p.x][p.y] != (int) 'F') {
                            HexMineManager.solveGame(p.x, p.y);
                            boolean win = checkGameForWin();
                            if (win == true) {
                                endOfGameStuff(win); 
                            }
                            else if (board[p.x][p.y] == (int) 'M') { 
                                endOfGameStuff(win);                     
                            }
                        }
                    }
                    else if (e.getButton() == MouseEvent.BUTTON3) {
                        HexMineManager.toggleFlag(p.x, p.y);
                        flag.setText("Flag(s) = " + flagged);
                        boolean win = checkGameForWin();
                        if (win == true) {
                            endOfGameStuff(win);
                        }
                    }
                }
                repaint();
            }
        }
        
        /**
         * labels specified at the start of the game
         */
        public void startingLabels() {
            score = new JLabel("EASY High Scores");
            score.setForeground(Color.BLUE); 
            score.setBounds(labelX + 103, labelY, 130, 30);
            add(score);
            
            score1 = new JLabel("Score1 = ");
            score1.setForeground(Color.BLUE); 
            score1.setBounds(labelX + 103, labelY + 30, 130, 30);
            add(score1);
            
            score2 = new JLabel("Score2 = ");
            score2.setForeground(Color.BLUE); 
            score2.setBounds(labelX + 103, labelY + 60, 130, 30);
            add(score2);
            
            score3 = new JLabel("Score3 = ");
            score3.setForeground(Color.BLUE); 
            score3.setBounds(labelX + 103, labelY + 90, 130, 30);
            add(score3);
            
            scoreA = new JLabel("HARD High Scores");
            scoreA.setForeground(Color.BLUE); 
            scoreA.setBounds(labelX + 2, labelY + 150, 130, 30);
            add(scoreA);
            
            scoreB = new JLabel("ScoreB = ");
            scoreB.setForeground(Color.BLUE); 
            scoreB.setBounds(labelX + 2, labelY + 180, 130, 30);
            add(scoreB);
            
            scoreC = new JLabel("ScoreC = ");
            scoreC.setForeground(Color.BLUE); 
            scoreC.setBounds(labelX + 2, labelY + 210, 130, 30);
            add(scoreC);
            
            scoreD = new JLabel("ScoreD = ");
            scoreD.setForeground(Color.BLUE); 
            scoreD.setBounds(labelX + 2, labelY + 240, 130, 30);
            add(scoreD); 
            
            label = new JLabel("00s");
            label.setForeground(Color.RED);
            label.setBounds(labelX + 25, labelY, 150, 30);
            add(label);
            
            miner = new JLabel("Mines = " + mines);
            miner.setForeground(Color.DARK_GRAY);
            miner.setBounds(labelX + 5, labelY + 50, 150, 30);
            add(miner);     
            
            flag = new JLabel("Flag(s) = " + 0);
            flag.setForeground(Color.MAGENTA); 
            flag.setBounds(labelX + 5, labelY + 100, 150, 30);
            add(flag);
        }
        
        /**
         * starts the timer when the mouse is clicked
         */
        public void startTimer() {            
            timer = new Timer(0, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LocalDateTime now = LocalDateTime.now();
                    Duration duration = Duration.between(startTime, now);
                    label.setText(format(duration));
                }
            });
        }
        
        /**
         * depending on the value of win, labels are displayed.
         * If user doesn't want to play, exit out of the game.
         * @param win
         */
        public void endOfGameStuff(boolean win) {
            int option;
            timer.stop();  
            
            repaint();
            if (win == true) {
                listOfTimes();
                option = JOptionPane.showConfirmDialog(null, "You have won the game!"
                        + "Want to play again?", "CONGRATULATIONS!", JOptionPane.YES_NO_OPTION);
            }
            else {
                option = JOptionPane.showConfirmDialog(null, "You have clicked on a mine."
                        + "Want to play again?", "GAME OVER", JOptionPane.YES_NO_OPTION);
            }
            
            if (option == JOptionPane.YES_OPTION) {
                reset();
                flag.setText("Flag(s) = " + 0);
                label.setText("00s");
            }
            else {
                System.exit(0);
            }
        }
    }
    
    /**
     * helps display the high scores after every game
     */
    public void initListOfTimes() {
        Collections.sort(highScoresEASY);
        Collections.sort(highScoresHARD);
        
        if (highScoresEASY.size() == 1) {
            score1.setText("Score1 = " + highScoresEASY.get(0));
        }
        
        if (highScoresHARD.size() == 1) {
            scoreB.setText("ScoreB = " + highScoresHARD.get(0));
        }
        
        if (highScoresEASY.size() == 2) {
            score1.setText("Score1 = " + highScoresEASY.get(0));
            score2.setText("Score2 = " + highScoresEASY.get(1));
        }
        
        if (highScoresHARD.size() == 2) {
            scoreB.setText("ScoreB = " + highScoresHARD.get(0));
            scoreC.setText("ScoreC = " + highScoresHARD.get(1));
        }
        
        if (highScoresEASY.size() >= 3) {
            score1.setText("Score1 = " + highScoresEASY.get(0));
            score2.setText("Score2 = " + highScoresEASY.get(1));
            score3.setText("Score3 = " + highScoresEASY.get(2));
        }
        
        if (highScoresHARD.size() >= 3) {
            scoreB.setText("ScoreB = " + highScoresHARD.get(0));
            scoreC.setText("ScoreC = " + highScoresHARD.get(1));
            scoreD.setText("ScoreD = " + highScoresHARD.get(2));
        }
    }
    
    /**
     * change the list of highscores every time a game is won.
     * print it to the external highscores file.
     */
    public void listOfTimes() {
        try {
            fw = new PrintWriter(new FileWriter(textFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
               
        String toBeSent = "";
        if (difficulty.equals("EASY")) {
            highScoresEASY.add((int)seconds);
            toBeSent = "E" + Integer.toString((int)seconds);
        }
        else if (difficulty.equals("HARD")) {
            highScoresHARD.add((int)seconds);
            toBeSent = "H" + Integer.toString((int)seconds);
        }
        
        fw.println(toBeSent);        
        initListOfTimes();
        fw.close();
    }
    
    /**
     * if board is solved , return true
     * otherwise, return false
     * @return
     */
    public boolean checkGameForWin() {
        for (int i=0;i<boardSize;i++) {
            for (int j=0;j<boardSize;j++) {
                if (board[i][j] == 0 && HexMineManager.compHex[i][j] != 'M') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * reset game after win/loss
     */
    public void reset() {
        makeBoard();
        Random rand = new Random();
        seed = seed + rand.nextInt(10);
        HexMineManager.reset(boardSize, mines, seed);
    }  
    
    /**
     * return seconds passed every time.
     * @param duration
     * @return
     */
    public String format(Duration duration) {
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        seconds = (mins * 60) + duration.minusMinutes(mins).toMillis() / 1000;
        return String.format("%02ds", seconds);
    }

}
