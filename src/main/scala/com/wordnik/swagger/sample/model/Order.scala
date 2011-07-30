package com.wordnik.swagger.sample.model

import java.util.Date
import javax.xml.bind.annotation.XmlRootElement._
import javax.xml.bind.annotation.{XmlElement, XmlRootElement}
import javax.xml.bind.annotation.XmlElement._
import com.wordnik.swagger.core.ApiProperty
import com.wordnik.swagger.core.ApiProperty._

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 10:30 PM
 */
@XmlRootElement(name = "order")
class Order {

  private var id:Long = 0

  private var petId:Long = 0

  private var quantity:Int = 0

  private var shipDate:Date = null

  private var status:String = null

  @XmlElement(name="id")
  def getId():Long = {
    id
  }

  def setId(id:Long):Unit = {
    this.id = id
  }

  @XmlElement(name="petId")
  def getPetId():Long = {
    petId
  }

  def setPetId(petId:Long):Unit = {
    this.petId = petId
  }

  @XmlElement(name="quantity")
  def getQuantity():Int = {
    quantity
  }

  def setQuantity(quantity:Int):Unit = {
    this.quantity = quantity
  }

  @XmlElement(name="status")
  @ApiProperty(value = "Order Status", allowableValues = "placed, approved, delivered")
  def getStatus():String = {
    status
  }

  def setStatus(status:String):Unit = {
    this.status = status
  }

  @XmlElement(name="shipDate")
  def getShipDate():Date = {
    shipDate
  }

  def setShipDate(shipDate:Date):Unit = {
    this.shipDate = shipDate
  }
}