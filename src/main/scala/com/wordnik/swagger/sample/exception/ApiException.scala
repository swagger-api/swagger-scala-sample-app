package com.wordnik.swagger.sample.exception

import java.lang.Exception

/**
 * User: ramesh
 * Date: 8/1/11
 * Time: 3:29 PM
 */

class ApiException(code:Int, msg:String) extends Exception(msg:String) {

}

class BadRequestException(code:Int, msg:String) extends ApiException(code:Int, msg:String) {

}

class NotFoundException(code:Int, msg:String) extends ApiException(code:Int, msg:String) {

} 
