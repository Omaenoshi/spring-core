package com.yet.spring;

import com.yet.spring.bean.Client;
import com.yet.spring.bean.Event;
import com.yet.spring.bean.EventType;
import com.yet.spring.logger.ConsoleEventLogger;
import com.yet.spring.logger.EventLogger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean("app", App.class);
        Event event = context.getBean("event", Event.class);
        app.logEvent(EventType.INFO, event, "Some event for user 1");

        Event event2 = context.getBean("event", Event.class);
        app.logEvent(EventType.ERROR, event2,"Some event for user 1");

        Event event3 = context.getBean("event", Event.class);
        app.logEvent(null, event3, "Some event for user 1");

        context.close();
    }

    public App() {
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, Event event, String msg) throws IOException {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }

        logger.logEvent(event);
    }
}
