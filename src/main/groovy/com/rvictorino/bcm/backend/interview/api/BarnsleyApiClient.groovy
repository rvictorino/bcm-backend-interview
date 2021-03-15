package com.rvictorino.bcm.backend.interview.api

import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.model.ProductionSegment
import groovyx.net.http.HTTPBuilder

import java.time.Instant

class BarnsleyApiClient extends PowerPlantClient {

    static final String BARNSLEY_MONITORING_API_ENDPOINT = 'https://interview.beta.bcmenergy.fr/barnsley'

    BarnsleyApiClient(HTTPBuilder httpClient) {
        super(httpClient)
    }

    @Override
    Production getProduction(String fromDate, String toDate) {
        //TODO handle errors: Http or parsing
        List<Map> jsonResponse = httpClient.get(uri: BARNSLEY_MONITORING_API_ENDPOINT, contentType : 'application/json', query: [
                from: fromDate,
                to: toDate
        ]) as List<Map>

        Production production = new Production()
        for(Map segment in jsonResponse) {
            Instant date = Instant.ofEpochSecond(segment.start_time as Integer)
            Instant end = Instant.ofEpochSecond(segment.end_time as Integer)
            Long power = segment.value as Long
            production.addSegment(new ProductionSegment(start: date, end: end, power: power))
        }

        production.fillMissingSegments()

        return production
    }
}
