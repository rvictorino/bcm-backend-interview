package com.rvictorino.bcm.backend.interview.api

import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.model.ProductionSegment
import groovyx.net.http.HTTPBuilder

import java.time.Instant


//TODO use template pattern to handle requesting and parsing response
class HawesApiClient extends PowerPlantClient {

    //TODO extract to config
    static final String HAWES_MONITORING_API_ENDPOINT = 'https://interview.beta.bcmenergy.fr/hawes'

    HawesApiClient(HTTPBuilder httpClient) {
        super(httpClient)
    }

    @Override
    Production getProduction(String fromDate, String toDate) {
        //TODO handle errors: Http or parsing
        List<Map> jsonResponse = httpClient.get(uri: HAWES_MONITORING_API_ENDPOINT, contentType : 'application/json', query: [
                from: fromDate,
                to: toDate
        ], ) as List<Map>

        Production production = new Production()
        for(Map segment in jsonResponse) {
            Instant date = Instant.ofEpochSecond(segment.start as Integer)
            Instant end = Instant.ofEpochSecond(segment.end as Integer)
            Long power = segment.power as Long
            production.addSegment(new ProductionSegment(start: date, end: end, power: power))
        }

        return production
    }
}
