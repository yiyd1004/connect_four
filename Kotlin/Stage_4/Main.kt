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
        println("${firstPlayer.getName()} VS ${secondPlayer.getName()}\n6 X 7 board")
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
                    println(
                        if (matchList.isNotEmpty()) {
                            if (isBoardValid == 0) {
                                "${firstPlayer.getName()} VS ${secondPlayer.getName()}\n$row X $col board"
                            } else {
                                "Board ${if (isBoardValid == 1) "rows" else "columns"} should be from 5 to 9"
                            }
                        } else {
                            "Invalid input"
                        }
                    )
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
        val board = GameBoard(row, col)
        board.printBoard()
        startGame(firstPlayer, secondPlayer, board);
    }
}

fun startGame(firstPlayer: Player, secondPlayer: Player, board: GameBoard) {
    var turn = 0;

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
                    break;
                }

                if (board.isBoardFull()) {
                    println("It is a draw")
                    break;
                }
                turn ++;
            } else {
                println("Column $col is full")
            }
        }
    }

    println("Game over!")
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