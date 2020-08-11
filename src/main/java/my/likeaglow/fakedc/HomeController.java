package my.likeaglow.fakedc;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.common.GlobalVariable;
import my.likeaglow.fakedc.model.AuthCheckDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.MemberVO;
import my.likeaglow.fakedc.repository.MemberRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private MemberRepository memberRepository;

  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model, HttpSession session) {
    // 컨트롤러 클래스의 메소드 리턴 타입이 String이면 리턴 값은 view 페이지의 경로를 나타낸다.(jsp 파일의 경로)
    logger.info("Welcome home! The client locale is {}.", locale);

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);
    model.addAttribute("member", session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY));

    return "home";
  }

  /**
   * 에러 페이지
   * 
   * @return
   */
  @GetMapping("/error")
  public ModelAndView error() {
    ModelAndView mv = new ModelAndView("error/errorpage");
    // new ModelAndView("jsp 파일 경로")
    return mv;
  }

  @GetMapping(value = "/test")
  public String test() {
    List<MemberVO> members = memberRepository.findAll();
    // Service를 거치지 않고 바로 Repository에 접근. 모든 열을 가져옴.
    // 열 하나는 MemberVO 객체 하나. MemberVO 객체들을 담을 list 선언
    for (MemberVO memberVO : members) {
      logger.info("result : " + memberVO);
    }

    return "home";
  }

  @GetMapping(value = "/test2")
  public String test2() {
    // Arrange (할당, 테스트할 값 선언)
    AuthCheckDTO auth = new AuthCheckDTO("LIKEAGLOW", "1234");

    // Action (테스트 하려고 하는 메서드 실행)
    LoginMemberDTO login = memberRepository.checkAuth(auth);

    // Assert (결과 확인, 예상된 결과가 나왔는지)
    if (login != null && login.getMEM_ID().equals(auth.getMEM_ID())) {
      logger.info("로그인 한 사용자의 정보가 제대로 출력되었습니다 - 이름 : " + login.getMEM_NAME());
    } else {
      logger.error("데이터베이스 로직에 문제가 있습니다.");
    }

    logger.info("auth : " + auth);
    logger.info("login : " + login);
//    if (auth.getERR_CD() == 1) {
//      logger.info("에러코드 결과가 제대로 출력되었습니다");
//    } else {
//      logger.info("에러코드가 예상하지 않은 값입니다 : " + auth.getERR_CD());
//    }

    return "home";
  }

  // 날짜 객체 생성
  public static final Date INSANE_DATE = new Date(2020, 8, 11);
  public static final LocalDateTime INSANE_DATE2 = LocalDateTime.of(2020, 8, 20, 0, 0, 0);

  @GetMapping(value = "/test3")
  public void test3() {

    INSANE_DATE.setYear(2022);
    // static final로 선언하였지만 값이 바뀜.

    logger.info(INSANE_DATE.toLocaleString());
  }

  @GetMapping(value = "/test4")
  public void test4() {
//    INSANE_DATE2.set
    // set 메소드가 없음. 이렇게 외부에서 값을 설정할 수 없는 객체가 불변객체.
    // 불변객체는 새로운 값을 저장할 때마다 객체를 새로 생성해야 하므로 메모리 소비가 커짐.

    logger.info(INSANE_DATE2.toString());
  }

}
