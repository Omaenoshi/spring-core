package com.yet.spring;

import com.yet.spring.bean.Client;
import com.yet.spring.bean.Event;
import com.yet.spring.logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean("app", App.class);
        Event event = context.getBean("event", Event.class);
        event.setMsg("Some event for user 1");
        app.logEvent(event);

        Event event2 = context.getBean("event", Event.class);
        event2.setMsg("Some event for user 2");
        app.logEvent(event2);

        Event event3 = context.getBean("event", Event.class);
        event3.setMsg("Some event for user 3");
        app.logEvent(event3);
    }

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event) throws IOException {
        eventLogger.logEvent(event);
    }
}
