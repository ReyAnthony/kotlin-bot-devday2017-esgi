import Actions.GiveDefinition
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory


val botToken = System.getenv("token")
val botUsername = "dicobot"

fun main(args: Array<String>) {

    val session = SlackSessionFactory.createWebSocketSlackSession(botToken)
    session.connect()
    session.addMessagePostedListener(GiveDefinition())
}