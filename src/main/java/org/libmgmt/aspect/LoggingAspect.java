package org.libmgmt.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	@Before("execution(* org.libmgmt.dao.UserDao.findByUserName(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("********************!LOG BEFORE!********************");
	}

	@After("execution(* org.libmgmt.dao.UserDao.findByUserName(..))")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("********************!LOG AFTER!********************");
	}

	@AfterReturning(pointcut = "execution(* org.libmgmt.dao.UserDao.findByUserName(..))", returning = "returnValue")
	public void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
		System.out.println("********************!LOG AFTER RETURNING!********************");
		System.out.println("Email in Result: " + returnValue);
	}

	// @AfterThrowing(pointcut = "execution(*
	// org.libmgmt.dao.UserDao.findByUserName(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		System.out.println("********************!LOG AFTER THROWING!********************");
		System.out.println("Exception : " + error);
	}

	// @Around("execution(* org.libmgmt.dao.UserDao.findByUserName(..))")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("********************!	LOG AROUND		!********************");
		System.out.println("********************!	AROUND BEFORE	!********************");
		joinPoint.proceed();
		System.out.println("********************!	AROUND AFTER	!********************");
	}

}