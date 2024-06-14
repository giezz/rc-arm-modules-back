package ru.rightcode.arm.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("@within(ru.rightcode.arm.annotation.Loggable)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logControllerMethods(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if (attributes == null) {
            log.info("Request attributes are null, Controller: {}, Method: {}", className, methodName);
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String ipAddr = getIpAddress(request);
        String userLogin = request.getUserPrincipal().getName();
        log.info("IPAddr: {}, User: {}, Controller: {}, Method: {}", ipAddr, userLogin, className, methodName);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddr = request.getHeader("X-Forwarded-For");
        if (ipAddr == null){
            ipAddr = request.getRemoteAddr();
        }

        return ipAddr;
    }

}
