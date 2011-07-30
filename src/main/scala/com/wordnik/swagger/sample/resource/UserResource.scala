package com.wordnik.swagger.sample.resource

import javax.ws.rs.{Produces, Path}
import com.wordnik.swagger.core.{Help, Api}

/**
 * Sample user service. 
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:23 PM
 */
trait UserResource {

  //unsecure
  //create user

  //secure
  //update user

  //secure
  //delete user

  //unsecure
  //get user

  //unsecure
  //login user
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