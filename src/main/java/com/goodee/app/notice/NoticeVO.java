package com.goodee.app.notice;

import java.sql.Date;

import lombok.Data;


// DTO = Data Transfer Object
// VO = Value Object (읽기 전용)

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Data  // getter, setter, 생성자 등등 전부 포함한 어노테이션
public class NoticeVO {

	private Long board_num;
	private String board_writer;
	private String board_title;
	private String board_contents;
	private Date create_date;
	
}
