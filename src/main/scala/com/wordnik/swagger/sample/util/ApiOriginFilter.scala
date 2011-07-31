package com.wordnik.swagr.sandbox.util

import java.io.IOException

import javax.servlet._
import javax.servlet.http.HttpServletResponse

class ApiOriginFilter extends javax.servlet.Filter {
  
  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) = {
    val res = response.asInstanceOf[HttpServletResponse]
    res.addHeader("Access-Control-Allow-Origin:", "*");
    chain.doFilter(request, response);
  }

  override def destroy() = {}

  @throws(classOf[ServletException])
  override def init(filterConfig: FilterConfig) {}
}