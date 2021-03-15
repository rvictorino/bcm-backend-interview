package com.rvictorino.bcm.backend.interview.model

import java.time.Instant
import java.util.Map.Entry

class Production {
    static final long SEGMENT_DURATION_IN_SECONDS = 900 //15 minutes
    final Map<Instant, ProductionSegment> production = new LinkedHashMap<Instant, ProductionSegment>()
    Instant start = Instant.MAX
    Instant end = Instant.MIN

    void addSegment(ProductionSegment segment) {
        // save whole production starting and ending date, for later use
        if(segment.start < this.start) {
            this.start = segment.start
        }
        if(segment.end > this.end) {
            this.end = segment.end
        }
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
            ProductionSegment sum = this.get(entry.key)
            sum.power += toMerge.get(entry.key).power
        }

        return this
    }

    void fillMissingSegments() {
        Instant currentSegmentTime = this.start + SEGMENT_DURATION_IN_SECONDS
        while(currentSegmentTime < this.end) {
            if(!this.production.containsKey(currentSegmentTime)) {
                this.fillMissingSegmentAt(currentSegmentTime)
            }
            currentSegmentTime += SEGMENT_DURATION_IN_SECONDS
        }
    }

    private void fillMissingSegmentAt(Instant segmentTime) {
        ProductionSegment before = this.production.get(segmentTime - SEGMENT_DURATION_IN_SECONDS)
        ProductionSegment after = this.production.get(segmentTime + SEGMENT_DURATION_IN_SECONDS)

        this.production.put(segmentTime, new ProductionSegment(
                start: segmentTime,
                end: after.start,
                power: before.power + after.power
        ))
    }

    List<ProductionSegment> toList() {
        return this.production.collect { Entry<Instant, ProductionSegment> entry ->
            return entry.value
        }
    }
}
