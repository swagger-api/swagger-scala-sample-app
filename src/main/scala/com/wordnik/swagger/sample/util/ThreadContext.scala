package com.wordnik.swagger.sample.util

/**
 * Used to provide access to current logged in user
 *
 * User: ramesh
 * Date: 8/1/11
 * Time: 9:47 AM
 */

class ThreadContext {

  var username:String = null;

  def getUsername():String = {
    username
  }

  def setUsername(username:String):Unit = {
    this.username = username
  }
}