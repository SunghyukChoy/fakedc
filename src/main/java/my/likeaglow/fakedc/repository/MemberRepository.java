package my.likeaglow.fakedc.repository;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;

@Repository
public class MemberRepository {

  public LoginMemberDTO checkAuth(AuthCheckDTO authCheckVO) {
    // TODO Auto-generated method stub
    return new LoginMemberDTO("TestID", "최성혁", 1, "로그인 성공");
  }

}
