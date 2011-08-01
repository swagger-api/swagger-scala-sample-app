package com.wordnik.swagger.sample.resource

import com.wordnik.swagger.core._
import javax.ws.rs.core.Response
import javax.ws.rs._
import com.wordnik.swagger.sample.model.Order
import com.wordnik.swagger.sample.data.StoreData
import util.RestResourceUtil
import com.sun.jersey.spi.resource.Singleton

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:23 PM
 */
trait PetStoreResource extends RestResourceUtil {

  var storeData = new StoreData

  @GET
  @Path("/order/{orderId}")
  @ApiOperation(value = "Find purchase order by id", notes = "For valid response try integer ids with value < 1000. " +
    "Any thing above 1000 or non integers will generate API erros", responseClass = "com.wordnik.swagger.sample.model.Order")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Order not found")))
  def getOrderById(
      @ApiParam(value="ID of pet that need to be fetched",required=true)@PathParam("orderId") orderId: String) = {
      var order = storeData.findOrderById(getLong(0,10000, 0, orderId))
      Response.ok.entity(order).build
  }

  @POST
  @Path("/order")
  @ApiOperation(value = "Place an order for pet", responseClass = "com.wordnik.swagger.sample.model.Order")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid order")))
  def placeOrder(
      @ApiParam(value="order placed for purchasing the pet",required=true)order: Order) = {
      storeData.placeOrder(order)
      Response.ok.entity("").build
  }

  @DELETE
  @Path("/order/{orderId}")
  @ApiOperation(value = "Delete purchase order by id", notes = "For valid response try integer ids with value < 1000. " +
    "Any thing above 1000 or non integers will generate API erros")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Order not found")))
  def deleteOrder(
      @ApiParam(value="ID of the order that need to be deleted",required=true)@PathParam("orderId") orderId: String) = {
      storeData.deleteOrder(getLong(0, 10000, 0, orderId))
      Response.ok.entity("").build
  }
}

@Path("/store.json")
@Singleton
@Api("/store")
@Produces(Array("application/json"))
class PetStoreResourceJSON extends Help
  with PetStoreResource

@Path("/store.xml")
@Singleton
@Api("/store")
@Produces(Array("application/xml"))
class PetStoreResourceXML extends Help
  with PetStoreResource