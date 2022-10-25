## Javalin SQS Concurrency Consumer

To init project, run:

```shell
$ docker-compose -f docker/docker-compose.yml up -d --build
$ chmod 777 ./docker/localstack/init-queues.sh
$ ./docker/localstack/init-queues.sh
$ ./gradlew run
```


To send message to queue ONE, run:
```shell
$ chmod 777 ./docker/localstack/send-message-queue-one.sh
$ ./docker/localstack/send-message-queue-one.sh
```


To send message to queue TWO, run:
```shell
$ chmod 777 ./docker/localstack/send-message-queue-two.sh 
$ ./docker/localstack/send-message-queue-two.sh 
```

To stop and remove docker container, run:
```shell
$ docker rm -f $(docker stop $(docker ps -a -q --filter "label=service_docker=javalin-service"))
```
