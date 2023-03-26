package cds.twilight.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import cds.twilight.entity.Order;
import cds.twilight.entity.OrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

/*
 * Validated annotation enables Bean validations to be used.
 * 
 * RequestMapping annotation tells Spring Boot to map all HTTP requests of /orders to this
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
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Twilight Lighting"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface TwilightOrderController {

//@formatter:off
 @Operation(
     summary = "Create an order for a light",
     description = "Returns the created order",
     responses = {
         @ApiResponse(
             responseCode = "201",
             description = "The created order is returned", 
             content = @Content(
                 mediaType = "application/json", 
                 schema = @Schema(
                     implementation = Order.class))),
         @ApiResponse(
             responseCode = "400",
             description = "The request parameters are invalid", 
             content = @Content(
                 mediaType = "application/json")),
         @ApiResponse(
             responseCode = "404", 
             description = "A light component was not found with the input criteria", 
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
             name = "orderRequest", 
             required = true,
             description = "The order as JSON")
     } // end parameters
 ) // end Operation
 // @formatter:on

  /*
   * PostMapping annotation tells Spring Boot that HTTP request POST is mapped to this method.
   * 
   * ResponseStatus annotation tells Spring Boot the response status to use if everything is
   * successful.
   * 
   * Valid annotation enables validation cascading.
   * 
   * RequestBody annotation means the method parameter should be bound to the body of the web
   * request. 
   */
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createLightOrder(@Valid @NotNull @RequestBody OrderRequest orderRequest);

} // end INTERFACE
