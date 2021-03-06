package com.rvictorino.bcm.backend.interview.api

import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.model.ProductionSegment
import com.xlson.groovycsv.CsvIterator
import com.xlson.groovycsv.CsvParser
import groovyx.net.http.HTTPBuilder

import java.time.Instant

class HounslowApiClient extends PowerPlantClient {

    static final String HOUNSLOW_MONITORING_API_ENDPOINT = 'https://interview.beta.bcmenergy.fr/hounslow'

    HounslowApiClient(HTTPBuilder httpClient) {
        super(httpClient)
    }

    @Override
    Production getProduction(String fromDate, String toDate) {
        //TODO handle errors: Http or parsing
        String csvResponse = httpClient.get(uri: HOUNSLOW_MONITORING_API_ENDPOINT, query: [
                from: fromDate,
                to: toDate
        ]) as String

        CsvIterator segments = new CsvParser().parse(csvResponse) as CsvIterator

        Production production = new Production()
        for(segment in segments) {
            Instant date = Instant.ofEpochSecond(segment.debut as Integer)
            Instant end = Instant.ofEpochSecond(segment.fin as Integer)
            Long power = segment.valeur as Long
            production.addSegment(new ProductionSegment(start: date, end: end, power: power))
        }

        production.fillMissingSegments()

        return production
    }
}
