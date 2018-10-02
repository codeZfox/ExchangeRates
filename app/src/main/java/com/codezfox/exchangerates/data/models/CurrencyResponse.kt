package com.codezfox.exchangerates.data.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "DailyExRates", strict = false)
class CurrencyResponse {

    @field:ElementList(inline = true)
    var courses: List<Currency>? = null
}