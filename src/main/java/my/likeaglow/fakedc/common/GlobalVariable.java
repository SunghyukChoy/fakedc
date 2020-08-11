package my.likeaglow.fakedc.common;

/**
 * 글로벌 변수 클래스
 * 
 * @author sunghyuk choi
 *
 */
public class GlobalVariable {

  private GlobalVariable() {
    // 외부에서 생성자로 객체를 생성하지 못하도록 private로 선언함.
    // static 변수들을 사용하기 위한 클래스이고 클래스명으로 static 변수들에 접근 가능하므로 객체 생성할 필요 없음.
  }

  // Session KEY
  public static final String LOGINMEMBERDTO_SESSION_KEY = "loginMemberDTO";

  // Global A ...

  // ETC...
}
