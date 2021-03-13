package com.rvictorino.bcm.backend.interview.model

import java.time.Instant
import java.util.Map.Entry

class Production {
    final Map<Instant, ProductionSegment> production = new LinkedHashMap<Instant, ProductionSegment>()

    void addSegment(ProductionSegment segment) {
        this.production.put(segment.start, segment)
    }

    ProductionSegment get(Instant instant) {
        return this.production.get(instant)
    }

    Production sumMerge(Production toMerge) {
        if(!toMerge) {
            return this
        }

        this.production.each { Entry<Instant, ProductionSegment> entry ->
            ProductionSegment sum = this.production.get(entry.key)
            sum.power += toMerge.get(entry.key).power
        }

        return this
    }

    List<ProductionSegment> toList() {
        return production.collect { Entry<Instant, ProductionSegment> entry ->
            return entry.value
        }
    }
}
