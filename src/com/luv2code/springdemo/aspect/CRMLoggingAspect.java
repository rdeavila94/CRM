package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declarations
	
	//add @before advice
	@Before("PointcutExpression.forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		//get the method that called
		String method = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("@Before advice with method: " + method);
		
		//display the arguments
		displayArgs(theJoinPoint.getArgs());
	}
	
	
	//add @after advice
	@AfterReturning(
		pointcut="PointcutExpression.forAppFlow()",
		returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		//display the method
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("@Returning from method: " + method);
		
		//display the result
		myLogger.info(theResult);
	}

	private void displayArgs(Object [] args) {
		for (Object object: args)
			myLogger.info("Args " + object);
	}
}
