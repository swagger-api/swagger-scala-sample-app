package com.wordnik.swagger.sample.model

import java.util.List
import java.util.ArrayList
import javax.xml.bind.annotation.XmlRootElement._
import javax.xml.bind.annotation.{XmlElement, XmlRootElement}
import com.wordnik.swagger.core.ApiProperty
import com.wordnik.swagger.core.ApiProperty._

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:15 PM
 */
@XmlRootElement(name = "pet")
class Pet {

  private var id:Long = 0
  
  private var category:Category = null

  private var name:String = null

  private var photoUrls:List[String] = new ArrayList[String]()

  private var tags:List[Tag] = new ArrayList[Tag]()

  private var status:String = null

  @XmlElement(name="id")
  def getId():Long = {
    id
  }

  def setId(id:Long):Unit = {
    this.id = id
  }
  
  @XmlElement(name="category")
  def getCategory():Category = {
    category
  }

  def setCategory(category:Category):Unit = {
    this.category = category
  }

  @XmlElement(name="name")
  def getName():String = {
    name
  }

  def setName(name:String):Unit = {
    this.name = name
  }

  @XmlElement(name="photoUrls")
  def getPhotoUrls():List[String] = {
    photoUrls
  }

  def setPhotoUrls(photoUrls:List[String]):Unit = {
    this.photoUrls = photoUrls
  }

  @XmlElement(name="tags")
  def getTags():List[Tag] = {
    tags
  }

  def setTags(tags:List[Tag]):Unit = {
    this.tags = tags
  }

  @XmlElement(name="status")
  @ApiProperty(value = "pet status in the store", allowableValues = "available,pending,sold")
  def getStatus():String = {
    status
  }

  def setStatus(status:String):Unit = {
    this.status = status
  }
}