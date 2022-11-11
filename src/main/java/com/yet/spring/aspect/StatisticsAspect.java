package com.yet.spring.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class StatisticsAspect {

    private int count;

    @Pointcut("execution(* *EventLogger.logEvent(..))")
    private void allCountEventLoggers() {}

}
