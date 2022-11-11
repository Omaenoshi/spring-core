package com.yet.spring.logger;

import com.yet.spring.bean.Event;

public class ConsoleEventLogger implements EventLogger {

    public ConsoleEventLogger() {
    }

    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
