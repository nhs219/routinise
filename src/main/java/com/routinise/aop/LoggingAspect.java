package com.routinise.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.JSONObject;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("com.routinise.aop.Pointcuts.allController()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info("{}({}) Request --> {} {} {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), request.getMethod(), request.getRequestURL(), getRequestBody(joinPoint) ,getHeaders(request));
    }

    @AfterReturning(value = "com.routinise.aop.Pointcuts.allControllerAndService()", returning = "response")
    public void doAfterReturning(JoinPoint joinPoint, Object response) {

        log.info("{}({}) Response --> {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), response);
    }

    @AfterThrowing(value = "com.routinise.aop.Pointcuts.allControllerAndService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("{}({}) Exception --> message : {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "com.routinise.aop.Pointcuts.allControllerAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("{}({}) Result", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature());
    }

    private ArrayList<String> getHeaders(HttpServletRequest request) {
        ArrayList<String> headers = new ArrayList<>();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> headers.add(headerName + ":" + request.getHeader(headerName)));

        return headers;
    }

    private JSONObject getRequestBody(JoinPoint joinPoint) {
        return new JSONObject(joinPoint.getArgs()[0]);
    }

}
