package resources.messaging.listeners

import com.amazon.sqs.javamessaging.SQSConnection
import com.amazon.sqs.javamessaging.message.SQSTextMessage
import resources.messaging.SqsListener
import java.util.concurrent.TimeUnit
import javax.jms.Message
import javax.jms.Session

class ExampleTwoListener : SqsListener {

    override fun onMessage(message: Message?) {
        val text = (message as SQSTextMessage).text
        println("Listening message one by one from queue ${Consts.AWS_SQS.QUEUES.EXAMPLE_TWO_QUEUE_NAME} per 10 seconds! - Message: $text; ID: ${message.sqsMessageId}}")

        TimeUnit.SECONDS.sleep(10)

        println("Ok!")
    }

    override fun setupConsumer(connection: SQSConnection) {
        val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
        val queue = session.createQueue(Consts.AWS_SQS.QUEUES.EXAMPLE_TWO_QUEUE_NAME)
        val consumer = session.createConsumer(queue)
        consumer.messageListener = this
    }

}