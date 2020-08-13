package my.likeaglow.fakedc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.DetailDTO;
import my.likeaglow.fakedc.model.PostListDTO;
import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.model.writePostDTO;

@Repository
public interface PostRepository {

  // 글쓰기
  public void savePost(writePostDTO writePostDTO);
  // Repository 클래스의 메소드명(Mapper의 id 속성값)은 데이터베이스의 관점에서 지어준다.

  // 글보기
  public PostVO selectPost(DetailDTO detailDTO);

  // 글 목록 불러오기
  public List<PostVO> postList(PostListDTO postListDTO);

}
