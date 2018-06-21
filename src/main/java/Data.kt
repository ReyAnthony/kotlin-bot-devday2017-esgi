import java.io.File

object Data {

    val dictionary: MutableMap<String, String> = mutableMapOf()

    fun addToMap(file: File){

        file.forEachLine {
            it ->
                val words = it.split(";")
                val key = words[0]
                val def = words[1]

            dictionary[key] = def
        }
    }

    val botUsername: String = System.getenv("botname")
    val botToken: String = System.getenv("token")
    var botId = ""

    fun atBot() = "<@${Data.botId}>"


}