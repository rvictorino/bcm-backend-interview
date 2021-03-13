package com.rvictorino.bcm.backend.interview.out

import com.rvictorino.bcm.backend.interview.model.Production

interface ProductionPrinter {
    void formatAndPrint(Production production)
}