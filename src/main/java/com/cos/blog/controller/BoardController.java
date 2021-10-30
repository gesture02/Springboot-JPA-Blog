package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})//아무것도 안적었을 때, /붙였을 때 홈으로 이동
	public String index(Model model, @PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {	//컨트롤러에서 세션에 접근하는법 @AuthenticationPrincipal PrincipalDetail principal (매개변수에)
		//System.out.println("login username : " + principal.getUsername());
		
		//model은 해당 데이터를 가지고 view까지 이동함
		model.addAttribute("boards", boardService.글목록(pageable)); // index라는 페이지로 boards가 넘어감
		return "index"; // viewResolver 작동 -> 해당 index 페이지로 model의 정보를 들고 이동
	}
	
	@GetMapping("/board/{id}")
	public String findById(Model model, @PathVariable int id) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	//USER 권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(Model model, @PathVariable int id) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
}
