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
    val firstPlayer = readln()

    println("Second player's name:")
    val secondPlayer = readln()

    printBoardMsg()
    val regex = Regex("(.*)\\s*(\\d)+\\s*[xX]\\s*(\\d)+\\s*(.*)", RegexOption.IGNORE_CASE)
    var boardSize = readln()
    var row: Int? = -1
    var col: Int? = -1

    if (boardSize.isEmpty()) {
        println("$firstPlayer VS $secondPlayer\n6 X 7 board")
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
                                "$firstPlayer VS $secondPlayer\n$row X $col board"
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
    }
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