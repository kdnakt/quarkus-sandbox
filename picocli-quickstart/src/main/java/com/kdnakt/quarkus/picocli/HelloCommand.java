package com.kdnakt.quarkus.picocli;

import picocli.CommandLine;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@CommandLine.Command
public class HelloCommand implements Runnable {

    @CommandLine.Option(names = {"-n", "--name"}, description = "Who will we greet?", defaultValue = "World")
    String name;

    private final GreetingService greetingService;

    public HelloCommand(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void run() {
        greetingService.sayHello(name);
    }
}

@Dependent
class GreetingService {
    void sayHello(String name) {
        System.out.println("Hello " + name + "!");
    }
}
