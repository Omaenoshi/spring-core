package com.yet.spring;

public class ConsoleEventLogger implements EventLogger{

    public ConsoleEventLogger() {
    }

    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
