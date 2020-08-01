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
   * @param registerVO 회원정보
   * @return 성공시 MEM_ID 반환, 실패시 null 반환
   */
  public String register(RegisterDTO registerVO) {

    logger.info("받은 registerVO.getMEM_ID : " + registerVO.getMEM_ID());
    logger.info("받은 registerVO.getMEM_ID의 길이 : " + registerVO.getMEM_ID().length());
    logger.info("받은 registerVO.getMEM_ID는  빈 문자열인가 : " + registerVO.getMEM_ID().equals(""));

    // 대충 DB에 넣는 로직
    if (registerVO.getMEM_ID().equals("") || registerVO.getMEM_NAME().equals("")
        || registerVO.getMEM_PASSWORD().equals("") || registerVO.getEMAIL().equals("")
        || registerVO.getBIRTHDAY().equals("") || registerVO.getPHONE_NUM().equals("")
        || registerVO.getINFO_OFFER().equals("")) {
      logger.info("MemberService.register return 값 : null");
      return null;
    }

    // TODO : 로직 추가
    logger.info("받은 registerVO : " + registerVO);
    registerVO.setMEM_ID(registerVO.getMEM_ID());
    registerVO.setMEM_NAME(registerVO.getMEM_NAME());
    registerVO.setMEM_PASSWORD(registerVO.getMEM_PASSWORD());
    registerVO.setEMAIL(registerVO.getEMAIL());
    registerVO.setPHONE_NUM(registerVO.getPHONE_NUM());
    registerVO.setBIRTHDAY(registerVO.getBIRTHDAY());
    registerVO.setINFO_OFFER(registerVO.getINFO_OFFER());
    logger.info("set 후  registerVO : " + registerVO);
    logger.info("set 후  registerVO.getMEM_ID : " + registerVO.getMEM_ID());

    return registerVO.getMEM_ID();
  }

  // TODO 5번: 로그인 처리 메서드 추가
  /**
   * 로그인
   * 
   * @param registerVO
   * @return
   */
  public LoginMemberDTO login(AuthCheckDTO authCheckVO) {
    logger.info("MemberService.login() 시작");
    // logger.info("login()에서 받은 registerVO.getMEM_ID : " + registerVO.getMEM_ID());

    if (authCheckVO.getMEM_ID().equals("") || authCheckVO.getMEM_PASSWORD().equals("")) {
      logger.info("MemberService.login return 값 : null");
      return null;
    }

    LoginMemberDTO result = memberRepository.checkAuth(authCheckVO);

//    logger.info("MemberService.login return하는 authCheck.getMEM_ID() : " + authCheckVO.getMEM_ID());
    return result;
  }
}
