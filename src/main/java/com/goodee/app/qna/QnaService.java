package com.goodee.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		
		pager.makeRow();
		
		return qnaMapper.getList(pager);
	}
	
	
	public int add(QnaVO qnaVO) throws Exception{
		log.info("============== before board_num : {}", qnaVO.getBoard_num());
		int result = qnaMapper.add(qnaVO);		
		log.info("============== After board_num : {}", qnaVO.getBoard_num());
		result = qnaMapper.refUpdate(qnaVO);
		
		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
}
