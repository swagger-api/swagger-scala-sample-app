package com.wordnik.swagger.sample.data

import collection.mutable.ListBuffer
import scala.collection.JavaConversions._

/**
  * @author ayush
  * @since 6/23/11 1:08 PM
  *
  */
object PhotoApi {
  def getPhotos(userId: String): Array[Photo] = {
    val photos = ListBuffer.empty[Photo]

    photos += new Photo("http://farm3.static.flickr.com/2724/5859810266_2337bb8fa1_b.jpg", "OnTheRoad")
    photos += new Photo("http://farm2.static.flickr.com/1021/4724086902_fd226d05a8_b.jpg", "Chilling in the 1900's")

    photos.toArray
  }
}