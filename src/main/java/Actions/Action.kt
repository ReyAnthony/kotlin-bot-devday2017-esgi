package Actions


import com.ullink.slack.simpleslackapi.SlackSession
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener

abstract class Action : SlackMessagePostedListener {

    val botUsername = "dicobot"

    override fun onEvent(posted: SlackMessagePosted?, session: SlackSession?) {

        if(posted == null || session == null){
            return
        }

        val newMessage = doTheWork(session, posted.messageContent)

        if(posted.sender.userName != botUsername)
            session.sendMessage(posted.channel, newMessage, null)


    }

    abstract fun doTheWork(session: SlackSession, message: String) : String

}