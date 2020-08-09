EVIL HANGMAN																				by: Shreeman Gautam

ALGORITHM

For this class assignment, I had to make a fair and evil hangman game. So, if you see the video now, you can see commands like 'java Hangman dictionary.txt evil'/
'java Hangman smallDictionary.txt evil'/'java Hangman animals.txt evil' start to make sense because there is a class called 'Hangman' which determines the class to use, in my case, the
classes are called 'EvilHangman' and 'FairHangman' respectively. 'EvilHangman' cheats the user while 'FairHangman' plays the traditional hangman game. So, when I specify
'java Hangman dictionary.txt evil', I am saying that I want to play the evil version using the 'dictionary.txt' file(which contains words). For the sake of a short video, I have only
shown the evil game because I am assuming most people are familiar with traditional hangman. So, what is evil hangman?

Unlike the traditional Hangman game, Evil Hangman is not played according to the rules, because a secret word is not chosen at the start. Rather, words that fit entered pattern of letters 
are used until the end of the game. Due to this, if a user cannot find the word, the game 'cheats' and prints out a random word(which matches the entered pattern), saying that the random 
word was the secret word all along. The only certain thing at the start of the game is the length of a word. So, words of that length are collected. Now, the game asks the player for a 
letter. Please refer to the first played game of the video to make sense of the next part.

As you can see, when I typed 'a', family patterns are made. The 'a---' family represents 'ally', the '---a' family represents 'beta', the '----' family represents 'cool', 'else', 'flew'
etc. and the '--a-' represents 'deal'. The family with the most members determines the pattern for the next turn. In this case, '----' had the largest family and was subsequently chosen
in the next turn, with the number of guesses being reduced by 1 since no correct word was guessed. This goes on until the user runs out of guesses or beats the cheating game.

I have used the properties of sets, maps and lists to write the code.

