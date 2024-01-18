package com.routinise.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.routinise.controller..*(..))")
    public void allController() {}

    @Pointcut("execution(* com.routinise.service..*(..))")
    public void allService() {}

    @Pointcut("allController() && allService()")
    public void allControllerAndService() {}

}
