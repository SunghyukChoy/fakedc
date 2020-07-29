package my.likeaglow.fakedc.service;

import org.springframework.stereotype.Service;

import my.likeaglow.fakedc.model.RegisterVO;

@Service
public class MemberService {

  /**
   * 회원 가입
   * 
   * @param registerVO 회원정보
   * @return 성공시 MEM_ID 반환, 실패시 null 반환
   */
  public String register(RegisterVO registerVO) {

    // 대충 DB에 넣는 로직
    if (registerVO.getMEM_ID() == null || registerVO.getMEM_NAME() == null || registerVO.getMEM_PASSWORD() == null
        || registerVO.getEMAIL() == null || registerVO.getPHONE_NUM() == null || registerVO.getBIRTHDAY() == null
        || registerVO.getINFO_OFFER() == null) {
      return null;
    }
    // TODO : 로직 추가
    registerVO.setMEM_ID(registerVO.getMEM_ID());
    registerVO.setMEM_NAME(registerVO.getMEM_NAME());
    registerVO.setMEM_PASSWORD(registerVO.getMEM_PASSWORD());
    registerVO.setEMAIL(registerVO.getEMAIL());
    registerVO.setPHONE_NUM(registerVO.getPHONE_NUM());
    registerVO.setBIRTHDAY(registerVO.getBIRTHDAY());
    registerVO.setINFO_OFFER(registerVO.getINFO_OFFER());

    return registerVO.getMEM_ID();
  }
}
