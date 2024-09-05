package com.goodee.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Transaction {

	
	@AfterThrowing("execution(* com.goodee.app.*.*.set*(..))")
	public void roolBack() {
		
	}
}
