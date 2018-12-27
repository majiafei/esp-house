package com.ruanmou.web.espweb.common;

import com.ruanmou.web.espweb.vo.UserVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 会话保存类
 */
public class UserContext {

  public static final String LONGED_USER = "loginUser";
  private static HttpSession session;

  static {
    ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    session = attrs.getRequest().getSession();
  }

  
  public static void setUser(UserVO user){
       session.setAttribute(LONGED_USER, user);
  }
  
  public static void remove() {
    session.removeAttribute(LONGED_USER);
  }
  
  public static UserVO getUser() {
    return (UserVO) session.getAttribute(LONGED_USER);
  }

}
