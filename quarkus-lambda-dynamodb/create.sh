aws lambda create-function --function-name my-function --zip-file fileb://target/quarkus-lambda-dynamodb-1.0-SNAPSHOT-runner.jar --handler io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest --runtime java8 --role arn:aws:iam::1234567:role/lambda-cli-role
