package com.goodee.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodee.app.util.Pager;


@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("list")
	public void getList(Pager pager, Model model) throws Exception{
		
		List<QnaVO> list = qnaService.getList(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
	}
	
	@GetMapping("add")
	public void add() throws Exception{}
	
	@PostMapping("add")
	public String add(QnaVO qnaVO) throws Exception{
		
		int result = qnaService.add(qnaVO);
		
		return "redirect:/qna/list";
	}
	
	@GetMapping("detail")
	public void getDetail(QnaVO qnaVO, Model model) throws Exception{
		
		qnaVO = qnaService.getDetail(qnaVO);
		
		model.addAttribute("qnaVO", qnaVO);
	}
}
