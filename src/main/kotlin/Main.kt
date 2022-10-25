import io.javalin.Javalin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import resources.aws.SqsClientConfig
import resources.messaging.SqsListenerConnectionFactory
import resources.messaging.listeners.ExampleOneListener
import resources.messaging.listeners.ExampleTwoListener

fun main() {
    JavalinApplication().setupJavalin()
}

internal class JavalinApplication: KoinComponent {
    private val listenerManager: SqsListenerConnectionFactory by inject()

    fun setupJavalin(): Javalin {
        //Setup koin
        startKoin {
            modules(AppModules.build())
        }

        // Setup AWS SQS
        listenerManager.build()

        // Setup Javalin
        val SERVER_PORT = 7900
        val app = Javalin
            .create()
            .get("/") { ctx -> ctx.result("Hello World!")}
            .start(SERVER_PORT)

        return app
    }
}

internal object AppModules {
    fun build(): List<Module> = listOf(
        listenerModules(),
    )

    private fun listenerModules() = module {
        single { ExampleOneListener() }
        single { ExampleTwoListener() }
        single { SqsClientConfig.build() } // AmazonSQS
        single { SqsListenerConnectionFactory(get(), get(), get()) }
    }
}
