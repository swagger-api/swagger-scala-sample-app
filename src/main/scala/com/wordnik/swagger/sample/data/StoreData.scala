package com.wordnik.swagger.sample.data

import collection.mutable.ListBuffer
import com.wordnik.swagger.sample.model.{Order}
import java.util.Date

/**
 * Provides test data for store resource
 *
 * User: ramesh
 * Date: 8/1/11
 * Time: 10:10 AM
 */

class StoreData {

  val orders:ListBuffer[Order] = new ListBuffer[Order]()

  {
    orders += createOrder (1, 1, 2, new Date(), "placed")
    orders += createOrder (2, 1, 2, new Date(), "delivered")
    orders += createOrder (3, 2, 2, new Date(), "placed")
    orders += createOrder (4, 2, 2, new Date(), "delivered")
    orders += createOrder (5, 3, 2, new Date(), "placed")
  }

  def findOrderById(orderId:Long):Order = {
    for (order <- orders){
      if (order.getId() == orderId){
        return order
      }
    }
    null
  }

  def placeOrder(order:Order):Unit = {
    for (currentOrder <- orders){
      if (currentOrder.getId() == order.getId()){
        orders -= order
      }
    }
    orders += order
  }

  def deleteOrder(orderId:Long):Unit = {
    for (order <- orders){
      if (order.getId() == orderId){
        orders -= order
      }
    }
  }

  private def createOrder(id:Long, petId:Long, quantity:Int, shipDate:Date, status:String):Order = {
    val order = new Order()
    order.setId(id)
    order.setPetId(petId)
    order.setQuantity(quantity)
    order.setShipDate(shipDate)
    order.setStatus(status)
    order
  }
}