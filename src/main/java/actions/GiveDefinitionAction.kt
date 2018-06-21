package actions

import com.ullink.slack.simpleslackapi.events.SlackMessagePosted

class GiveDefinitionAction : Action() {

    override fun eventImpl(posted: SlackMessagePosted) : String {

        return getMessageFromInput(posted)
    }

    private fun getMessageFromInput(posted: SlackMessagePosted) : String{

        var newMessage = ""

        if(botIsMentioned(posted)) {

            val words =
                    posted.messageContent
                        .replace(Data.atBot(), "")
                        .trim()
                        .split(" ")
                        .filter { w -> w != "" }

            if(words.isNotEmpty()){

                for (word in words){
                    Data.dictionary.getIgnoreCase(word.trim())?.let {
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

    private fun botIsMentioned(posted: SlackMessagePosted) : Boolean =
            posted.messageContent.contains(Data.atBot(), ignoreCase = true)

    //extension method
    private fun Map<String, String>.getIgnoreCase(searchedKey: String) : String? {

        val aKey = this.keys.find { it -> searchedKey.equals(it, ignoreCase = true) }
        return this[aKey]
    }

}
