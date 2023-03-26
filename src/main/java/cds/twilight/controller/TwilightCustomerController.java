package cds.twilight.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import cds.Constants;
import cds.twilight.entity.Customer;
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
 * RequestMapping annotation tells Spring Boot to map all HTTP requests of /customers to this
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
@RequestMapping("/customers")
@OpenAPIDefinition(info = @Info(title = "Twilight Lighting"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface TwilightCustomerController {

//@formatter:off
 @Operation(
     summary = "Returns a list of customers",
     description = "Returns a list of customers given a last name",
     responses = {
         @ApiResponse(
             responseCode = "200",
             description = "A list of customers is returned", 
             content = @Content(
                 mediaType = "application/json", 
                 schema = @Schema(
                     implementation = Customer.class))),
         @ApiResponse(
             responseCode = "400",
             description = "The request parameters are invalid", 
             content = @Content(
                 mediaType = "application/json")),
         @ApiResponse(
             responseCode = "404", 
             description = "No customers were found", 
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
             name = "lastName", 
             allowEmptyValue = false,
             required = false,
             description = "The last name (i.e., 'Young')\nEnter 'allall' to return all customers")
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
 List<Customer> fetchCustomers(
     @Length(max = Constants.TRIM_MAX_LENGTH)
     @Pattern(regexp = "[\\w\\s]*")
     @RequestParam(required = false) String lastName);   
 // @formatter:on

//@formatter:off
@Operation(
    summary = "Creates a new customer",
    description = "Creates a new customer given a first name, last name, and phone number",
    responses = {
        @ApiResponse(
            responseCode = "201",
            description = "A new customer is created", 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(
                    implementation = Customer.class))),
        @ApiResponse(
            responseCode = "400",
            description = "The request parameters are invalid", 
            content = @Content(
                mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No customers were created", 
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
            name = "customerId", 
            allowEmptyValue = false,
            required = false,
            description = "Enter your desired nickname"),
        @Parameter(
            name = "firstName", 
            allowEmptyValue = false,
            required = false,
            description = "The first name (i.e., 'Sue')"),
        @Parameter(
            name = "lastName", 
            allowEmptyValue = false,
            required = false,
            description = "The last name (i.e., 'Young')"),
        @Parameter(
            name = "phone", 
            allowEmptyValue = false,
            required = false,
            description = "The phone number (i.e., '222.333.5555")
    } // end parameters
) // end Operation

/*
 * PostMapping annotation tells Spring Boot that HTTP request POST is mapped to this method.
 * 
 * ResponseStatus annotation tells Spring Boot the response status to use if everything is
 * successful.
 */ 
 @PostMapping
 @ResponseStatus(code = HttpStatus.CREATED)
 Optional<Customer> createCustomer(
     @Length(max = 50)
     @Pattern(regexp = "[\\w\\s]*")
     @RequestParam(required = false) String customerId,
     @Length(max = 25)
     @Pattern(regexp = "[\\w\\s]*")
     @RequestParam(required = false) String firstName,
     @Length(max = 25)
     @Pattern(regexp = "[\\w\\s]*")
     @RequestParam(required = false) String lastName,
     @Length(max = 20)
     @Pattern(regexp = "[0-9.\\s]*")
     @RequestParam(required = false) String phone
     ); // end createCustomer
// @formatter:on

//@formatter:off
@Operation(
   summary = "Update a customer",
   description = "Update a customer's information",
   responses = {
       @ApiResponse(
           responseCode = "200",
           description = "A customer is updated", 
           content = @Content(
               mediaType = "application/json", 
               schema = @Schema(
                   implementation = Customer.class))),
       @ApiResponse(
           responseCode = "400",
           description = "The request parameters are invalid", 
           content = @Content(
               mediaType = "application/json")),
       @ApiResponse(
           responseCode = "404", 
           description = "No customers were found to be updated", 
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
           name = "customerId", 
           allowEmptyValue = false,
           required = false,
           description = "Enter the nickname you selected when you first signed up"),
       @Parameter(
           name = "firstName", 
           allowEmptyValue = false,
           required = false,
           description = "The first name (i.e., 'Sue')"),
       @Parameter(
           name = "newFirstName", 
           allowEmptyValue = false,
           required = false,
           description = "The new first name or the old one"),
       @Parameter(
           name = "lastName", 
           allowEmptyValue = false,
           required = false,
           description = "The last name (i.e., 'Young')"),
       @Parameter(
           name = "newLlastName", 
           allowEmptyValue = false,
           required = false,
           description = "The new last name or the old one"),
       @Parameter(
           name = "phone", 
           allowEmptyValue = false,
           required = false,
           description = "The phone number (i.e., '222.333.5555"),
       @Parameter(
           name = "newPhone", 
           allowEmptyValue = false,
           required = false,
           description = "The new phone number\n or the old one")
   } // end parameters
) // end Operation

/*
 * @PutMapping annotation tells Spring Boot that HTTP request PUT is mapped to this method.
 * 
 * ResponseStatus annotation tells Spring Boot the response status to use if everything is
 * successful.
 */ 
@PutMapping
@ResponseStatus(code = HttpStatus.OK)
Optional<Customer> updateCustomer(
    @Length(max = 50)
    @Pattern(regexp = "[\\w\\s]*")
    @RequestParam(required = false) String customerId,
    @Length(max = 25)
    @Pattern(regexp = "[\\w\\s]*")
    @RequestParam(required = false) String firstName,
    @Length(max = 25)
    @Pattern(regexp = "[\\w\\s]*")
    @RequestParam(required = false) String newFirstName,
    @Length(max = 25)
    @Pattern(regexp = "[\\w\\s]*")
    @RequestParam(required = false) String lastName,
    @Length(max = 25)
    @Pattern(regexp = "[\\w\\s]*")
    @RequestParam(required = false) String newLlastName,
    @Length(max = 20)
    @Pattern(regexp = "[0-9.\\s]*")
    @RequestParam(required = false) String phone,
    @Length(max = 20)
    @Pattern(regexp = "[0-9.\\s]*")
    @RequestParam(required = false) String newPhone
    ); // end createCustomer
//@formatter:on

//@formatter:off
@Operation(
 summary = "Delete a customer",
 description = "Delete a customer from the database",
 responses = {
     @ApiResponse(
         responseCode = "200",
         description = "A customer was deleted", 
         content = @Content(
             mediaType = "application/json", 
             schema = @Schema(
                 implementation = Customer.class))),
     @ApiResponse(
         responseCode = "400",
         description = "The request parameters are invalid", 
         content = @Content(
             mediaType = "application/json")),
     @ApiResponse(
         responseCode = "404", 
         description = "No customers found to be deleted or one of the values entered was incorrect", 
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
         name = "customerId", 
         allowEmptyValue = false,
         required = false,
         description = "Enter the nickname you selected when you first signed up"),
     @Parameter(
         name = "firstName", 
         allowEmptyValue = false,
         required = false,
         description = "The first name of the customer to be deleted"),     
     @Parameter(
         name = "lastName", 
         allowEmptyValue = false,
         required = false,
         description = "The last name of the customer to be deleted"),
     @Parameter(
         name = "phone", 
         allowEmptyValue = false,
         required = false,
         description = "The phone number of the customer to be deleted")
 } // end parameters
) // end Operation

/*
 * @DeleteMapping annotation tells Spring Boot that HTTP request DELETE is mapped to this method.
 * 
 * ResponseStatus annotation tells Spring Boot the response status to use if everything is
 * successful.
 */ 
@DeleteMapping
@ResponseStatus(code = HttpStatus.OK)
Optional<Customer> deleteCustomer(
  @Length(max = 50)
  @Pattern(regexp = "[\\w\\s]*")
  @RequestParam(required = false) String customerId,
  @Length(max = 25)
  @Pattern(regexp = "[\\w\\s]*")
  @RequestParam(required = false) String firstName,
  @Length(max = 25)
  @Pattern(regexp = "[\\w\\s]*")
  @RequestParam(required = false) String lastName,
  @Length(max = 20)
  @Pattern(regexp = "[0-9.\\s]*")
  @RequestParam(required = false) String phone
  ); // end deleteCustomer
//@formatter:on

} // end INTERFACE
