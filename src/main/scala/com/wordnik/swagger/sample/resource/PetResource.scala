package com.wordnik.swagger.sample.resource

import com.wordnik.swagger.core.ApiOperation._
import com.wordnik.swagger.core.ApiError._
import com.wordnik.swagger.core._
import com.wordnik.swagger.core.ApiParam._
import javax.ws.rs.core.Response
import javax.ws.rs._
import org.junit.runner.manipulation.NoTestsRemainException
import com.wordnik.swagger.sample.data.{PetData}
import util.RestResourceUtil
import java.lang.Exception
import com.wordnik.swagger.sample.model.{ApiException, Pet}
import com.sun.jersey.spi.resource.Singleton

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:27 PM
 */
trait PetResource extends RestResourceUtil {
  var petData = new PetData

  @GET
  @Path("/{petId}")
  @ApiOperation(value = "Find pet by id", notes = "For valid response try integer ids with value < 10. " +
    "Any thing above 1000 or non integers will generate API erros", responseClass = "com.wordnik.swagger.sample.model.Pet")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Pet not found")))
  def getPetById(
      @ApiParam(value="ID of pet that need to be fetched",required=true)@PathParam("petId") petId: String) = {
      var pet = petData.getPetbyId(getLong(0, 100000, 0, petId))
      if (null != pet){
        Response.ok.entity(pet).build
      }else{
        throw new ApiException(404, "Pet not found")
      }
  }

  @POST
  @ApiOperation(value = "Add pet to the store")
  @ApiErrors(Array(
    new ApiError(code = 405, reason = "Validation exception")))
  def addPet(
      @ApiParam(value="Pet object that needs to be added to the store",required=true) pet: Pet) = {
      petData.addPet(pet)
      Response.ok.entity("SUCCESS").build
  }

  @PUT
  @ApiOperation(value = "Update pet in the store")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Pet not found"),
    new ApiError(code = 405, reason = "Validation exception")))
  def updatePet(
      @ApiParam(value="Pet object that needs to be added to the store",required=true) pet: Pet) = {
      petData.addPet(pet)
      Response.ok.entity("SUCCESS").build
  }

  @GET
  @Path("/findByStatus")
  @ApiOperation(value = "Finds pets by status",
    notes = "Muliple status values can be provided with comma seperated strings. Default value is available",
    responseClass = "com.wordnik.swagger.sample.model.Pet", mutiValueResponse = true)
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid status value")))
  def findPetsByStatus(
      @ApiParam(value="Status values that needs to be considered for filter",required=true, defaultValue = "available",
      allowableValues = "available,pending,sold", allowMultiple = true)@QueryParam("status") status: String) = {
      var results = petData.findPetByStatus(status)
      Response.ok.entity(results).build
  }

  @GET
  @Path("/findByTags")
  @ApiOperation(value = "Finds pets by tags",
    notes = "Muliple tagsvalues can be provided with comma seperated strings. ",
    responseClass = "com.wordnik.swagger.sample.model.Pet", mutiValueResponse = true)
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid tag value")))
  def findPetsByTags(
      @ApiParam(value="Tags that needs to be considered for filter",required=true,
        allowMultiple = true)@QueryParam("tags") tags: String) = {
    var results = petData.findPetByTags(tags)
    Response.ok.entity(results).build
  }
}

@Path("/pet.json")
@Api("/pet")
@Singleton
@Produces(Array("application/json"))
class PetResourceJSON extends Help
  with PetResource

@Path("/pet.xml")
@Api("/pet")
@Singleton
@Produces(Array("application/xml"))
class PetResourceXML extends Help
  with PetResource