package com.pedrovalencia.foursquare_poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valenciap
 * on 01/02/2016.
 */

@SpringBootApplication
@RestController
@RequestMapping("/poi")
public class FoursquarePoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoursquarePoiApplication.class, args);
    }
}
