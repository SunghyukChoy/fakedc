package my.likeaglow.fakedc.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.likeaglow.fakedc.common.GlobalVariable;
import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LeaveDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.MemberVO;
import my.likeaglow.fakedc.model.RegisterDTO;
import my.likeaglow.fakedc.model.UpdateMemberDTO;
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

    if (registerDTO.isInvalidDTO()) {
      logger.info("MemberService.register return 값 : null");
      return null;
    }

    logger.info("받은 registerVO : " + registerDTO);

    memberRepository.register(registerDTO);

    if (!registerDTO.isSuccessRegister()) {
      // 쿼리 수행 결과 ERR_CD, ERR_MSG 값이 registerDTO에 담김
      // 회원가입 실패 시.
      return null;
    }

    return registerDTO.getMEM_ID();
  }

  /**
   * 로그인
   * 
   * @param session
   * @param registerVO
   * @return
   */
  public LoginMemberDTO login(AuthCheckDTO authCheckDTO, HttpSession session) {
    logger.info("MemberService.login() 시작");

    // 유효성 검사
    if (authCheckDTO.getMEM_ID().equals("") || authCheckDTO.getMEM_PASSWORD().equals("")) {
      // 사용자가 아이디 또는 비밀번호를 입력하지 않아 authCheckDTO에 값이 없는 경우
      logger.info("MemberService.login return 값 : null");
      return null;
    }

    LoginMemberDTO loginMemberDTO = memberRepository.checkAuth(authCheckDTO);
    // 아이디가 맞지 않는 경우, 비밀번호가 맞지 않는 경우 등의 로직은 포로시저에서 실행
    // 쿼리가 작동하면 loginMemberDTO에는 MEM_ID, MEM_NAME이 담기고
    // authCheckDTO에는 기존의 데이터(view 페이지의 form 태그에서 받은 MEM_ID, MEM_PASSWORD)
    // logger.info(authCheckDTO.getMEM_ID());
    // 그리고 쿼리 실행 결과인 ERR_CD, ERR_MSG가 담김

    if (authCheckDTO.isLoginSuccess()) {
      session.setAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY, loginMemberDTO);
      // session에 "member"를 키로, loginMemberDTO를 value로 저장
    }
    
    return loginMemberDTO;
    // LoginMemberDTO 객체를 갖고 메소드를 호출했던 곳(MemberController.loginProcess() 메소드로 돌아감)
  }

  /**
   * 회원정보 불러오기
   * 
   * @param loginMember
   * @return
   */
  public MemberVO myInfo(String memberId) {
    logger.info("memberService.myInfo() 시작");

    MemberVO memberInfo = (MemberVO) memberRepository.myInfo(memberId);

    return memberInfo;
  }

  /**
   * 회원 정보 수정하기
   * 
   * @param updateMemberDTO
   * @return
   */
  public MemberVO updateInfo(UpdateMemberDTO updateMemberDTO) {

    logger.info("memberService.updateInfo() 시작");
    logger.info("받은 updateMemberDTO : " + updateMemberDTO);

    if (updateMemberDTO.getMEM_ID().equals("") || updateMemberDTO.getMEM_NAME().equals("")
        || updateMemberDTO.getMEM_PASSWORD().equals("") || updateMemberDTO.getEMAIL().equals("")
        || updateMemberDTO.getBIRTHDAY().equals("") || updateMemberDTO.getPHONE_NUM().equals("")
        || updateMemberDTO.getINFO_OFFER().equals("")) {
      logger.info("MemberService.updateInfo return 값 : null");
      return null;
    }

    MemberVO updatedVO = memberRepository.updateInfo(updateMemberDTO);
    logger.info("updateMemberDTO.getERR_CD() : " + updateMemberDTO.getERR_CD());
    if (updateMemberDTO.getERR_CD() != 1) {
      return null;
    }

    logger.info("memberService - 업데이트된 memberVO : " + updatedVO);

    return updatedVO;
  }

  /**
   * 회원 탈퇴
   * 
   * @param leaveDTO
   */
  public void leave(LeaveDTO leaveDTO) {

    logger.info("매퍼 실행 전 leaveDTO getERR_CD() : " + leaveDTO.getERR_CD());

    memberRepository.leave(leaveDTO);

    logger.info("매퍼 실행 후 leaveDTO getERR_CD() : " + leaveDTO.getERR_CD());
  }
}
