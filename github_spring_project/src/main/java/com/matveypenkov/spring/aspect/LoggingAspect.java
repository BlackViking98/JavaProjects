package com.matveypenkov.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Around("execution (* com.matveypenkov.spring.dao.*.*(..))")
    public Object allMethodsLoggeringAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();
        System.out.println("Start of " + methodName + " method.");
        Object methodResult = proceedingJoinPoint.proceed();
        System.out.println("End of " + methodName + " method.");
        return methodResult;
    }
}
