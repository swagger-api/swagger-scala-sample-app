package com.wordnik.swagr.sandbox.util

import com.wordnik.swagger.core.ApiAuthorizationFilter
import javax.ws.rs.core.{HttpHeaders, UriInfo}

class ApiAuthorizationFilterImpl extends ApiAuthorizationFilter {


  def authorize(apiPath: String, headers: HttpHeaders , uriInfo: UriInfo ): Boolean = {
     if(apiPath.equals("/apiList.xml/allApis")) false else true;
  }
}