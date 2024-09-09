package com.goodee.app.qna;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	
	private Long board_num;
	@NotBlank
	private String board_writer;
	@Size(max=5, min=1)
	private String board_title;
	private String board_contents;
	private Date create_date;
	private Long ref;
	private Long step;
	private Long depth;

}
