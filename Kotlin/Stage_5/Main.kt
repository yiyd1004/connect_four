package connectfour

fun main() {
    /**
     * Connect Four
     * First player's name:
     * > Ava
     * Second player's name:
     * > Oliver
     * Set the board dimensions (Rows x Columns)
     * Press Enter for default (6 x 7)
     * >
     * Ava VS Oliver
     * 6 X 7 board
     */

    println("Connect Four")
    println("First player's name:")
    val firstPlayer = Player(readln())

    println("Second player's name:")
    val secondPlayer = Player(readln())

    printBoardMsg()
    val regex = Regex("(.*)\\s*(\\d)+\\s*[xX]\\s*(\\d)+\\s*(.*)", RegexOption.IGNORE_CASE)
    var boardSize = readln()
    var row: Int? = -1
    var col: Int? = -1

    if (boardSize.isEmpty()) {
        //println("${firstPlayer.getName()} VS ${secondPlayer.getName()}\n6 X 7 board")
        row = 6
        col = 7
    } else {
        while(true) {
            val match = regex.find(boardSize)?.value
            //println(match)
            if (!match.isNullOrEmpty()) {
                val matchList = match.split("x", "X").toMutableList()
                row = matchList[0].trim().toIntOrNull()
                col = matchList[1].trim().toIntOrNull()

                if (row != null && col != null) {
                    val isBoardValid = validateBoardSize(row, col)

                    if (matchList.isNotEmpty()) {
//                            if (isBoardValid == 0) {
//                                "${firstPlayer.getName()} VS ${secondPlayer.getName()}\n$row X $col board"
//                            } else {
//                                "Board ${if (isBoardValid == 1) "rows" else "columns"} should be from 5 to 9"
//                            }
                        if (isBoardValid != 0) {
                            println("Board ${if (isBoardValid == 1) "rows" else "columns"} should be from 5 to 9")
                        }
                    } else {
                        println("Invalid input")
                    }

                    if (matchList.isNotEmpty() && isBoardValid == 0) {
                        break
                    }
                } else {
                    println("Invalid input")
                }
            } else {
                println("Invalid input")
            }


            printBoardMsg()
            boardSize = readln()
        }
    }

    if (row != null && col != null) {
        val numOfGames = getNumOfGames()
        val board = GameBoard(row, col)
        printNumOfGamesMsg(firstPlayer, secondPlayer, numOfGames, board)
        startGame(firstPlayer, secondPlayer, numOfGames, board);
    }
}

fun getNumOfGames(): Int {
    var numOfGames: Int?? = 1

    while(true) {
        println("Do you want to play single or multiple games?")
        println("For a single game, input 1 or press Enter")
        println("Input a number of games:")

        val input = readln()
        if (input.isEmpty()) {
            break;
        }

        numOfGames  = input.toIntOrNull()

        if (numOfGames == null || numOfGames <= 0) {
            println("Invalid input")
        } else {
            break;
        }
    }

    return numOfGames!!
}

fun printNumOfGamesMsg(firstPlayer: Player, secondPlayer: Player, numOfGames: Int, board: GameBoard) {
    println("${firstPlayer.getName()} VS ${secondPlayer.getName()}")
    println("${board.getRow()} X ${board.getCol()} board")
    println(if (numOfGames == 1) "Single game" else "Total $numOfGames games")
}

fun startGame(firstPlayer: Player, secondPlayer: Player, numOfGames: Int, board: GameBoard) {
    var turn = 0;
    var gameNum = 1;
    val score = IntArray(2)

    if (numOfGames > 1) {
        println("Game #$gameNum")
    }

    board.printBoard()

    while(true) {
        val curPlayer = if (turn % 2 == 0) firstPlayer else secondPlayer

        println("${curPlayer.getName()}'s turn:")
        val input = readln()

        if (input.equals("end", true)) {
            break;
        }

        val col = input.toIntOrNull()
        if (col == null) {
            println("Incorrect column number")
        } else if (col !in 1..board.getCol()) {
            println("The column number is out of range (1 - ${board.getCol()})")
        } else {
            if (board.addPlayerInput(col, turn % 2)) {
                board.printBoard()

                if (board.isPlayerWinner(turn % 2)) {
                    println("Player ${curPlayer.getName()} won")

                    if (numOfGames == 1) {
                        break;
                    } else {
                        score[turn % 2] += 2
                        printScore(score, firstPlayer, secondPlayer)

                        if (gameNum == numOfGames) {
                            break;
                        }

                        gameNum ++
                        board.resetBoard()

                        println("Game #$gameNum")
                        board.printBoard()
                    }
                }

                if (board.isBoardFull()) {
                    println("It is a draw")

                    if (numOfGames == 1) {
                        break;
                    } else {
                        score[0] ++
                        score[1] ++
                        printScore(score, firstPlayer, secondPlayer)

                        if (gameNum == numOfGames) {
                            break;
                        }

                        gameNum ++
                        board.resetBoard()

                        println("Game #$gameNum")
                        board.printBoard()
                    }
                }
                turn ++;
            } else {
                println("Column $col is full")
            }
        }
    }

    println("Game over!")
}

fun printScore(score: IntArray, firstPlayer: Player, secondPlayer: Player) {
    println("Score")
    println("${firstPlayer.getName()}: ${score[0]} ${secondPlayer.getName()}: ${score[1]}")
}

fun printBoardMsg() {
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")
}

fun validateBoardSize (row: Int, col: Int) : Int {
    if (row !in 5..9)
        return 1

    if (col !in 5..9)
        return 2

    return 0
}