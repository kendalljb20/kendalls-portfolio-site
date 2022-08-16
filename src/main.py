import random
WORDLIST_FILENAME = "words.txt"

def loadWords():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r')
    # line: string
    line = inFile.readline()
    # wordlist: list of strings
    wordlist = line.split()
    print("  ", len(wordlist), "words loaded.")
    return wordlist
wordlist = loadWords()
def playGame():
  wordToGuess = random.choice(wordlist)
  secretWord = ""
  for i in wordToGuess:
    secretWord+= "_"

  print("Welcome to the game, Hangman!")
  print("I am thinking of a word that is",len(wordToGuess),"letters long.")
  print("You have 7 tries to guess the word. Good luck :)")

  missesLeft = 7
  lettersGuessed = ""


  #print(" THIS IS SECRET WORD: "+ wordToGuess)
  print("  +---+")
  print("      |")
  print("      |")
  print("      |")
  print("      |")
  print("      |")
  print("=========")


  while missesLeft !=0 and (secretWord != wordToGuess):
    print("Please guess a letter: "+ secretWord )
    guess=str(input().lower())
      
    while len(guess) > 1:
      print("Only guess one letter: "+ secretWord )
      guess=str(input().lower())

    if(len(lettersGuessed) != 0):
      for i in lettersGuessed:
        while guess == i:
          print("You already guessed '" + guess + "'")
          print("Guess again: "+ secretWord )
          guess=str(input().lower())  



    lettersGuessed += guess
    print("\nAvailable letters:",getAvailableLetters(lettersGuessed))
    print("---------------------------------------------")
    if len(guess)>1:
      if(guess==wordToGuess):
        secretWord = wordToGuess
      #else:
        #missesLeft-=1
        
    else: 
      goodGuess = False
    
      for i in range(0, len(wordToGuess)): 
        curLetter = wordToGuess[i: i+1 ]

        if guess == curLetter:
          secretWord = secretWord[0:i] + curLetter + secretWord[i+1: len(secretWord)]

          goodGuess = True
      if goodGuess == False:
        print("Oops! '" + guess + "' is not in my word: " + secretWord)
        missesLeft-=1

    
    print("You have",missesLeft,"guesses left.")

  if wordToGuess == secretWord:
    print("The word was: "+wordToGuess)
    print("Congratulations! You won! :)")

  else:
    print("Sorry, you ran out of guesses. The word was",wordToGuess)

def getAvailableLetters(letterList):
    '''
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters that represents what letters have not
      yet been guessed.
    '''
    import string
    ans=list(string.ascii_lowercase)
    for i in letterList:
        ans.remove(i)
    return ''.join(ans)

playGame()