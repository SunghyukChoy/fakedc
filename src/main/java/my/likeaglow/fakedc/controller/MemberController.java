package my.likeaglow.fakedc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.RegisterDTO;
import my.likeaglow.fakedc.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
  // Console 창에 로그 찍는 객체. getLogger(현재클래스.class)

  @GetMapping("/register") // @RequestMapping 대체. RequestMethod 설정 불필요. Get으로 명시. 괄호 안은 매핑된 URL 주소
  public ModelAndView register() {
    ModelAndView mv = new ModelAndView("member/register");
    // mv.setViewName("member/register"); // new ModelAndView("member/register")와 같음
    // 보낼 view 페이지(.jsp 파일 이름) 설정
    setTestRegister(mv);
    // mv 객체를 파라미터로 넣고 setTestRegister 메소드 실행

    return mv;
    // mv 객체에 데이터(아래의 setTestRegister에 담긴 기본값)가 담긴 후 view 페이지로 이동
  }

  // 기본값 설정 메소드.
  private void setTestRegister(ModelAndView mv) {
    RegisterDTO registerVO = new RegisterDTO();
    // RegisterVO 객체 생성
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
   * @param registerDTO 회원 정보
   * @return 성공시 로그인 화면으로 전환, 실패시 포스트백
   */
  @PostMapping("/register")
  public ModelAndView registerProcess(RegisterDTO registerDTO) {
    // ModelAndView에서 Model의 의미 : 데이터를 담을 그릇을 의미. Value Object(VO).
    // RegisterVO는 회원가입 데이터를 담는 객체
    String memberId = memberService.register(registerDTO);
    // registerVO를 매개변수로 넘겨 memberService 클래스의 register 메소드 실행
    // 회원가입 성공 시 registerVO.getMEM_ID() 값을 변수 memberId에 저장.

    if (memberId == null) { // 회원가입 실패 시 포스트 백(postback)
      ModelAndView mv = new ModelAndView("member/register");
      // 이동할 (다시 되돌아갈) view 페이지 설정하면서 mv 객체 생성
      mv.addObject("vo", registerDTO);
      // 매개변수로 받은 registerVO를 value로, "vo"를 key로 하여 mv 객체에 저장.
      // 사용자가 입력한 데이터를 그대로 유지하여 view 페이지로 돌아감을 의미.
      return mv;
      // mv 객체를 가지고 /member/register.jsp 페이지로 이동.
      // view 페이지에서는 다시 데이터를 입력 받아 현재 메소드로 이동.
    }

    logger.info("제출된 멤버 값 : " + registerDTO);
    // registerVO에 담긴 값을 찍어내는 로거. RegisterVO 클래스에 @ToString 어노테이션 붙여줘서 가능.

    return new ModelAndView("redirect:/member/login");
    // 회운가입 성공 시 /member/login.jsp 페이지로 이동
  }

  /**
   * 회원 로그인 페이지
   * 
   * @return 로그인 페이지 호출
   */
  @GetMapping("/login")
  public ModelAndView login() {
    // TODO 1번: mv 만들어서 테스트용 AuthCheckVO 객체를 만들어서 모델로 삽입하여 view에서 사용하도록 함
    ModelAndView mv = new ModelAndView("member/login");

    setTestLogin(mv);

    return mv;
  }

  private void setTestLogin(ModelAndView mv) {
    AuthCheckDTO authCheckVO = new AuthCheckDTO();

    authCheckVO.setMEM_ID("LIKEAGLOW");
    authCheckVO.setMEM_PASSWORD("1234");

    mv.addObject("vo", authCheckVO);
  }

  /**
   * 회원 로그인 프로세스
   * 
   * @param authCheckVO 로그인 체크 객체
   * @return 성공시 개인정보 상세페이지로 이동, 실패시 포스트백
   */
  @PostMapping("/login")
  public ModelAndView loginProcess(AuthCheckDTO authCheckVO) {
    // TODO 3번 : 아래 로깅을 통해서 AuthCkeckVO의 필드값을 체크하기 위해 AuthCheckVO 에 적절한 어노테이션 삽입, 아래
    // 코드는 수정하지 말 것
    logger.debug("로그인 객체 로그 : " + authCheckVO);

    LoginMemberDTO loginVO = memberService.login(authCheckVO);

    if (authCheckVO.getERR_CD() != 1) {
      ModelAndView mv = new ModelAndView("member/login");
      authCheckVO.setMEM_PASSWORD("");

      mv.addObject("vo", authCheckVO);
      return mv;
    }

    // TODO 4번: 멤버 서비스에 적절한 로그인 시도 메서드를 성공하여 결과에 따라 다음과 같이 처리한다
    // 로그인 성공시 : 메인 페이지("/")로 이동
    // 로그인 실패시 : 로그인 페이지를 다시 불러오되 입력한 아이디만 출력되도록 함
    return new ModelAndView("redirect:/");
  }
}
