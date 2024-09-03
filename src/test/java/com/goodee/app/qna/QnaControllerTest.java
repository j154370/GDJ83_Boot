package com.goodee.app.qna;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	void getListTest() throws Exception{
//		mockMvc.perform(
//				get("/qna/list")
//				.param("page", "1")
//				.param("kind", "k1")
//				.param("search", "2")
//				
//				)
//				.andDo(print())
//		;
//	}
//	
//	@Test
//	void getDetailTest() throws Exception{
//		mockMvc.perform(
//					get("/qna/detail")
//					.param("board_num", "108")
//				)
//				.andExpect(status().isOk())
//				.andDo(print())
//		;
//	}
	
	@Test
	void addTest() throws Exception{
		mockMvc.perform(
				post("/qna/add")
				.param("board_title", "목업")
				.param("board_writer", "목업정효")
				.param("board_contents", "목업활용")
				)
				.andDo(print())
		;
	}

}
