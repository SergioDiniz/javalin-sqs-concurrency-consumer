package resources.messaging

import com.amazon.sqs.javamessaging.SQSConnection
import javax.jms.MessageListener

interface SqsListener: MessageListener {
    fun setupConsumer(connection: SQSConnection)
}