package com.wordnik.swagger.sample.util

/**
 * User: ramesh
 * Date: 8/1/11
 * Time: 9:55 AM
 */

object ThreadContextLocator {

  val threadContextLocator: ThreadLocal[ThreadContext] = new ThreadLocal[ThreadContext]

  def getThreadContext: ThreadContext = {
    return threadContextLocator.get
  }

  def setThreadContext(threadContext: ThreadContext): Unit = {
    if (threadContext == null) {
      threadContextLocator.remove
    }
    threadContextLocator.set(threadContext)
  }
}