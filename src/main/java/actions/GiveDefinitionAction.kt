package actions

import com.ullink.slack.simpleslackapi.events.SlackMessagePosted

fun Map<String, String>.getIgnoreCase(searchedKey: String) : String? {

    val aKey = this.keys.find { it -> searchedKey.equals(it, ignoreCase = true) }
    return this[aKey]
}

class GiveDefinitionAction : Action() {

    override fun eventImpl(posted: SlackMessagePosted) : String {

        val newMessage = getMessageFromInput(posted)
        return newMessage
    }

    private fun getMessageFromInput(posted: SlackMessagePosted) : String{

        var newMessage = ""

        if(posted.messageContent.contains(Data.atBot(), ignoreCase = true)) {

            val words = posted.messageContent.replace(Data.atBot(), "").trim().split(" ").filter { w -> !w.equals("") }
            if(words.size > 0){

                for (word in words){
                    Data.dictionnary.getIgnoreCase(word.trim())?.let {
                        newMessage += "$word => $it \n"
                    }
                }

                if(newMessage.isEmpty())
                    newMessage += "Pas de définition pour la requête ..."
            }

            if(newMessage.isEmpty())
                newMessage += "Merci d'indiquer au moins un mot pour que je puisse le définir"

        }
        return newMessage
    }
}
