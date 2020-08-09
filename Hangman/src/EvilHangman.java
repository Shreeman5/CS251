/**
 * This program allows a user to play
 * an evil game of Hangman. Unlike the
 * fair Hangman game, this game is not played
 * according to the rules, such as, a secret word
 * is not chosen. Rather, words that fit entered
 * pattern of letters are used until the end of
 * the game. Due to this, if a user cannot find 
 * the word, the game 'cheats' and prints out a 
 * random word(which matches the entered pattern),
 * saying that the random word was the secret word
 * all along. Since FairHangman and EvilHangman 
 * use same implementations of some methods, the keyword
 * 'super' is used whenever necessary since the
 * evil game extends the fair game.
 * Date: Nov 5, 2018
 * Name: Shreeman Gautam
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EvilHangman extends FairHangman {

    /**
     * Global Variables used in the program
     */
    String fileTBR;
    String uniqueWord;
    int variable;
    String toBeSent;
    int wordLength;
    List<String> familyWords = new ArrayList<>();
    int counter = 0;
    char returningword[];
    Set<String> uniqueStrings = new HashSet<String>();
    Map<String, List<String>> doMapping = new HashMap<String, List<String>>();
    char enteredLetter;
    Collection<Character> guessedLetters;
    int guesses1;
    int secretWordCounter = 0;
    
    /**
     * Using the 'super' keyword, the evil constructor
     * utilizes the fair game's constructor to know the 
     * name of the file.
     */
    public EvilHangman(String dictionaryFile) {
        super(dictionaryFile);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Bookkeeping to ensure that every new game 
     * starts properly (like 6 guesses, no guessed letters,
     * new secret word whose length is used to
     * narrow down a family of words with the same
     * length, new family of Words etc.)
     */
    public void initGame(int guesses) {
        this.secretWordCounter = 0;
        this.uniqueWord = getSecretWord();
        this.variable = 0;
        this.counter = 0;
        returningword = null;
        guessedLetters = new ArrayList<Character>();
        this.guesses1 = guesses;
        enteredLetter = ' ';
        this.toBeSent = " ";
        familyWords = new ArrayList<>();
    }

    /**
     * get the alphabets guessed, with the collection updated
     * at the 'updateWithGuesses' method
     */
    public Collection<Character> getGuessedLetters(){
        return guessedLetters;
    }

    /**
     * get the number of guesses remaining, with the guesses
     * updated at the 'updateWithGuesses' method
     */
    public int getGuessesRemaining() {
        return guesses1;
    }
    
    /**
     * The first for loop of this method, using the
     * super keyword to read the file defined from the 
     * constructor and subsequently add it to a list,
     * determines the word family to be used in this 
     * program, using the wordLength variable which 
     * is updated in the getSecretWord method.
     * Initially, any secret word is represented by
     * blanks('-'). Then, depending on the accuracy 
     * of the guess, array 'returningWord'(later string)
     * is updated accordingly. As a result, the method
     * returns a string representation.
     */
    public String getPuzzle() {
        if (variable <= 0) {
            for (String temp : super.fileTBU) {
                if (temp.length() == wordLength) {
                    familyWords.add(temp);
                }
            }
            variable++;
        }
        String text;
        
        if (counter <= 0) {
            returningword = new char[wordLength];
            for (int i = 0; i < wordLength; i++) {
                returningword[i] = BLANK;
            }
        }
        
        if (counter >= 1) {
            String bar = familyWords.get(0);
            for (int i = 0; i < bar.length(); i++) {
                if (bar.charAt(i) == enteredLetter) {
                    returningword[i] = enteredLetter;
                }
                else if (bar.charAt(i) == wordList(bar.charAt(i))) {
                    returningword[i] = bar.charAt(i);
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
     * This method exists to get the length of
     * a word and using that length, the getPuzzle 
     * method finds a family of words with the same
     * length. However, that length is extracted by 
     * the use of the 'super' keyword to get the length
     * of the file to be read and the word which gives
     * the length of the word family. If the user cannot
     * beat the game, this method reaches into the 
     * family of words(which match the entered patterns)
     * still left and picks up a random word and reveals 
     * it as the secret word all along, effectively 
     * stooping the user from knowing that the game 
     * was rigged from the start.
     */
    public String getSecretWord() {
        if (secretWordCounter <= 0) {
            wordLength = 0;
            int length = super.fileTBU.size();
            Random randWord = new Random();
            int num = randWord.nextInt(length);
            toBeSent = super.fileTBU.get(num);
            wordLength = toBeSent.length();
            variable++;
            secretWordCounter++;
            return toBeSent;
        }
        else {
            int lengthy = familyWords.size();
            Random randyWord = new Random();
            int num1 = randyWord.nextInt(lengthy);
            String matcher = familyWords.get(num1);
            return matcher;
        }
    }
    
    /**
     * Here, it is determined whether the string
     * obtained at the end of every isGameOver loop
     * in the Hangman file has blanks or not.
     * If there are blanks, the method returns false
     * otherwise, it returns true.
     */
    public boolean isComplete() {
        if (variable >= 1) {
            int completeCounter = 0;
            String noBlanks = String.valueOf(returningword);
            for (int i = 0; i < noBlanks.length(); i++) {
                if (noBlanks.charAt(i) == BLANK) {
                    completeCounter++;
                }
            }
            if (completeCounter > 0) {
                return false;
            }
            return true;
        }
        return false;
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
     * The method that diverges the most from the fair game's
     * implementation. Already guessed letters do not change
     * the number of guesses. If the list returned from
     * the getLargestFamily() method contains a word that
     * contains the entered letter, the method returns true,
     * otherwise returns false and reduces number of guesses.
     */
    public boolean updateWithGuess(char letter) {
        int value;
        if (!guessedLetters.contains(letter)) {
            guessedLetters.add(letter);
        }
        else {
            return true;
        }
        enteredLetter = letter;
        makeSetOfUniqueStrings(letter);
        constructHashMap(letter);
        List<String> largestList = getLargestFamily();
        value = largestList.get(0).indexOf(letter);
        if (value != -1) {
            return true;
        }
        else {
            guesses1--;
            return false;
        }
    }
    
    /**
     * Here, the largest family is found. The string,
     * with the most matches, is found and the 
     * matches are put into a list and returned.
     * to the 'updateWithGuess' method. If two families,
     * represented by lists, have the same amount of
     * matches, the least revealing family is put into
     * the list and returned.
     */
    public <T> List<String> getLargestFamily() {
        familyWords.clear();       
        int numberOfThatPattern = 0;
        int numberOfBlanks = 0;
        int thisNumberOfBlanks = 0;
        for (Map.Entry<String, List<String>> entry : doMapping.entrySet()) {
            numberOfBlanks = 0;
            String thisPattern = entry.getKey();
            System.out.println(thisPattern); //Please uncomment this to 
                                                //see the unique string
            List<String> ofThisPattern = entry.getValue();
            System.out.println(ofThisPattern); // Please uncomment this
                                       // to see the strings of that family
            int localFamilyLength = ofThisPattern.size();
            for (int i = 0; i < thisPattern.length(); i++) {
                if (thisPattern.charAt(i) == BLANK) {
                    numberOfBlanks++;
                }
            }
            if (localFamilyLength > numberOfThatPattern) {
                numberOfThatPattern = localFamilyLength;
                familyWords = ofThisPattern;
                thisNumberOfBlanks = numberOfBlanks;
            }
            else if (localFamilyLength == numberOfThatPattern) {
                if (numberOfBlanks > thisNumberOfBlanks) {
                    numberOfThatPattern = localFamilyLength;
                    familyWords = ofThisPattern;
                    thisNumberOfBlanks = numberOfBlanks;
                }
            }
        }
        return familyWords;
    }

    /**
     * As the method name suggests, an uncompleted 
     * string is mapped to various words that contain it.
     */
    public void constructHashMap(char letter) {
        doMapping.clear();
        for (String unikString: uniqueStrings) {
            doMapping.put(unikString, new ArrayList<>());
            for (String mapping: familyWords) {
                for (int i = 0; i < mapping.length(); i++) {
                    if (mapping.charAt(i) == wordList(mapping.charAt(i))) {
                        returningword[i] = mapping.charAt(i);
                    }
                    else {
                        returningword[i] = BLANK;
                    }
                }
                if(String.valueOf(returningword).equals(unikString)) {
                    doMapping.get(unikString).add(mapping);
                }
            }
        }
    }

    /**
     * Utilizing the unique property of sets, unique strings are 
     * added to the uniqueStrings set.
     */
    public void makeSetOfUniqueStrings(char letter) {
        uniqueStrings.clear();
        for (String unikString: familyWords) {
            for (int i = 0; i < unikString.length(); i++) {
                if (unikString.charAt(i) == letter) {
                    returningword[i] = letter;
                }
                else if (unikString.charAt(i) == wordList(unikString.charAt(i))) {
                    returningword[i] = unikString.charAt(i);
                }
                else {
                    returningword[i] = BLANK;
                }
            }
            uniqueStrings.add(String.valueOf(returningword));
            returningword = new char[wordLength];
            for (int i = 0; i < wordLength; i++) {
                returningword[i] = BLANK;
            }
        }
    }

    /**
     * Called from various methods, this method exists such 
     * that strings are accurately represented in every 
     * method.
     */
    public char wordList(char letter) {
        List<Character> list = (List<Character>) getGuessedLetters();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == letter) {
                return list.get(i);
            }
        }
        return '#';
    }
}
