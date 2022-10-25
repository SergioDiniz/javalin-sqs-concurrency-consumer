package resources.messaging.listeners

import com.amazon.sqs.javamessaging.SQSConnection
import com.amazon.sqs.javamessaging.message.SQSTextMessage
import resources.messaging.SqsListener
import java.util.concurrent.TimeUnit
import javax.jms.Message
import javax.jms.MessageConsumer
import javax.jms.Session

class ExampleOneListener: SqsListener {

    override fun onMessage(message: Message?) {
        val text = (message as SQSTextMessage).text
        println("Listening multiple messages from queue ${Consts.AWS_SQS.QUEUES.EXAMPLE_ONE_QUEUE_NAME} per 10 seconds! - Message: $text; ID: ${message.sqsMessageId}}")

        TimeUnit.SECONDS.sleep(10)

        println("Ok!")
    }

    override fun setupConsumer(connection: SQSConnection) {
        val consumers = mutableListOf<MessageConsumer>()
        val queue = connection
            .createQueueSession(false, Session.AUTO_ACKNOWLEDGE)
            .createQueue(Consts.AWS_SQS.QUEUES.EXAMPLE_ONE_QUEUE_NAME)

        repeat(Consts.AWS_SQS.CONFIGS.MAX_CONNECTION) {
            val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
            consumers.add(session.createConsumer(queue))
        }

        consumers.forEach{ it.messageListener = this}
    }

}