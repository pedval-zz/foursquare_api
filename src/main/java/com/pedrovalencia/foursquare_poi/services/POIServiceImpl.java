package com.pedrovalencia.foursquare_poi.services;

import com.pedrovalencia.foursquare_poi.beans.POIBean;
import com.pedrovalencia.foursquare_poi.exceptions.POIServiceException;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valenciap
 * on 01/02/2016.
 */
public class POIServiceImpl implements POIService{

    /* Parameters to connect  with Foursquare API */
    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${client.callback.url}")
    private String clientCallbackUrl;

    @Override
    public List<POIBean> getPOIs(String place) {
        List<POIBean> pois = new ArrayList<>();

        Result<VenuesSearchResult> result = getVenuesSearchFromAPI(place);

        if(result.getMeta().getCode() != 200) {
            throw new POIServiceException("Error code: "+ result.getMeta().getCode().toString());
        }

        convertToPOIBean(pois, result);

        return pois;
    }

    /**
     * Converter from the Foursquare API bean to our custom bean
     * (POIBean)
     * @param pois
     * @param result
     */
    private void convertToPOIBean(List<POIBean> pois, Result<VenuesSearchResult> result) {
        for (CompactVenue venue : result.getResult().getVenues()) {
            POIBean fourSquareBean =
                    new POIBean(venue.getName(),
                            venue.getLocation().getAddress(),
                            venue.getLocation().getPostalCode(),
                            venue.getLocation().getLat(),
                            venue.getLocation().getLng());
            pois.add(fourSquareBean);
        }
    }

    /**
     * Connect to foursquare API to retrieve useful information
     * for the POIs
     * @param place
     * @return
     */
    private Result<VenuesSearchResult> getVenuesSearchFromAPI(String place) {
        // First we need a initialize FoursquareApi.
        FoursquareApi foursquareApi = new FoursquareApi(clientId, clientSecret, clientCallbackUrl);

        // After client has been initialized we can make queries.
        Result<VenuesSearchResult> result;
        try {
            result =
                    foursquareApi.venuesSearch(null, null, null, null, null, null,
                            null, null, null, null, null, null, place);
        } catch (FoursquareApiException e) {
            throw new POIServiceException("Error calling foursquare api: ", e.getCause());
        }
        return result;
    }
}
