package com.goodee.app.qna;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	
	private Long board_num;
	private String board_writer;
	private String board_title;
	private String board_contents;
	private Date create_date;
	private Long ref;
	private Long step;
	private Long depth;

}
