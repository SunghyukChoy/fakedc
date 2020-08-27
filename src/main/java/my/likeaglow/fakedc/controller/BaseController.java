package my.likeaglow.fakedc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import my.likeaglow.fakedc.common.GlobalVariable;
import my.likeaglow.fakedc.model.LoginMemberDTO;

public abstract class BaseController {

  // HttpSession 객체를 생성하는 메서드.
  public HttpSession getHttpSession() {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return attr.getRequest().getSession(true);
  }

  public LoginMemberDTO getLoginMember() {
    HttpSession session = getHttpSession();
    LoginMemberDTO loginMember = (LoginMemberDTO) session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY);
    return loginMember;
  }

  public void setLoginMember(LoginMemberDTO loginMemberDTO) {
    getHttpSession().setAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY, loginMemberDTO);
  }

  public boolean hasLoginMember() {
    return getLoginMember() != null;
  }

}
