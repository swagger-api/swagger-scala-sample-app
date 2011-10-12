# Swagger Sample App

## Overview
This is a project to build a stand-alone server which implements the Swagger spec.  You can find out 
more about both the spec and the framework at http://swagger.wordnik.com.  For more information 
about Wordnik's APIs, please visit http://developer.wordnik.com.  There is an online version of this
server at http://petstore.swagger.wordnik.com/api/resources.json

### Prerequisites
You need the following installed and available in your $PATH:

<li>- Java 1.6 or greater (http://java.oracle.com)

<li>- Apache ant 1.7 or greater (http://ant.apache.org/)

<li>- Scala 2.8.1 or greater (http://www.scala-lang.org)

<li>- Apachy ivy installed (from swagger-core)

### To build
To build the stand-alone server, run this task:
<pre>
ant dist
</pre>

This will resolve all required dependencies (~30MB) including the swagger-core library and store them in
your local ivy cache.  It will then build a stand-alone server in the "dist" folder which can be run by
shell script:

<pre>
cd dist
./bin/run-dev.sh
</pre>

This will start Jetty embedded on port 8002 with JDB enabled on port 8015.  If you have other services
using these ports you can change the HTTP port in the conf/jetty/jetty.xml file.  The JDB port can be
changed or disabled completely from the bin/run-dev.sh

### Testing the server
Once started, you can navigate to http://localhost:8002/api/resources.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool available in a separate project.  This lets you inspect the API using an 
intuitive UI.  You can pull this code from here:  https://github.com/wordnik/swagger-ui

You can then open the src/main/html/index.html file in any HTML5-enabled browser.  Open opening, enter the
URL of your server in the top-centered input box (default is http://localhost:8002/api).  Click the "Explore" 
button and you should see the resources available on the server.

### Applying an API key
The sample app has an implementation of the Swagger ApiAuthorizationFilter.  This restricts access to resources
based on api-key.  There are two keys defined in the sample app:

<li>- default-key</li>

<li>- special-key</li>

When no key is applied, the "default-key" is applied to all operations.  If the "special-key" is entered, a
number of other resources are shown in the UI, including sample CRUD operations.  Note this behavior is similar
to that on http://developer.wordnik.com/docs but the behavior is entirely up to the implementor.