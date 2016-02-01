package com.pedrovalencia.foursquare_poi;

import com.pedrovalencia.foursquare_poi.beans.POIBean;
import com.pedrovalencia.foursquare_poi.services.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by valenciap
 * on 01/02/2016.
 */

@SpringBootApplication
@RestController
@RequestMapping("/poi")
public class FoursquarePoiApplication {

    POIService poiService;

    /**
     * GET method to retrieve Foursquare POIs
     * @param query
     * @return List of POIs
     */
    @RequestMapping(value="/find/{query}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<POIBean> getPOIs(@PathVariable String query) {
        return poiService.getPOIs(query);
    }

    @Autowired
    public void setFourSquareService(POIService poiService) {
        this.poiService = poiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FoursquarePoiApplication.class, args);
    }
}
