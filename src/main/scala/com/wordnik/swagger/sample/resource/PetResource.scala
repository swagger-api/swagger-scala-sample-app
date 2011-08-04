/**
 *  Copyright 2011 Wordnik, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wordnik.swagger.sample.resource

import com.wordnik.swagger.core._
import com.wordnik.swagger.core.ApiParam._
import com.wordnik.swagger.core.ApiOperation._
import com.wordnik.swagger.core.util.RestResourceUtil
import com.wordnik.swagger.sample.data.{ PetData }
import com.wordnik.swagger.sample.model.{ Pet }
import com.wordnik.swagger.core.ApiError._
import com.wordnik.swagger.sample.exception.NotFoundException

import com.sun.jersey.spi.resource.Singleton

import javax.ws.rs.core.Response
import javax.ws.rs._
import java.lang.Exception

trait PetResource extends RestResourceUtil {
  var petData = new PetData

  @GET
  @Path("/{petId}")
  @ApiOperation(value = "Find Pet by id", notes = "Returns a pet when id < 10. " +
    "Id > 1000 or non integers will simulate API error conditions", responseClass = "com.wordnik.swagger.sample.model.Pet")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Pet not found")))
  def getPetById(
    @ApiParam(value = "ID of pet that need to be fetched", required = true)@PathParam("petId") petId: String) = {
    var pet = petData.getPetbyId(getLong(0, 100000, 0, petId))
    if (null != pet) {
      Response.ok.entity(pet).build
    } else {
      throw new NotFoundException(404, "Pet not found")
    }
  }

  @POST
  @ApiOperation(value = "Add a new Pet to the store")
  @ApiErrors(Array(
    new ApiError(code = 405, reason = "Invalid input")))
  def addPet(
    @ApiParam(value = "Pet object that needs to be added to the store", required = true) pet: Pet) = {
    petData.addPet(pet)
    Response.ok.entity("SUCCESS").build
  }

  @PUT
  @ApiOperation(value = "Update an existing Pet")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 404, reason = "Pet not found"),
    new ApiError(code = 405, reason = "Validation exception")))
  def updatePet(
    @ApiParam(value = "Pet object that needs to be added to the store", required = true) pet: Pet) = {
    petData.addPet(pet)
    Response.ok.entity("SUCCESS").build
  }

  @GET
  @Path("/findByStatus")
  @ApiOperation(value = "Finds Pets by status",
    notes = "Multiple status values can be provided with comma seperated strings",
    responseClass = "com.wordnik.swagger.sample.model.Pet", mutiValueResponse = true)
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid status value")))
  def findPetsByStatus(
    @ApiParam(value = "Status values that needs to be considered for filter", required = true, defaultValue = "available",
      allowableValues = "available,pending,sold", allowMultiple = true)@QueryParam("status") status: String) = {
    var results = petData.findPetByStatus(status)
    Response.ok.entity(results).build
  }

  @GET
  @Path("/findByTags")
  @ApiOperation(value = "Finds Pets by tags",
    notes = "Muliple tags can be provided with comma seperated strings",
    responseClass = "com.wordnik.swagger.sample.model.Pet", mutiValueResponse = true)
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid tag value")))
  @Deprecated
  def findPetsByTags(
    @ApiParam(value = "Tags to filter by", required = true,
      allowMultiple = true)@QueryParam("tags") tags: String) = {
    var results = petData.findPetByTags(tags)
    Response.ok.entity(results).build
  }
}

@Path("/pet.json")
@Api(value = "/pet", description = "Operations about Pets")
@Singleton
@Produces(Array("application/json"))
class PetResourceJSON extends Help
  with PetResource

@Path("/pet.xml")
@Api(value = "/pet", description = "Operations about Pets")
@Singleton
@Produces(Array("application/xml"))
class PetResourceXML extends Help
  with PetResource