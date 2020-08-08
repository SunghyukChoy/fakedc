package my.likeaglow.fakedc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.model.PostVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	/**
	 * 게시판 게시글 출력
	 * @param boardId
	 * @return
	 */
	@GetMapping("/{boardId}")
	public ModelAndView postList(@PathVariable String boardId) {
		
		int pageIndex = 0;
		int pageSize = 10;
		int searchType = 0;	// 없음
		String keyword = "";
		int postType = 1;	// 일반
		
		/*
		 * 숙제 #3
		 * PostService.getPosts 메서드가 DB에서 값을 가져올 수 있게 완성시키고
		 * 리스트가 정상적으로 가져와지는지 로그로 확인하는 로직을 작성할 것
		 * 
		 * 추가: 리스트 페이지를 만들면 베리 굿
		 */
		
		return null;
	}
	
}
