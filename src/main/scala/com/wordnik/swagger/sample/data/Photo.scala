package com.wordnik.swagger.sample.data

import javax.xml.bind.annotation.{XmlElement, XmlRootElement}
import javax.xml.bind.annotation.XmlElement._
import org.codehaus.jackson.annotate.JsonProperty
import org.codehaus.jackson.annotate.JsonProperty._
import com.wordnik.swagger.core.ApiProperty

/**
  * @author ayush
  * @since 6/23/11 1:03 PM
  *
  */

@XmlRootElement(name = "photo")
class Photo(u: String, t: String) {
  private var url: String = u
  private var title: String = t

  def this() = this(null, null)

	@XmlElement(name="url")
  @ApiProperty(value = "Url for photo", allowableValues = "a,b,c", notes = "Sample note for a url", access = "restricted")
	def getUrl():String = {
		url
	}

	def setUrl(url:String) {
		this.url = url
	}

	@XmlElement(name="title")
	def getTitle():String = {
		title
	}

	def setTitle(title:String) {
		this.title = title
	}
}