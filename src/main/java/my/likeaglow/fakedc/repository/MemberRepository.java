package my.likeaglow.fakedc.repository;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.AuthCheckVO;
import my.likeaglow.fakedc.model.LoginMemberVO;

@Repository
public class MemberRepository {

  public LoginMemberVO checkAuth(AuthCheckVO authCheckVO) {
    // TODO Auto-generated method stub
    return new LoginMemberVO("TestID", "최성혁", 1, "로그인 성공");
  }

}
