package com.kdnakt;

import javax.inject.Inject;
import javax.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.kdnakt.dynamodb.Fruit;
import com.kdnakt.dynamodb.FruitSyncService;

@Named("dynamodb")
public class DynamodbLambda implements RequestHandler<InputObject, Fruit> {

    @Inject
    FruitSyncService service;

    @Override
    public Fruit handleRequest(InputObject input, Context context) {
        return service.get(input.getName());
    }
}
