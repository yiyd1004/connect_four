package connectfour

class GameBoard (row: Int, col: Int) {
    private var row: Int = -1
    private var col: Int = -1
    private var filledCount: Int = 0
    private var board: Array<IntArray>? = null
    private var playerInputPos = IntArray(2)

    init {
        this.row = row
        this.col = col
        board = Array(row) { IntArray(col).apply { fill(-1) } }
        filledCount = 0
        playerInputPos.apply { fill(-1) }
    }

    fun getRow(): Int {
        return this.row
    }

    fun getCol(): Int {
        return this.col
    }

    fun addPlayerInput(col: Int, player: Int): Boolean {
        val row = getRowPos(col - 1)

        if (row >= 0) {
            board!![row][col - 1] = player;
//            println("[$row,$col]")
//            println(board.contentDeepToString())
            playerInputPos[0] = row
            playerInputPos[1] = col - 1
            filledCount ++
            return true;
        }

        return false;
    }

    private fun getRowPos(col: Int): Int {
        var row = -1;
        for (i in this.row - 1 downTo  0) {
            if (board!![i][col] == -1) {
                row = i
                break;
            }
        }

        return row
    }

    fun isBoardFull(): Boolean {
        return (row * col) - filledCount == 0
    }

    fun resetBoard() {
        board = Array(row) { IntArray(col).apply { fill(-1) } }
        filledCount = 0
        playerInputPos.apply { fill(-1) }
    }

    fun isPlayerWinner(player: Int): Boolean {
        if (playerInputPos[0] == -1 || playerInputPos[1] == -1) {
            return false
        }

        return checkHorizontal(player) || checkVertical(player) || checkDiagonal(player)
    }

    private fun checkHorizontal(player: Int): Boolean {
        var token = 1
        var count = 1;

        val rowPos = playerInputPos[0]
        val colPos = playerInputPos[1]

        while (count < 4 && token < 4) {
            if (colPos + count < col &&
                board!![rowPos][colPos + count] == player) {
                token ++
            } else {
                break;
            }
            count ++
        }

        count = 1
        while (count < 4 && token < 4) {
            if (colPos - count >= 0 &&
                board!![rowPos][colPos - count] == player) {
                token ++;
            } else {
                break;
            }
            count ++
        }

        return token == 4
    }

    private fun checkVertical(player: Int): Boolean {
        var token = 1
        var count = 1;

        val rowPos = playerInputPos[0]
        val colPos = playerInputPos[1]

        while (count < 4 && token < 4) {
            if (rowPos + count < row &&
                board!![rowPos + count][colPos] == player) {
                token ++
            } else {
                break;
            }
            count ++
        }

        count = 1
        while (count < 4 && token < 4) {
            if (rowPos - count >= 0 &&
                board!![rowPos - count][colPos] == player) {
                token ++;
            } else {
                break;
            }
            count ++
        }

        return token == 4
    }

    private fun checkDiagonal(player: Int): Boolean {
        var leftUpToken = 1
        var rightUpToken = 1
        var count = 1

        val rowPos = playerInputPos[0]
        val colPos = playerInputPos[1]

        while (count < 4 && rightUpToken < 4) {
            if (rowPos - count >= 0 && colPos + count < col &&
                board!![rowPos - count][colPos + count] == player) {
                rightUpToken ++
            } else {
                break;
            }

            count ++
        }

        count = 1
        while (count < 4 && rightUpToken < 4) {
            if (rowPos + count < row && colPos - count >= 0 &&
                board!![rowPos + count][colPos - count] == player) {
                rightUpToken ++
            } else {
                break;
            }

            count ++
        }

        count = 1
        while (count < 4 && leftUpToken < 4) {
            if (rowPos - count >= 0 && colPos - count >= 0 &&
                board!![rowPos - count][colPos - count] == player) {
                leftUpToken ++
            } else {
                break;
            }

            count ++
        }

        count = 1
        while (count < 4 && rightUpToken < 4) {
            if (rowPos + count < row && colPos + count < col &&
                board!![rowPos + count][colPos + count] == player) {
                leftUpToken ++
            } else {
                break;
            }

            count ++
        }

        return rightUpToken == 4 || leftUpToken == 4
    }


    fun printBoard() {
        if (board == null) {
            return
        }

        val boardData = StringBuilder()
        for (idx in 1..col) {
            boardData.append(" ${idx}")
        }
        boardData.append('\n')

        for (row in 0 until row) {
            for (col in 0 until col) {
                boardData.append('\u2551')

                if (board!![row][col] == -1) boardData.append(" ")
                else if (board!![row][col] == 0) boardData.append('o')
                else if (board!![row][col] == 1) boardData.append('*')
            }
            boardData.append('\u2551')
            boardData.append("\n")
        }

        boardData.append('\u255A')
        for (idx in 1 until col) {
            boardData.append("\u2550\u2569")
        }
        boardData.append("\u2550\u255D")

        println(boardData.toString())
    }
}