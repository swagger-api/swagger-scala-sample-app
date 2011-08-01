package com.wordnik.swagger.sample.util

import javax.ws.rs.core.{HttpHeaders, UriInfo}
import collection.mutable.ListBuffer
import java.io.File
import java.util.ArrayList
import java.net.URLDecoder
import scala.collection.JavaConversions._
import com.wordnik.swagger.core.{Api, ApiAuthorizationFilter}
import com.wordnik.swagger.sample.util.Secure
import javax.ws.rs.Path
import com.sun.org.apache.xpath.internal.operations.Variable
import org.eclipse.jdt.internal.compiler.codegen.MethodNameAndTypeCache

/**
 * This is a sample authorization filter. This class initializes security rules at class and method level.
 *
 * The rules are maintained in simple map with key as path and a boolean value indicating given path is secure or
 * not. For method level security the key is combination of http method and path .
 *
 * If the resource or method is secure then it can only be viewed by users who login into the system.
 *
 * Login infomration is derived from thread local.
 *
 */
class ApiAuthorizationFilterImpl extends ApiAuthorizationFilter {

  var isFilterInitialized:Boolean = false
  var methodSecurityAnotations:Map[String, Boolean] =  Map[String, Boolean]()
  var classSecurityAnotations:Map[String, Boolean] =  Map[String, Boolean]()

  def authorize(apiPath: String, method:String, headers: HttpHeaders , uriInfo: UriInfo ): Boolean = {
     var mName = method.toUpperCase;
     if (isPathSecure(mName+":"+apiPath, false)){
        false
     }else {
        true
     }
  }

  def authorizeResource(apiPath: String, headers: HttpHeaders, uriInfo: UriInfo): Boolean = {
    if (isPathSecure(apiPath, true)){
       false
    }else {
       true
    }
  }

  private def isPathSecure(apiPath:String, isResource:Boolean):Boolean = {
    if (!isFilterInitialized.booleanValue()) initialize()
    if (isResource.booleanValue()){
      if(classSecurityAnotations.contains(apiPath)){
        classSecurityAnotations.get(apiPath).get
      }else{
        false
      }
    }else{
      if(methodSecurityAnotations.contains(apiPath)){
        methodSecurityAnotations.get(apiPath).get
      }else{
        false
      }
    }
  }

  private def initialize() = {

    //initialize classes
    classSecurityAnotations += "/user" -> false
    classSecurityAnotations += "/pet" -> false
    classSecurityAnotations += "/store" -> true

    //initialize method security
    methodSecurityAnotations += "GET:/pet/{petId}" -> false
    methodSecurityAnotations += "POST:/pet" -> true
    methodSecurityAnotations += "PUT:/pet" -> true
    methodSecurityAnotations += "GET:/pet/findByStatus" -> false
    methodSecurityAnotations += "GET:/pet/findByTags" -> false
    methodSecurityAnotations += "GET:/store/order/{orderId}" -> true
    methodSecurityAnotations += "DELETE:/store/order/{orderId}" -> true
    methodSecurityAnotations += "POST:/store/order" -> true
    methodSecurityAnotations += "POST:/user" -> false
    methodSecurityAnotations += "PUT:/user/{username}" -> true
    methodSecurityAnotations += "DELETE:/user/{username}" -> true
    methodSecurityAnotations += "GET:/user/{username}" -> false
    methodSecurityAnotations += "GET:/user/login" -> false
    methodSecurityAnotations += "GET:/user/logout" -> false

  }

}