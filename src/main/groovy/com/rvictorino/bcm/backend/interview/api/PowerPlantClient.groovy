package com.rvictorino.bcm.backend.interview.api

import com.rvictorino.bcm.backend.interview.model.Production
import groovyx.net.http.HTTPBuilder

abstract class PowerPlantClient {

    final HTTPBuilder httpClient

    PowerPlantClient(HTTPBuilder httpClient) {
        this.httpClient = httpClient
    }

    abstract Production getProduction(String fromDate, String toDate)
}