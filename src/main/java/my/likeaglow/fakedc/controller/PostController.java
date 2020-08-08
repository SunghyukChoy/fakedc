package my.likeaglow.fakedc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@GetMapping("/write")
	public ModelAndView write() {
		
		ModelAndView mv = new ModelAndView("post/write");
		
		
		return mv;
	}

}
