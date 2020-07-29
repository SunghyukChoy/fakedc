package my.likeaglow.fakedc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.model.RegisterVO;
import my.likeaglow.fakedc.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

  @GetMapping("/register") // @RequestMapping 대체. RequestMethod 설정 불필요. Get으로 명시. 괄호 안은 매핑된 URL 주소
  public ModelAndView register() {
    ModelAndView mv = new ModelAndView("member/register");
    // mv.setViewName("member/register"); // new ModelAndView("member/register")와 같음
    // 보낼 view 페이지(.jsp 파일 이름) 설정
    setTestRegister(mv);
    // mv 객체를 파라미터로 넣고 setTestRegister 메소드 실행

    return mv;
    // mv 객체에 데이터가 담긴 후 view 페이지로 이동
  }

  // 기본값 설정 메소드.
  private void setTestRegister(ModelAndView mv) {
    RegisterVO registerVO = new RegisterVO();
    // RegisterVo 객체 생성
    registerVO.setMEM_ID("likeaglow");
    registerVO.setMEM_NAME("최성혁");
    registerVO.setMEM_PASSWORD("1111");
    registerVO.setEMAIL("likeaglow@google.com");
    registerVO.setPHONE_NUM("010-1111-1111");
    registerVO.setBIRTHDAY("1988-02-04");
    registerVO.setINFO_OFFER("agree");
    // RegisterVO 객체의 필드들에 값을 설정

    mv.addObject("vo", registerVO);
    // addObject(String name, Object value)
    // "vo" : view 페이지에 전달할 값을 담은 key. view 페이지에서 이 key로 데이터 접근.
    // registerVO : view 페이지에 전달할 값을 담은 객체. value
  }

  /**
   * 회원가입 프로세스
   * 
   * @param registerVO 회원 정보
   * @return 성공시 로그인 화면으로 전환, 실패시 포스트백
   */
  @PostMapping("/register")
  public ModelAndView registerProcess(RegisterVO registerVO) {
    // ModelAndView에서 Model의 의미 : 데이터를 담을 그릇을 의미. Value Object(VO).
    // RegisterVO는 회원가입 데이터를 담는 객체
    String memberId = memberService.register(registerVO);

    if (memberId == null) {
      ModelAndView mv = new ModelAndView("member/register");
      mv.addObject("vo", registerVO);
      return mv;
    }

    logger.info("제출된 멤버 값 : " + registerVO);
    // registerVO에 담긴 값을 찍어내는 로거. RegisterVO 클래스에 @ToString 어노테이션 붙여줘서 가능.

    return new ModelAndView("redriect:/member/login");
  }

  /**
   * ' 회원 로그인 페이지
   * 
   * @return 로그인 페이지 호출
   */
  @GetMapping("/login")
  public ModelAndView login() {
    return null;
  }

  /**
   * 회원 로그인 프로세스
   * 
   * @param authCheckVO 로그인 체크 객체
   * @return 성공시 개인정보 상세페이지로 이동, 실패시 포스트백
   */
  @PostMapping("/login")
  public ModelAndView loginProcess() {
    return null;
  }
}
