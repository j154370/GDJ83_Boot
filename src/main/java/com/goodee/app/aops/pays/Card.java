package com.goodee.app.aops.pays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class Card {

	@Around("execution(* com.goodee.app.aops.transfers.Transfer.take*(..))")
	public void cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("=====Before 카드를 찍었습니다======");
		log.info("=== ARGS : {}", joinPoint.getArgs());
		Object obj = joinPoint.proceed();
		log.info("=== Return: {}", obj);
		log.info("=====After 카드를 찍었습니다======");
	}
}
