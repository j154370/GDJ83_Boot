package com.goodee.app.aops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goodee.app.aops.pays.Card;
import com.goodee.app.aops.transfers.Transfer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Start {
	
	@Autowired
	private Transfer transfer;
	
	@Autowired
	private Card card;
	
	public void go() {

		transfer.takeBus(50);

		transfer.takeSubway(15L, "jeonghyo");

		transfer.walk();
	}

}
