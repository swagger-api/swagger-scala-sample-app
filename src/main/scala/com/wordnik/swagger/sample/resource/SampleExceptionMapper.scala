package com.wordnik.swagger.sample.resource

import javax.ws.rs.ext.{ExceptionMapper, Provider}
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status
import com.wordnik.swagger.sample.exception.{ApiException, BadRequestException, NotFoundException}
import com.wordnik.swagger.sample.model.ApiResponse

/**
 * User: ramesh
 * Date: 8/1/11
 * Time: 3:27 PM
 */
@Provider
class SampleExceptionMapper extends ExceptionMapper[ApiException] {
    def toResponse(exception: ApiException): Response = {
        exception match {
            case e: NotFoundException => Response.status(Status.NOT_FOUND).entity(new ApiResponse(ApiResponse.ERROR, e.getMessage())).build
            case e: BadRequestException => Response.status(Status.BAD_REQUEST).entity(new ApiResponse(ApiResponse.ERROR, e.getMessage())).build
            case e: ApiException => Response.status(Status.BAD_REQUEST).entity(new ApiResponse(ApiResponse.ERROR, e.getMessage())).build
            case _ => {
                Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ApiResponse(ApiResponse.ERROR, "a system error occured")).build
            }
        }
    }
}