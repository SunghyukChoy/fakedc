package my.likeaglow.fakedc;

import java.text.DateFormat;
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
  MemberRepository memberRepository;

  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model, HttpSession session) {
    logger.info("Welcome home! The client locale is {}.", locale);

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);
    model.addAttribute("member", session.getAttribute("member"));

    return "home";
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
    AuthCheckDTO auth = new AuthCheckDTO("LIKEAGLOW", "1234", -1, null);

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

}
