package com.example.ecommerce.project.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GeneralInterceptorAspect {

	Logger logger = Logger.getLogger(getClass().getName());

	//This will run just before execution of any method.
	@Before(value="execution(* com.example.ecommerce.project..*(..)) && !execution(* com.example.ecommerce.project.jwt..*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("Before Method Invoked sign>>>>>>>"+joinPoint.getSignature().getName());
		logger.info("Before Method Invoked arg >>>>>>>"+joinPoint.getArgs());

	}

	//This will run just after execution of any method.
	@After(value="execution(* com.example.ecommerce.project..*(..)) && !execution(* com.example.ecommerce.project.jwt..*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		logger.info("After Method Invoked  >>>>>>>"+joinPoint.getSignature());
	}

	//This will run as soon as function returns its value.
	@AfterReturning(value="execution(* com.example.ecommerce.project..*(..)) && !execution(* com.example.ecommerce.project.jwt..*(..))")
	public void afterReturn(JoinPoint joinPoint) {
		logger.info("After Method Return  >>>>>>>");
	}

	//This executes before and after join point.
	@Around(value="execution(* com.example.ecommerce.project..*(..)) && !execution(* com.example.ecommerce.project.jwt..*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Before method invoked signature: " + joinPoint.getSignature());
		logger.info("Before method invoked arguments: " + joinPoint.getArgs());
		logger.info("Before method invoked string: " + joinPoint.toString());
		Object result = null;
		try {
			result = joinPoint.proceed();
			logger.info("Method executed successfully with result: " + result);
		} catch (Throwable e) {
			logger.severe("Exception occurred: " + e.getMessage());
			throw e;
		}
		logger.info("After method invoked signature: " + joinPoint.getSignature());
		logger.info("After method invoked arguments: " + joinPoint.getArgs());
		logger.info("After method invoked string: " + joinPoint.toString());
		return result;
	}

}
