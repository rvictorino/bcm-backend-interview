package com.rvictorino.bcm.backend.interview.out

import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.out.converter.InstantToEpochJsonConverter
import groovy.json.JsonGenerator
import groovy.json.JsonOutput

class JsonPrinter implements ProductionPrinter {

    @Override
    void formatAndPrint(Production production) {
         JsonGenerator jsonGenerator = new JsonGenerator.Options()
            .addConverter(new InstantToEpochJsonConverter())
            .build()
        String json = jsonGenerator.toJson(production.toList())
        print(JsonOutput.prettyPrint(json))
    }
}
