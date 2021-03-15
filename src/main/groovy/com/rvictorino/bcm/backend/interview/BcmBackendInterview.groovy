package com.rvictorino.bcm.backend.interview

import com.rvictorino.bcm.backend.interview.api.BarnsleyApiClient
import com.rvictorino.bcm.backend.interview.api.HawesApiClient
import com.rvictorino.bcm.backend.interview.api.HounslowApiClient
import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.out.CsvPrinter
import com.rvictorino.bcm.backend.interview.out.JsonPrinter
import com.rvictorino.bcm.backend.interview.out.OutputFormat
import com.rvictorino.bcm.backend.interview.out.ProductionPrinter
import groovyx.net.http.HTTPBuilder

class BcmBackendInterview {

    static void main(String[] args) {
        //TODO validate arguments
        String from = args[0]
        String to = args[1]
        String outputFormat = args[2]

        PowerSumAggregator powerAggregator = new PowerSumAggregator()

        HTTPBuilder httpBuilder = new HTTPBuilder()

        //TODO implement and replace other power plants clients
        powerAggregator.addPowerPlant(new HawesApiClient(httpBuilder))
        powerAggregator.addPowerPlant(new BarnsleyApiClient(httpBuilder))
        powerAggregator.addPowerPlant(new HounslowApiClient(httpBuilder))

        //TODO use more appropriate types for arguments (currently Strings)
        Production sum = powerAggregator.getProductionSum(from, to)

        ProductionPrinter formatter = getPrinter(outputFormat)

        formatter.formatAndPrint(sum)
    }

    //TODO extract
    static ProductionPrinter getPrinter(String format) {
        OutputFormat outputFormat = OutputFormat.getByName(format.toUpperCase())
        if(outputFormat == OutputFormat.JSON) {
            return new JsonPrinter()
        }
        if(outputFormat == OutputFormat.CSV) {
            return new CsvPrinter()
        }
        //TODO implement other formatter
        throw new IllegalArgumentException("Cannot find output formatAndPrint: ${format}")
    }
}
