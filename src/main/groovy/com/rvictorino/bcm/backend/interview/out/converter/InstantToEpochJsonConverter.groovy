package com.rvictorino.bcm.backend.interview.out.converter

import groovy.json.JsonGenerator

import java.time.Instant

class InstantToEpochJsonConverter implements JsonGenerator.Converter {
    @Override
    boolean handles(Class<?> type) {
        return Instant.class == type
    }

    @Override
    Object convert(Object value, String key) {
        Instant instant = value as Instant
        return instant?.epochSecond?.toString()
    }
}
