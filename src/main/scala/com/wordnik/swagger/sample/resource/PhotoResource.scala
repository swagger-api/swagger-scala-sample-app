package com.wordnik.swagger.sample.resource

import javax.ws.rs._
import core.Response
import com.wordnik.swagger.core._
import com.wordnik.swagger.sample.data.PhotoApi
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Occurs
import scala.Array

/**
  * @author ayush
  * @since 6/23/11 1:04 PM
  *
  */
trait PhotoResource {

  @GET
  @Path("/{userId}")
  @ApiOperation(value = "Fetches photos by UserId", responseClass = "com.wordnik.swagger.sample.data.Photo", mutiValueResponse = true, tags = "DM,PD")
  @ApiErrors(Array(
    new ApiError(code = 400, reason = "Invalid ID supplied"),
    new ApiError(code = 403, reason = "Not Authorized to access User"),
    new ApiError(code = 404, reason = "User not found")))
  def getPhotosById(
      @ApiParam(value="ID of User whose photos need to be fetched",required=true, allowMultiple=false,
        allowableValues="allowedId1,allowedId2,allowedId3")@PathParam("userId") userId: String) = {
      Response.ok.entity(PhotoApi.getPhotos(userId)).build
  }
}

@Path("/photo.json")
@Api("/photo")
@Produces(Array("application/json"))
class PhotoResourceJSON extends Help
  with PhotoResource

@Path("/photo.xml")
@Api("/photo")
@Produces(Array("application/xml"))
class PhotoResourceXML extends Help
with PhotoResource
