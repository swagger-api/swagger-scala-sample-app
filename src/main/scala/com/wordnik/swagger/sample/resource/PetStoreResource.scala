package com.wordnik.swagger.sample.resource

import com.wordnik.swagger.sample.util.Secure
import com.wordnik.swagger.core._
import javax.ws.rs.core.Response
import javax.ws.rs._
import com.wordnik.swagger.sample.model.Order

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:23 PM
 */
trait PetStoreResource {

  @GET
  @Path("/order/{orderId}")
  @ApiOperation(value = "Find purchase order by id", notes = "For valid response try integer ids with value < 1000. " +
    "Any thing above 1000 or non integers will generate API erros", responseClass = "com.wordnik.swagger.sample.model.Order")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Order not found")))
  @Secure
  def getOrderById(
      @ApiParam(value="ID of pet that need to be fetched",required=true)@PathParam("petId") userId: String) = {
      Response.ok.entity("").build
  }

  @POST
  @Path("/order")
  @ApiOperation(value = "Place an order for pet", responseClass = "com.wordnik.swagger.sample.model.Order")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid order")))
  @Secure
  def placeOrder(
      @ApiParam(value="order placed for purchasing the pet",required=true)order: Order) = {
      Response.ok.entity("").build
  }

  @DELETE
  @Path("/order/{orderId}")
  @ApiOperation(value = "Delete purchase order by id", notes = "For valid response try integer ids with value < 1000. " +
    "Any thing above 1000 or non integers will generate API erros")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Order not found")))
  @Secure
  def deleteOrder(
      @ApiParam(value="ID of the order that need to be deleted",required=true)@PathParam("orderId") orderId: String) = {
      Response.ok.entity("").build
  }
}

@Path("/store.json")
@Api("/store")
@Produces(Array("application/json"))
class PetStoreResourceJSON extends Help
  with PetStoreResource

@Path("/store.xml")
@Api("/store")
@Produces(Array("application/xml"))
class PetStoreResourceXML extends Help
  with PetStoreResource