package com.wordnik.swagger.sample.resource

import com.wordnik.swagger.core.ApiOperation._
import com.wordnik.swagger.core.ApiError._
import com.wordnik.swagger.core._
import com.wordnik.swagger.core.ApiParam._
import javax.ws.rs.core.Response
import javax.ws.rs._
import com.wordnik.swagger.sample.model.User

/**
 * Sample user service. 
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:23 PM
 */
trait UserResource {

  @POST
  @ApiOperation(value = "Create user", notes = "This can only be done the logged in user")
  def createUser(
      @ApiParam(value="Created user object",required=true)user: User) = {
      Response.ok.entity("").build
  }

  @PUT
  @Path("/{username}")
  @ApiOperation(value = "Updated user", notes = "This can only be done the logged in user")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid username supplied"),
    new ApiError(code = 404, reason = "User not found")))
  def updateUser(
      @ApiParam(value="name that need to be deleted",required=true)@PathParam("username") username: String,
      @ApiParam(value="Updated user object",required=true)user: User) = {
      Response.ok.entity("").build
  }

  @DELETE
  @Path("/{username}")
  @ApiOperation(value = "Delete user", notes = "This can only be done the logged in user")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid username supplied"),
    new ApiError(code = 404, reason = "User not found")))
  def deleteUser(
      @ApiParam(value="name that need to be deleted",required=true)@PathParam("username") username: String) = {
      Response.ok.entity("").build
  }

  @GET
  @Path("/{username}")
  @ApiOperation(value = "Get user by user name", responseClass = "com.wordnik.swagger.sample.model.User")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid username supplied"),
    new ApiError(code = 404, reason = "User not found")))
  def getUserByName(
      @ApiParam(value="name that need to be fetched",required=true)@PathParam("username") username: String) = {
      Response.ok.entity("").build
  }

  @GET
  @Path("/login")
  @ApiOperation(value = "Login user into the system", responseClass = "String")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid username and password combination")))
  def loginUser(
      @ApiParam(value="user name for login",required=true)@QueryParam("username") username: String,
       @ApiParam(value="password for login in clear text",required=true)@QueryParam("password") password: String) = {
      Response.ok.entity("").build
  }
}


@Path("/user.json")
@Api("/user")
@Produces(Array("application/json"))
class UserResourceJSON extends Help
  with UserResource

@Path("/user.xml")
@Api("/user")
@Produces(Array("application/xml"))
class UserResourceXML extends Help
with UserResource