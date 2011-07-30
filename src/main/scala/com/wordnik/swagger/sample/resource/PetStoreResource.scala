package com.wordnik.swagger.sample.resource

import javax.ws.rs.{Produces, Path}
import com.wordnik.swagger.core.{Help, Api}

/**
 * User: ramesh
 * Date: 7/29/11
 * Time: 5:23 PM
 */
//secure resource
trait PetStoreResource {

  //throw error if id is not found
  //get purchase order

  //purchase a pet

  //delete order
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