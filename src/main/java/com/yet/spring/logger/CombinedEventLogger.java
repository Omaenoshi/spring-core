package com.yet.spring.logger;

import com.yet.spring.bean.Event;

import java.io.IOException;
import java.util.List;

public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> loggers;

    public CombinedEventLogger() {
    }

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger eventLogger:
             loggers) {
            eventLogger.logEvent(event);
        }
    }
}
