import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory

val botUsername = System.getenv("botname")
val botToken = System.getenv("token")

fun main(args: Array<String>) {

    val session = SlackSessionFactory.createWebSocketSlackSession(botToken)
    session.connect()

    session.addMessagePostedListener { posted, slackSession ->

        var newMessage = ""

        if(posted.sender.userName != botUsername)
            slackSession.sendMessage(posted.channel, newMessage, null)
    }

}