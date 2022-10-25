#!/bin/zsh

region=sa-east-1
endpoint=http://localhost:4566
queueOneUrl=$endpoint/000000000000/example-two-sqs

echo 'Creating messages'

for i in $(seq 1 15)
do
  aws --region $region --endpoint $endpoint sqs send-message --queue-url $queueOneUrl --message-body "Hello World! - $i"  >> /dev/null
done

echo 'Done!'
