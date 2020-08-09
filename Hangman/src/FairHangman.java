/**
 * This program allows a user to
 * play a fair game of Hangman. 
 * This class implements HangmanInterface and 
 * gets all the methods from that interface.
 * The class Hangman asks for letters
 * and judging whether the letter is 
 * present/absent in the word predetermined 
 * at the start of the game, empty places
 * (represented by '-') are updated accordingly.
 * Date: Nov 5, 2018
 * Name: Shreeman Gautam
 */

import java.util.*;
import java.io.*;

public class FairHangman implements HangmanInterface{
    
    /**
     * Global Variables used in the program
     */
    List<String> fileTBU = new ArrayList<>();
    String toBeSent;
    Collection<Character> guessedLetters;
    int guesses1;
    char word;
    char returningword[];
    int counter = 0;
    int variable;
    String uniqueword;
    String fileTBR;

    /**
     * Bookkeeping to ensure that every new game 
     * starts properly (like 6 guesses, no guessed letters,
     * new secret word etc.)
     */
    public void initGame(int guesses) {
        word = ' ';
        guessedLetters = new ArrayList<Character>();
        this.guesses1 = guesses;
        this.variable = 0;
        this.uniqueword = getSecretWord();
        this.counter = 0;
        returningword = null;
    }

    /**
     * get the number of guesses remaining, with the guesses
     * updated at the 'updateWithGuesses' method
     */
    public int getGuessesRemaining() {
        return guesses1;
    }

    /**
     * get the alphabets guessed, with the collection updated
     * at the 'updateWithGuesses' method
     */
    public Collection<Character> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Initially, any secret word is represented by
     * blanks('-'). Then, depending on the accuracy 
     * of the guess, array 'returningWord'(later string)
     * is updated accordingly. As a result, the method
     * returns a string representation.
     */
    public String getPuzzle() {
        String text;
        if (counter <= 0) {
            returningword = new char[getSecretWord().length()];
            for (int i = 0; i < returningword.length; i++) {
                returningword[i] = BLANK;
            }
        }
        if (counter >= 1) {
            for (int i = 0; i < getSecretWord().length(); i++) {
                if (getSecretWord().charAt(i) == word) {
                    returningword[i] = word;
                }
                else if (returningword[i] != BLANK) {
                    returningword[i] = returningword[i];
                }
                else {
                    returningword[i] = BLANK;
                }
            }
        }
        counter++;
        text = String.valueOf(returningword);
        return text;
    }

    /**
     * returns the word that is to be guessed
     */
    public String getSecretWord() {
        if (variable <= 0) {
            int length = fileTBU.size();
            Random randWord = new Random();
            int num = randWord.nextInt(length);
            toBeSent = fileTBU.get(num);
            variable++;
        }
        return toBeSent;
    }

    /**
     * if the getPuzzle method returns a string 
     * that matches the secret word, method returns 
     * true, else returns false.
     */
    public boolean isComplete() {
        String word = getPuzzle();
        if(uniqueword.equals(word)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * If guesses drop to zero or the isComplete()
     * method returns true, this method returns true
     * and the game is over. Otherwise, keep
     * playing.
     */
    public boolean isGameOver() {
        if (this.getGuessesRemaining() <= 0 || isComplete()){
            return true;
        }
        return false;
    }

    /**
     * Number of guesses and list of characters are updated 
     * according to the letter guessed. If a letter is guessed
     * twice, that is not taken into account. 
     */
    public boolean updateWithGuess(char letter) {
        int value;
        if (!guessedLetters.contains(letter)) {
            guessedLetters.add(letter);
        }
        word = letter;
        value = getSecretWord().indexOf(letter);
        if (value != -1) {
            return true;
        }
        guesses1--;
        return false;
    }

    /**
     * This constructor reads the name of the external
     * file to be used and sends it to the 'readFileTBR()'
     * method. If file is not found, error message is
     * printed out.
     */
    public FairHangman(String dictionaryFile) {
        this.fileTBR = dictionaryFile;
        try {
            readFileTBR();
        }
        catch(IOException e) {
            System.out.println("This file is not present");
        }
    }

    /**
     * The external file is read and added to a list which is used
     * in the getSecretWord() method to retrieve a random word.
     * After nothing is left to be read, stream is closed.
     */
    public void readFileTBR() throws IOException{
        BufferedReader fr = new BufferedReader(new FileReader(fileTBR));
        try {
            String line;
            while ((line = fr.readLine()) != null) {
                fileTBU.add(line);
            }
        }
        finally {
            if (fr != null) {
                fr.close();
            }
        }
    }
}
