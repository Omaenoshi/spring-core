package com.yet.spring.logger;

import com.yet.spring.bean.Event;

import java.io.IOException;

public interface EventLogger {

    void logEvent(Event event) throws IOException;
}
