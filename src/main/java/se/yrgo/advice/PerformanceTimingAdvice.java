package se.yrgo.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceTimingAdvice {
    @Around("execution(* se.yrgo.services..*.*(..)) || execution(* se.yrgo.dataaccess..*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint jp) throws Throwable {
        long startTime = System.nanoTime();

        try {
            return jp.proceed();
        } finally {
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000.0;

            System.out.printf("Time taken for the method %s from the class %s took %.4fms%n",
                    jp.getSignature().getName(),
                    jp.getSignature().getDeclaringTypeName(),
                    duration);
        }
    }
}