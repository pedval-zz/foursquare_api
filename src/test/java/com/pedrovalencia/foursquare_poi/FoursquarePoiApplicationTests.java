package com.pedrovalencia.foursquare_poi;

import com.pedrovalencia.foursquare_poi.beans.POIBean;
import com.pedrovalencia.foursquare_poi.exceptions.POIServiceException;
import com.pedrovalencia.foursquare_poi.services.POIService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by valenciap
 * on 01/02/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class FoursquarePoiApplicationTests {
    private MockMvc mvc;

    @InjectMocks
    private FoursquarePoiApplication controller;

    @Mock
    private POIService poiService;

    @Before
    public void setUp() throws Exception {
        poiService = Mockito.mock(POIService.class);
        controller = new FoursquarePoiApplication();
        controller.setFourSquareService(poiService);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getPOIsOK() throws Exception {

        List<POIBean> pois = getPOIBeans();

        when(poiService.getPOIs("london")).thenReturn(pois);

        mvc.perform(MockMvcRequestBuilders.get("/poi/find/london"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"name\":\"London\",\"address\":\"Street 1\",\"postalCode\":\"NW1 AAA\",\"lat\":0.0,\"lng\":0.0},{\"name\":\"Trafalgar Square\",\"address\":\"Trafalgar Sq\",\"postalCode\":\"N1 AAA\",\"lat\":0.0,\"lng\":0.0}]")));
    }

    @Test
    public void getTestEmptyResult() throws Exception {

        List<POIBean> pois =  new ArrayList<>();

        when(poiService.getPOIs("london")).thenReturn(pois);

        mvc.perform(MockMvcRequestBuilders.get("/poi/find/london"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test(expected=Exception.class)
    public void getTestNoOK() throws Exception{

        List<POIBean> pois = getPOIBeans();

        when(poiService.getPOIs("london")).thenThrow(new POIServiceException("Error"));

        mvc.perform(MockMvcRequestBuilders.get("/poi/find/london"))
                .andExpect(content().string(equalTo("[]")));
    }

    /**
     * Initializes the List of POIBean to mock the response
     * @return
     */
    private List<POIBean> getPOIBeans() {
        POIBean poi1 = new POIBean("London", "Street 1", "NW1 AAA", 0,0);
        POIBean poi2 = new POIBean("Trafalgar Square", "Trafalgar Sq", "N1 AAA",0,0);

        List<POIBean> pois =  new ArrayList<>();
        pois.add(poi1);
        pois.add(poi2);
        return pois;
    }


}

