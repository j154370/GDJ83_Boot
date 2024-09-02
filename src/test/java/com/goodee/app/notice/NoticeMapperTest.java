package com.goodee.app.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void getListTest() throws Exception{
		List<NoticeVO> list = noticeMapper.getList();
		
		for(NoticeVO noticeVO : list) {
			System.out.println(noticeVO.toString());
		}
		assertNotEquals(0, list.size());
	}
	
//	@Test
//	void addList() throws Exception{
//		
//		int result = noticeDAO.addList();
//		
//		assertNotEquals(0, result);
//	}

}
