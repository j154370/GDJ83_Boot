package com.goodee.app.Ioc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.goodee.app.robot.Robot;

@SpringBootTest
class IocTest {
	
	@Autowired
	private Robot robot;

	@Test
	void test() {
		System.out.println("test case");
		robot.getRobotArm().punch();
		assertNotNull(robot);
	}

}
