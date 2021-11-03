package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {	//내가 필요한 데이터를 한방에 받을 수 있다.
	private int userId;
	private int boardId;
	private String content;
}
