package my.likeaglow.fakedc.repository;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.PostVO;

@Repository
public interface PostRepository {

  public void savePost(PostVO postVO);
  // Repository 클래스의 메소드명(Mapper의 id 속성값)은 데이터베이스의 관점에서 지어준다.

}
