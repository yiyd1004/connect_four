package connectfour

class GameBoard (row: Int, col: Int) {
    private var row: Int = -1
    private var col: Int = -1
    private var board: Array<IntArray>? = null

    init {
        this.row = row
        this.col = col
        board = Array(row) { IntArray(col).apply { fill(-1) } }
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