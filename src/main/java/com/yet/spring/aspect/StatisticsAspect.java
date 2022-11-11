package com.yet.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticsAspect {
    private final Map<Class<?>, Integer> counter = new HashMap<>();

    @Pointcut("execution(* *.logEvent(..))")
    private void allCountEventLoggers() {}

    @AfterReturning(pointcut = "allCountEventLoggers()", returning = "jp")
    public void countAfter(JoinPoint jp) {
        Class<?> eventClass = jp.getTarget().getClass();
        if (!counter.containsKey(eventClass)) {
            counter.put(eventClass, 0);
        }

        counter.put(eventClass, counter.get(eventClass) + 1);
    }

    public Map<Class<?>, Integer> getCounter() {
        return Collections.unmodifiableMap(counter);
    }

    @After("execution(* com.yet.spring.App.logEvent(..))")
    public void outputLoggingCounter() {
        System.out.println("Loggers statistics. Number of calls: ");
        for (Map.Entry<Class<?>, Integer> entry : counter.entrySet()) {
            System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
        }
    }
}
