package jetbrains.kotlin.course.hangman

import kotlin.text.withIndex

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int) = "Welcome to the game!$newLineSymbol$newLineSymbol" +
        "In this game, you need to guess the word made by the computer.$newLineSymbol" +
        "The hidden word will appear as a sequence of underscores, one underscore means one letter.$newLineSymbol" +
        "You have $maxAttemptsCount attempts to guess the word.$newLineSymbol" +
        "All words are English words, consisting of $wordLength letters.$newLineSymbol" +
        "Each attempt you should enter any one letter,$newLineSymbol" +
        "if it is in the hidden word, all matches will be guessed.$newLineSymbol$newLineSymbol" +
        "" +
        "For example, if the word \"CAT\" was guessed, \"_ _ _\" will be displayed first, " +
        "since the word has 3 letters.$newLineSymbol" +
        "If you enter the letter A, you will see \"_ A _\" and so on.$newLineSymbol$newLineSymbol" +
        "" +
        "Good luck in the game!"

// You will use this function later
fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

// You will use this function later
fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun isComplete(secret: String, currentGuess: String): Boolean = secret == currentGuess.replace(separator, "")

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    var newWord = StringBuilder(currentUserWord)
    for ((index, symbol) in secret.withIndex()) {
        if (symbol == guess) {
            newWord[2 * index] = symbol
        }
    }
    return newWord.toString()
}

fun generateSecret(): String = words.random()

fun getHiddenSecret(wordLength: Int): String = List(wordLength) { underscore }.joinToString(separator)

fun isCorrectInput(userInput: String): Boolean {
    if (userInput.length != 1) {
        println("The length of your guess should be 1! Try again!")
        return false
    }
    for (letter in userInput) {
        if (!letter.isLetter()) {
            println("You should input only English letters! Try again!")
            return false
        }
    }
    return true
}

fun safeUserInput(): Char {
    do {
        println("Please input your guess.")
        val guess = safeReadLine()
        if (isCorrectInput(guess)) {
            return guess.uppercase()[0]
        }
    } while (true)
}
fun getRoundResults(secret: String, guess: Char, currentUserWord: String): String {
    if (!secret.contains(guess)) {
        println("Sorry, the secret does not contain the symbol: $guess. The current word is $currentUserWord")
        return currentUserWord
    } else {
        val newUserWord = generateNewUserWord(secret, guess, currentUserWord)
        println("Great! This letter is in the word! The current word is $newUserWord")
        return newUserWord
    }
}

fun playGame(secret: String, maxAttemptsCount: Int): Unit {
    var complete: Boolean
    var attempts = 0
    var word = getHiddenSecret(secret.length)
    do {
        val guess = safeUserInput()
        word = getRoundResults(secret, guess, word)
        complete = isComplete(secret, word)
        attempts++
        if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! My word is $secret")
            break
        } else if (isWon(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed it!")
        }
    } while (!complete)
}

fun main() {
    // Uncomment this code on the last step of the game

     println(getGameRules(wordLength, maxAttemptsCount))
     playGame(generateSecret(), maxAttemptsCount)
}
