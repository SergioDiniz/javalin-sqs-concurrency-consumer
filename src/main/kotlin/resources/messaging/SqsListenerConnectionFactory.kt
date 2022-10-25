package resources.messaging

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.services.sqs.AmazonSQS
import resources.messaging.listeners.ExampleOneListener
import resources.messaging.listeners.ExampleTwoListener

class SqsListenerConnectionFactory(
    private val sqsClient: AmazonSQS,
    private val exampleOneListener: ExampleOneListener,
    private val exampleTwoListener: ExampleTwoListener
) {

    fun build(){
        val con = SQSConnectionFactory(ProviderConfiguration(), sqsClient)
        val connection = con.createConnection()

        // Init config for queue
        exampleOneListener.setupConsumer(connection)
        exampleTwoListener.setupConsumer(connection)

        connection.start()
    }

}