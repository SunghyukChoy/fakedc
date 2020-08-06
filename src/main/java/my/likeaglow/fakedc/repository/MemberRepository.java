package my.likeaglow.fakedc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.MemberVO;
import my.likeaglow.fakedc.model.RegisterDTO;

@Repository
public interface MemberRepository {
  // 구현은 src/main/resources/mappers/MemberMapper.xml에서 구현
  // 메소드명과 같은 id를 가진 select 태르를 실행함
  // 설정은 src/main/webapp/WEB-INF/spring/root-context.xml에서 확인

  public List<MemberVO> findAll();

  public LoginMemberDTO checkAuth(AuthCheckDTO authCheckDTO);

  public void register(RegisterDTO registerDTO);

  public MemberVO myInfo(LoginMemberDTO loginMemberDTO);

}
