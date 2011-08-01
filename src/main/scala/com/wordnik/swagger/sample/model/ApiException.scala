package com.wordnik.swagger.sample.model

import java.lang.Exception

/**
 * User: ramesh
 * Date: 8/1/11
 * Time: 11:04 AM
 */

class ApiException(code:Int, msg:String) extends Exception(msg) {

  //def this(e:Exception) = this(e.getMessage)
}

