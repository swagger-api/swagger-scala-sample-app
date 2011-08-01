package com.wordnik.swagger.sample.util

import java.io.IOException
import javax.servlet._
import http.{HttpServletRequest, HttpServletResponse}

/**
 * Filter that is used to set user session infomration in thread local for API authorization filter to access.
 *
 * This will also add user information to session if the call is for login and removes the user information for session
 * if the call is for logout
 *
 * User: ramesh
 * Date: 8/1/11
 * Time: 12:21 AM
 */

class SampleAppFilter  extends javax.servlet.Filter {

  val USER_SESSION_ATTRIBUTE = "username"

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) = {

    var session = request.asInstanceOf[HttpServletRequest].getSession()

    //if login request add user info to the session
    var path = request.asInstanceOf[HttpServletRequest].getPathInfo()
    if (null !=path && path.contains("login")){
      val username = request.getParameter("username")
      session.setAttribute(USER_SESSION_ATTRIBUTE, username)
    }else if (null !=path && path.contains("logout")){
      session.removeAttribute(USER_SESSION_ATTRIBUTE)
      var context = new ThreadContext
      ThreadContextLocator.setThreadContext(context)
    }else{
      if(null != session.getAttribute(USER_SESSION_ATTRIBUTE)){
          var context = new ThreadContext
          context.setUsername(session.getAttribute(USER_SESSION_ATTRIBUTE).toString)
          ThreadContextLocator.setThreadContext(context)
      }else{
          var context = new ThreadContext
          ThreadContextLocator.setThreadContext(context)
      }
    }
    chain.doFilter(request, response);
  }

  override def destroy() = {}

  @throws(classOf[ServletException])
  override def init(filterConfig: FilterConfig) {}

  
}