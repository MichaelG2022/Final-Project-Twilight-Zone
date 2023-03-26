package cds.twilight.controller;

import java.util.List;
import javax.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import cds.twilight.entity.Lamp;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/*
 * Validated annotation enables Bean validations to be used.
 * 
 * RequestMapping annotation tells Spring Boot to map all HTTP requests of /lamps to this
 * class/interface. Spring Boot will then look for mapping to HTTP verbs, such as GetMapping at the
 * bottom of this class.
 * 
 * OpenAPIDefinition annotation provides the "chapter" title as well as information about the server
 * for Swagger to provide organized documentation.
 * 
 * Operation annotation starts a list of information for Swagger to use for documentation for the
 * method below, including a summary, a description, possible responses and response codes, and
 * parameters in the method.
 * 
 * ApiResponse annotation provides specific information to Swagger for each of the four possible
 * HTTP Response codes for this method. This includes the response code, a description of the code,
 * and the content type, in this case, application/json. The 200 code also provides the schema to
 * help Swagger define the data types in the response from the Controller.
 * 
 * Parameter annotation provides information about the method arguments.
 */
@Validated
@RequestMapping("/lamps")
@OpenAPIDefinition(info = @Info(title = "Twilight Lighting"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface TwilightLampController {

//@formatter:off
 @Operation(
     summary = "Returns a list of lamps",
     description = "Returns a list of lamps given an optional lamp wattage",
     responses = {
         @ApiResponse(
             responseCode = "200",
             description = "A list of lamps is returned", 
             content = @Content(
                 mediaType = "application/json", 
                 schema = @Schema(
                     implementation = Lamp.class))),
         @ApiResponse(
             responseCode = "400",
             description = "The request parameters are invalid", 
             content = @Content(
                 mediaType = "application/json")),
         @ApiResponse(
             responseCode = "404", 
             description = "No lamps were found", 
             content = @Content(
                 mediaType = "application/json")),
         @ApiResponse(
             responseCode = "500", 
             description = "An unplanned error occurred", 
             content = @Content(
                 mediaType = "application/json"))
     }, // end responses
     parameters = {
         @Parameter(
             name = "wattage", 
             allowEmptyValue = false,
             required = false,
             description = "The lamp wattage (i.e., '575')\nEnter '9999' to return all lamps"),
     } // end parameters
 ) // end Operation
 
 /*
  * GetMapping annotation tells Spring Boot that HTTP request GET is mapped to this method.
  * 
  * ResponseStatus annotation tells Spring Boot the response status to use if everything is
  * successful.
  */
 @GetMapping
 @ResponseStatus(code = HttpStatus.OK)
 List<Lamp> fetchLamps(
     @Positive     
     @RequestParam(required = false) int wattage
     ); // end fetchLamps
 // @formatter:on

} // end INTERFACE
