package ru.rightcode.arm.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@within(ru.rightcode.arm.annotation.Loggable)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logControllerMethods(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if (attributes == null) {
            log.info("Request attributes are null, Controller: {}, Method: {}", className, methodName);
            return;
        }
        request = attributes.getRequest();
        String ipAddr = request.getRemoteAddr();
        String userLogin = request.getUserPrincipal().getName();
        log.info("IPAddr: {}, User: {}, Controller: {}, Method: {}", ipAddr, userLogin, className, methodName);
    }
}
