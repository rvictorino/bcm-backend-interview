package com.rvictorino.bcm.backend.interview

import com.rvictorino.bcm.backend.interview.api.BarnsleyApiClient
import com.rvictorino.bcm.backend.interview.api.HawesApiClient
import com.rvictorino.bcm.backend.interview.api.HounslowApiClient
import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.out.JsonPrinter
import com.rvictorino.bcm.backend.interview.out.ProductionPrinter

class BcmBackendInterview {

    static final String JSON_FORMAT = 'json'

    static void main(String[] args) {
        //TODO validate arguments
        String from = args[0]
        String to = args[1]
        String outputFormat = args[2]

        PowerSumAggregator powerAggregator = new PowerSumAggregator()

        //TODO implement and replace other power plants clients
        powerAggregator.addPowerPlant(new HawesApiClient())
        powerAggregator.addPowerPlant(new BarnsleyApiClient())
        powerAggregator.addPowerPlant(new HounslowApiClient())

        //TODO use more appropriate types for arguments (currently Strings)
        Production sum = powerAggregator.getProductionSum(from, to)

        ProductionPrinter formatter = getPrinter(outputFormat)

        formatter.formatAndPrint(sum)
    }

    //TODO extract
    static ProductionPrinter getPrinter(String format) {
        if(JSON_FORMAT == format) {
            return new JsonPrinter()
        }
        //TODO implement other formatter
        throw new IllegalArgumentException("Cannot find output formatAndPrint: ${format}")
    }
}
