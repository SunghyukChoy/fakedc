package my.likeaglow.fakedc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.likeaglow.fakedc.model.PostVO;

@Service
public class BoardService {

	@Autowired
	private PostService postService;
	
	public List<PostVO> getPosts() {
		return postService.getPosts();
	}
	
}
