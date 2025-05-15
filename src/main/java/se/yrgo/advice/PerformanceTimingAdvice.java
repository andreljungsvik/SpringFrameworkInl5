package se.yrgo.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceTimingAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.nanoTime();

        try {
            return methodInvocation.proceed();
        } finally {
            long endTime = System.nanoTime();
            double timeTakenMs = (endTime - startTime) / 1000000.0;

            String methodName = methodInvocation.getMethod().getName();
            String className = methodInvocation.getMethod().getDeclaringClass().getName();

            System.out.printf(
                    "Time taken for the method %s from the class %s took %.4fms%n",
                    methodName, className, timeTakenMs
            );
        }
    }
}