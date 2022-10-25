package resources.aws

import Consts
import com.amazonaws.ClientConfiguration
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder

internal object SqsClientConfig {
    fun build(): AmazonSQS {
        return AmazonSQSClientBuilder
            .standard()
            .withClientConfiguration(
                ClientConfiguration().withMaxConnections(Consts.AWS_SQS.CONFIGS.MAX_CONNECTION)
            )
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    Consts.AWS_SQS.CONFIGS.ENDPOINT,
                    Consts.AWS_SQS.CONFIGS.REGION
                )
            )
            .build()
    }
}