package my.likeaglow.fakedc.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public List<PostVO> getPosts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 글쓰기
	 * 등록에 성공하면 POST_ID를 출력
	 * 게시글을 등록하기 위한 상태가 유효하지 않으면 -1 출력
	 * 데이터베이스에 등록이 실패하면 -2 출력
	 * 
	 * @author sunghyuk choi
	 * @param postVO 게시글 객체
	 * @return 게시글 ID값
	 */
	public long write(PostVO postVO) {
		
		if(postVO.isInvalid()) {
			return -1;
		}
		
		postRepository.savePost(postVO);
		
		if(!postVO.isSuccessCall()) {
			return -2;
		}
		
		return postVO.getPOST_ID();
	}
}
