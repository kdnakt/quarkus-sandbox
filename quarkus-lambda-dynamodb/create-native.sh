aws lambda create-function --function-name my-native-function --zip-file fileb://target/function.zip --handler any.name.not.used --runtime provided --role arn:aws:iam::195905244530:role/aws-lambda-basic-role --environment Variables="{DISABLE_SIGNAL_HANDLERS=true}"
