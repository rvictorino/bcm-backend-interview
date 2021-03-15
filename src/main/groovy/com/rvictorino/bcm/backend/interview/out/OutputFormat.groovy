package com.rvictorino.bcm.backend.interview.out

enum OutputFormat {
    JSON,
    CSV

    static OutputFormat getByName(String formatName) {
        for(OutputFormat outputFormat in values()) {
            if(outputFormat.name() == formatName) {
                return outputFormat
            }
        }
        return null
    }
}