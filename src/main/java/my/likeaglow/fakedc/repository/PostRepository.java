package my.likeaglow.fakedc.repository;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.PostVO;

@Repository
public interface PostRepository {

	public void savePost(PostVO postVO);
	
}
