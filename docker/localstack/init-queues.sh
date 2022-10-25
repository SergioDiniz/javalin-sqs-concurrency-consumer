#!/bin/zsh

echo 'Creating queues...'

region=sa-east-1
endpoint=http://localhost:4566
queueOneName=example-one-sqs
queueTwoName=example-two-sqs

#Delete if exist and create
aws --region $region --endpoint $endpoint sqs delete-queue --queue-url $endpoint/queue/$queueOneName >> /dev/null || true
aws --region $region --endpoint $endpoint sqs create-queue --queue-name $queueOneName >> /dev/null

aws --region $region --endpoint $endpoint sqs delete-queue --queue-url $endpoint/queue/$queueTwoName >> /dev/null || true
aws --region $region --endpoint $endpoint sqs create-queue --queue-name $queueTwoName >> /dev/null

echo 'Done!'