import actions.GiveDefinitionAction
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory
import java.io.File


fun main(args: Array<String>) {

    val session = SlackSessionFactory.createWebSocketSlackSession(Data.botToken)
    val dict = File(Thread.currentThread().contextClassLoader.getResource("dico.csv").toURI())

    session.connect()
    Data.addToMap(dict)

    Data.botId =
            session.users.find{ it.userName.equals( Data.botUsername , ignoreCase = true) }?.id
            ?: throw IllegalStateException("bot id could not be retrieved, please check that you spelled the botname right")

    session.addMessagePostedListener( GiveDefinitionAction() )
}