package com.rvictorino.bcm.backend.interview

import com.rvictorino.bcm.backend.interview.api.PowerPlantClient
import com.rvictorino.bcm.backend.interview.model.Production

class PowerSumAggregator {
    List<PowerPlantClient> powerPlantClients = new ArrayList<PowerPlantClient>()

    void addPowerPlant(PowerPlantClient powerPlantClient) {
        this.powerPlantClients.add(powerPlantClient)
    }

    Production getProductionSum(String from, String to) {
        List<Production> productions = this.powerPlantClients.collect { PowerPlantClient client ->
            return client.getProduction(from, to)
        }

        //TODO change this to extract summing operation from Production class, seems to better belong here
        Production sum = productions.inject(null as Production) { Production accumulatedProduction, Production currentProduction ->
            return currentProduction.sumMerge(accumulatedProduction)
        }

        return sum
    }
}
