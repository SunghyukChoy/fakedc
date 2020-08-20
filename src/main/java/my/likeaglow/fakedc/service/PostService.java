package my.likeaglow.fakedc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.likeaglow.fakedc.model.PostAuthCheckDTO;
import my.likeaglow.fakedc.model.PostListDTO;
import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.model.UpdatePostDTO;
import my.likeaglow.fakedc.model.writePostDTO;
import my.likeaglow.fakedc.repository.PostRepository;

@Service
public class PostService {

  private static final Logger logger = LoggerFactory.getLogger(PostService.class);

  @Autowired
  private PostRepository postRepository;

  public List<PostVO> getPosts() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * 글쓰기 등록에 성공하면 POST_ID를 출력 게시글을 등록하기 위한 상태가 유효하지 않으면 -1 출력 데이터베이스에 등록이 실패하면 -2
   * 출력
   * 
   * @author sunghyuk choi
   * @param writePostDTO 게시글 객체
   * @return 게시글 ID값
   */
  public long write(writePostDTO writePostDTO) {

    logger.info("PostService.write() 메서드 시작");
    if (writePostDTO.isInvalid()) {
      return -1;
    }

    postRepository.savePost(writePostDTO);

    logger.info("쿼리 실행 후 얻은 POST_ID : " + writePostDTO.getPOST_ID());

    if (!writePostDTO.isSuccessCall()) {
      return -2;
    }

    return writePostDTO.getPOST_ID();
  }

  /**
   * 글 보기
   * 
   * @param postId
   * @return
   */
  public PostVO detail(Long postId) {

    PostVO postVO = postRepository.selectPost(postId);

    return postVO;
  }

  /**
   * 게시글 불러오기
   * 
   * @param postListDTO
   * @return
   */
  public List<PostVO> getPosts(PostListDTO postListDTO) {
    logger.info("PostService.getPosts() 시작");

    List<PostVO> list = postRepository.postList(postListDTO);

    return list;

  }

  /**
   * 게시글 업데이트
   * 
   * @param updatePostDTO
   * @param memberId
   * @return
   */
  public PostVO update(PostAuthCheckDTO postAuthCheckDTO, UpdatePostDTO updatePostDTO) {
    logger.info("PostService.updatePost() 시작");

    logger.info("인증 전 postAuthCheckDTO ERR_CD : " + postAuthCheckDTO.getERR_CD());
    postRepository.authCheck(postAuthCheckDTO);
    logger.info("인증 후 postAuthCheckDTO ERR_CD : " + postAuthCheckDTO.getERR_CD());

    if (!postAuthCheckDTO.isSuccessCall()) { // 본인 게시글임이 확인되지 않는다면 여기서 리턴됨.
      return null;
    }

    PostVO updatedPost = postRepository.updatePost(updatePostDTO);

    logger.info("업데이트된 postVO : " + updatedPost);

    if (!updatePostDTO.isSuccessCall()) {
      return null;
    }
    return updatedPost;
  }
}
