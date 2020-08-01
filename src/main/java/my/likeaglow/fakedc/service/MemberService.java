package my.likeaglow.fakedc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.RegisterDTO;
import my.likeaglow.fakedc.repository.MemberRepository;

@Service
public class MemberService {

  private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

  @Autowired
  MemberRepository memberRepository;

  /**
   * 회원 가입
   * 
   * @param registerDTO 회원정보
   * @return 성공시 MEM_ID 반환, 실패시 null 반환
   */
  public String register(RegisterDTO registerDTO) {

    if (registerDTO.getMEM_ID().equals("") || registerDTO.getMEM_NAME().equals("")
        || registerDTO.getMEM_PASSWORD().equals("") || registerDTO.getEMAIL().equals("")
        || registerDTO.getBIRTHDAY().equals("") || registerDTO.getPHONE_NUM().equals("")
        || registerDTO.getINFO_OFFER().equals("")) {
      logger.info("MemberService.register return 값 : null");
      return null;
    }

    logger.info("받은 registerVO : " + registerDTO);

    memberRepository.register(registerDTO);

    if (registerDTO.getERR_CD() != 1) {
      return null;
    }

    return registerDTO.getMEM_ID();
  }

  /**
   * 로그인
   * 
   * @param registerVO
   * @return
   */
  public LoginMemberDTO login(AuthCheckDTO authCheckVO) {
    logger.info("MemberService.login() 시작");

    // 유효성 검사
    if (authCheckVO.getMEM_ID().equals("") || authCheckVO.getMEM_PASSWORD().equals("")) {
      logger.info("MemberService.login return 값 : null");
      return null;
    }

    return memberRepository.checkAuth(authCheckVO);
  }
}
