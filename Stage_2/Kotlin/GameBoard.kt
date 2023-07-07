package connectfour

class GameBoard (row: Int, col: Int) {
    var row: Int = -1
    var col: Int = -1
    var board: Array<IntArray>? = null

    init {
        this.row = row
        this.col = col
        board = Array(row) { IntArray(col) }
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
                if (board!![row][col] == 0) boardData.append('\u2551' + " ")
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