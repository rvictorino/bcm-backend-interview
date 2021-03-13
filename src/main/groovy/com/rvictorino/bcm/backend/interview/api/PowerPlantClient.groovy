package com.rvictorino.bcm.backend.interview.api

import com.rvictorino.bcm.backend.interview.model.Production

interface PowerPlantClient {
    Production getProduction(String fromDate, String toDate)
}