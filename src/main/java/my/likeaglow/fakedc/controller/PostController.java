package my.likeaglow.fakedc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@GetMapping("/write")
	public ModelAndView write() {

		ModelAndView mv = new ModelAndView("post/write");

		setTestPost(mv);
		
		return mv;
	}

	private void setTestPost(ModelAndView mv) {
		PostVO postVO = new PostVO();
		postVO.setBOARD_ID("test");
		postVO.setPOST_TITLE("테스트 게시글입니다.");
		postVO.setPOST_PASSWORD("1111");
		postVO.setPOST_TYPE(1);
		postVO.setCREATE_USER("likeaglow");
		postVO.setPOST_CONTENT("게시글 테스트입니다.\n잘부탁드립니다.");
		
		mv.addObject("vo", postVO);
	}

	@PostMapping("/write")
	public ModelAndView writeProcess(PostVO postVO) {
		logger.info("parameter: " + postVO);
		long postId = postService.write(postVO);

		ModelAndView mv = new ModelAndView();
		// failure
		if (postId < 0) {

			mv.setViewName("post/write");
			mv.addObject("vo", postVO);
			return mv;
		}
		

		mv.setViewName("redirect:" + postId);
		return mv;
	}

	@GetMapping("/{postId}")
	public ModelAndView detail(@PathVariable long postId) {
		return new ModelAndView("post/detail");
	}

}
