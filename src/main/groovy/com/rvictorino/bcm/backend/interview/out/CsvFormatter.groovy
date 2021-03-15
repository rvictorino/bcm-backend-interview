package com.rvictorino.bcm.backend.interview.out

import com.rvictorino.bcm.backend.interview.model.Production
import com.rvictorino.bcm.backend.interview.model.ProductionSegment

class CsvFormatter implements OutputFormatter {
    static final String SEPARATOR = ','
    static final String LINE_END = '\n'

    @Override
    void formatAndPrint(Production production) {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append("start${SEPARATOR}end${SEPARATOR}power${LINE_END}")

        for(ProductionSegment segment in production.toList()) {
            stringBuilder.append("${segment.start.epochSecond}${SEPARATOR}${segment.end.epochSecond}${SEPARATOR}${segment.power}${LINE_END}")
        }

        print(stringBuilder.toString())
    }
}
