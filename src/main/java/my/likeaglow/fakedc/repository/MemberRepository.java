package my.likeaglow.fakedc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.MemberVO;
import my.likeaglow.fakedc.model.RegisterDTO;

@Repository
public interface MemberRepository {

  public List<MemberVO> findAll();

  public LoginMemberDTO checkAuth(AuthCheckDTO authCheckDTO);

  public void register(RegisterDTO registerDTO);

}
