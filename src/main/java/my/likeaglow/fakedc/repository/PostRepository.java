package my.likeaglow.fakedc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.DeletePostDTO;
import my.likeaglow.fakedc.model.PostAuthCheckDTO;
import my.likeaglow.fakedc.model.PostListDTO;
import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.model.PostViewCountDTO;
import my.likeaglow.fakedc.model.RecommendPostDTO;
import my.likeaglow.fakedc.model.UpdatePostDTO;
import my.likeaglow.fakedc.model.writePostDTO;

@Repository
public interface PostRepository {

  // 글쓰기
  public void savePost(writePostDTO writePostDTO);
  // Repository 클래스의 메소드명(Mapper의 id 속성값)은 데이터베이스의 관점에서 지어준다.

  // 글보기
  public PostVO selectPost(Long postId);

  // 글 목록 불러오기
  public List<PostVO> postList(PostListDTO postListDTO);

  // 게시글 본인 인증
  public void authCheck(PostAuthCheckDTO postAuthCheckDTO);

  // 글 수정하기
  public PostVO updatePost(UpdatePostDTO updatePostDTO);

  // 글 삭제하기
  public void deletePost(DeletePostDTO deletePostDTO);

  // 글 조회수 증가
  public void increaseViewCount(PostViewCountDTO postViewCountDTO);

  // 글 추천/비추천
  public void updateRecommend(RecommendPostDTO recommendPostDTO);
}
