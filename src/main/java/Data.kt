import java.io.File

object Data {

    val dictionnary: MutableMap<String, String> = mutableMapOf()

    fun addToMap(file: File){

        file.forEachLine {
            it ->
                val words = it.split(";")
                val key = words[0]
                val def = words[1]

                dictionnary.put(key, def)
        }
    }

    val botUsername = System.getenv("botname")
    val botToken = System.getenv("token")
    var botId = ""

    fun atBot() = "<@${Data.botId}>"


}