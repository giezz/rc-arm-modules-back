package ru.rightcode.anketi.config;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(ru.rightcode.anketi.service..*)")
    public void serviceMethods() {
        // Pointcut for service layer methods
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        log.error("Exception in service layer: {}", exception.getMessage(), exception);
    }
}

