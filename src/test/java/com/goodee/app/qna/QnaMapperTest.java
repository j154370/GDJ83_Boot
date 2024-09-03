package com.goodee.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.goodee.app.util.Pager;

@SpringBootTest
class QnaMapperTest {

	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	void getListTest() throws Exception{
		
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.setKind("k1");
		pager.setSearch("2");
		pager.makeRow();
		
		List<QnaVO> list = qnaMapper.getList(pager);
		
		assertNotEquals(0, list.size());
	}
	
//	@Test
//	void addTest() throws Exception{
//		
//		for(int i=4; i < 110; i++) {
//			QnaVO qnaVO = new QnaVO();
//			qnaVO.setBoard_writer("w" + i);
//			qnaVO.setBoard_title("t" + i);
//			qnaVO.setBoard_contents("c" + i);
//			qnaVO.setRef((long) i);
//			qnaVO.setStep(0L);
//			qnaVO.setDepth(0L);
//			
//			if(i%10==0) {
//				Thread.sleep(500);
//			}
//			
//			int result = qnaMapper.add(qnaVO);
//		}
//	}

}
