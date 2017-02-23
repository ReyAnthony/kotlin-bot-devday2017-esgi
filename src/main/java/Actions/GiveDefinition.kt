package Actions

import com.ullink.slack.simpleslackapi.SlackSession
import javax.activation.DataSource

fun Map<String, String>.getIgnoreCase(key: String) : String? {

    var key = this.keys.find{ k -> k.equals(key, ignoreCase = true) }
    return this[key]
}


class GiveDefinition : Action(){

    override fun doTheWork(session: SlackSession, message: String): String {

        val botId = session.users.find { b -> b.userName.equals(botUsername) }?.id ?: "YENAPA"
        val botMention = "<@$botId>"
        var givenMessage = message

        var messageToReturn = ""

        if(message.contains(botMention)){

            givenMessage.replace(botMention, "")
            var keywords: List<String> = givenMessage.split(" ").filter { m -> m.isNotEmpty() }

            for(keyword in keywords) {

                var definition = Datasource.dictionnary.getIgnoreCase(keyword) ?: continue
                messageToReturn += "$keyword -> $definition \n"
            }
        }

        return messageToReturn
    }

}