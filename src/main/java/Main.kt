import actions.GiveDefinitionAction
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory
import java.io.File


fun main(args: Array<String>) {

    val session = SlackSessionFactory.createWebSocketSlackSession(Data.botToken)
    val dico = File(Thread.currentThread().contextClassLoader.getResource("dico.csv").toURI())

    session.connect()
    Data.addToMap(dico)
    Data.botId = session.users.find { u -> u.userName.equals( Data.botUsername , ignoreCase = true) }?.id ?: ""

    session.addMessagePostedListener( GiveDefinitionAction() )
}