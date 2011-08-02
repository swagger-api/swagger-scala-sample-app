package com.wordnik.swagger.sample.util

import javax.ws.rs.core.{HttpHeaders, UriInfo}
import collection.mutable.ListBuffer
import java.io.File
import java.util.ArrayList
import java.net.URLDecoder
import scala.collection.JavaConversions._
import com.wordnik.swagger.core.{Api, ApiAuthorizationFilter}
import javax.ws.rs.Path
import com.sun.org.apache.xpath.internal.operations.Variable
import org.eclipse.jdt.internal.compiler.codegen.MethodNameAndTypeCache

/**
 * This is a sample authorization filter. This class initializes security rules at class and method level.
 *
 * The rules are maintained in simple map with key as path and a boolean value indicating given path is secure or
 * not. For method level security the key is combination of http method and path .
 *
 * If the resource or method is secure then it can only be viewed using a secured api key
 *
 * Note: Objective of this class is not to provide fully functional implementation of authorization filter. This is
 * only a sample demonstration how API authorization filter works. 
 *
 */
class ApiAuthorizationFilterImpl extends ApiAuthorizationFilter {

  var isFilterInitialized:Boolean = false
  var methodSecurityAnotations:Map[String, Boolean] =  Map[String, Boolean]()
  var classSecurityAnotations:Map[String, Boolean] =  Map[String, Boolean]()
  var securekeyId = "sample-app-secure-key"
  var unsecurekeyId = "sample-app-un-secure-key"

  def authorize(apiPath: String, method:String, headers: HttpHeaders , uriInfo: UriInfo ): Boolean = {
     var apiKey = uriInfo.getQueryParameters.getFirst("api_key")
     val mName = method.toUpperCase;
     if (isPathSecure(mName+":"+apiPath, false)){
       if (apiKey == securekeyId){
        return true
       }else{
         return false
       }
     }
     true
  }

  def authorizeResource(apiPath: String, headers: HttpHeaders, uriInfo: UriInfo): Boolean = {
    var apiKey = uriInfo.getQueryParameters.getFirst("api_key")
    if (isPathSecure(apiPath, true)){
      if (apiKey == securekeyId){
       return true
      }else{
        return false
      }
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
    classSecurityAnotations += "/user.{format}" -> false
    classSecurityAnotations += "/pet.{format}" -> false
    classSecurityAnotations += "/store.{format}" -> true

    //initialize method security
    methodSecurityAnotations += "GET:/pet.{format}/{petId}" -> false
    methodSecurityAnotations += "POST:/pet.{format}" -> true
    methodSecurityAnotations += "PUT:/pet.{format}" -> true
    methodSecurityAnotations += "GET:/pet.{format}/findByStatus" -> false
    methodSecurityAnotations += "GET:/pet.{format}/findByTags" -> false
    methodSecurityAnotations += "GET:/store.{format}/order/{orderId}" -> true
    methodSecurityAnotations += "DELETE:/store.{format}/order/{orderId}" -> true
    methodSecurityAnotations += "POST:/store.{format}/order" -> true
    methodSecurityAnotations += "POST:/user.{format}" -> false
    methodSecurityAnotations += "PUT:/user.{format}/{username}" -> true
    methodSecurityAnotations += "DELETE:/user.{format}/{username}" -> true
    methodSecurityAnotations += "GET:/user.{format}/{username}" -> false
    methodSecurityAnotations += "GET:/user.{format}/login" -> false
    methodSecurityAnotations += "GET:/user.{format}/logout" -> false

  }

}