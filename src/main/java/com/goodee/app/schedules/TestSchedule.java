package com.goodee.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.app.qna.QnaMapper;
import com.goodee.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	
	@Autowired
	private QnaMapper qnaMapper;
	
//	@Scheduled(cron="*/5 * * * * *")
//	public void test3() throws Exception{
//		log.error("Qna Update 5초 간격으로");
//		QnaVO qnaVO = new QnaVO();
//		qnaVO.setBoard_writer("test");
//		qnaVO.setBoard_title("test");
//		qnaVO.setBoard_contents("test");
//		qnaMapper.add(qnaVO);
//		qnaMapper.refUpdate(qnaVO);
//	}

}
