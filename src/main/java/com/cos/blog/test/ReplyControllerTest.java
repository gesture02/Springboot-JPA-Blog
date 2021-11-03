package com.cos.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get(); // jackson 라이브러리 작동 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
		
		//getter를 리턴하면서 무한참조가 생긴다.
	}
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
}
