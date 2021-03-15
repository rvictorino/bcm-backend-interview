package com.rvictorino.bcm.backend.interview.out

import com.rvictorino.bcm.backend.interview.model.Production

interface OutputFormatter {
    void formatAndPrint(Production production)
}