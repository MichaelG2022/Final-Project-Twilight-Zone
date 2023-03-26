package cds.twilight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cds.ComponentScanMarker;

/**
 * @author mwgoeres
 * 
 * 
 * SpringBootApplication annotation tells Spring Boot that this is a Spring Boot application.
 * Then the Main method tells SpringApplication to run this class.
 */
@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class TwilightZone {

  public static void main(String[] args) {
    SpringApplication.run(TwilightZone.class, args);

  } // end MAIN

} // end CLASS
