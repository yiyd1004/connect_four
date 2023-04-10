package connectfour

class Player (name: String) {
    private var name: String? = null

    init {
        this.name = name
    }

    fun getName(): String? {
        return this.name
    }
}