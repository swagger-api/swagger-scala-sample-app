package com.wordnik.swagger.sample.resource

import javax.ws.rs.{Produces, Path}
import com.wordnik.swagger.core.{Help, Api}

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:27 PM
 */

trait PetResource {

  //find pet by id

  //secure
  //update pet

  //set default as open //query nd path parameters 
  //get pets by status with more than one possible status

  //find pets by tags with multiple values as inputs
  
}

@Path("/pet.json")
@Api("/pet")
@Produces(Array("application/json"))
class PetResourceJSON extends Help
  with PetResource

@Path("/pet.xml")
@Api("/pet")
@Produces(Array("application/xml"))
class PetResourceXML extends Help
with PetResource