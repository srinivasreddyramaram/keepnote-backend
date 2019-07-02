package com.stackroute.keepnote.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of Reminder controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	// Pointcut expression for all controller methods
		@Pointcut("execution (* com.stackroute.keepnote.controller.ReminderController.*(..))")
		public void allControllerMethods() {

		}

		@Before("allControllerMethods()")
		public void beforeAdvice(JoinPoint joinPoint) {
			logger.info("========Running beforeAdvice method=========");
			logger.debug("Method name: " + joinPoint.getSignature().getName());
			logger.info("========Exiting beforeAdvice method=========");
		}

		@After("allControllerMethods()")
		public void afterAdvice(JoinPoint joinPoint) {
			logger.info("==========Running afterAdvice method ===========");
			logger.debug("Method name: " + joinPoint.getSignature().getName());
			logger.info("========= Exiting afterAdvice method =========");
		}

		@AfterReturning(value = "allControllerMethods()", returning = "result")
		public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
			logger.info("==========Running afterReturningAdvice method ===========");
			logger.debug("Method name: " + joinPoint.getSignature().getName());
			logger.debug("Returned result: " + result);
			logger.info("========= Exiting afterReturningAdvice method =========");

		}

		@AfterThrowing(value = "allControllerMethods()", throwing = "error")
		public void afterThrowingAdvice(JoinPoint joinPoint, Throwable error) {
			logger.info("==========Running   method ===========");
			logger.debug("Method name: " + joinPoint.getSignature().getName());
			logger.debug("Thrown error: " + error);
			logger.info("========= Exiting afterThrowingAdvice method =========");
		}
}
