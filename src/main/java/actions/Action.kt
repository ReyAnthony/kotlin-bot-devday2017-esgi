package actions

import com.ullink.slack.simpleslackapi.SlackSession
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener

abstract class Action : SlackMessagePostedListener {

    override fun onEvent(posted: SlackMessagePosted?, session: SlackSession?) {

        if(posted == null || session == null)
            return

        val newMessage = eventImpl(posted)

        if(posted.sender.userName != Data.botUsername && newMessage.isNotEmpty())
            session.sendMessage(posted.channel, newMessage, null)

    }

    protected abstract fun eventImpl(posted: SlackMessagePosted) : String
}