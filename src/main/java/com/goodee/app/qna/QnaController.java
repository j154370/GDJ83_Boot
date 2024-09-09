package com.goodee.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodee.app.util.Pager;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/qna/*")
@Slf4j
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
	public void add(QnaVO qnaVO) throws Exception{}
	
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult) throws Exception{
		
		if(bindingResult.hasErrors()) {
			log.error("Writer가 비어있다");
			return "/qna/add";
		}
		int result = qnaService.add(qnaVO);
		
		return "redirect:/qna/list";
	}
	
	@GetMapping("detail")
	public void getDetail(QnaVO qnaVO, Model model) throws Exception{
		
		qnaVO = qnaService.getDetail(qnaVO);
		
		model.addAttribute("qnaVO", qnaVO);
	}
}
