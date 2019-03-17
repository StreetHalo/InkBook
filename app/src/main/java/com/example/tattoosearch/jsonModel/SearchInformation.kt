package com.example.tattoosearch.jsonModel

import java.util.HashMap

class SearchInformation {

    var searchTime: Double? = null
    var formattedSearchTime: String? = null
    var totalResults: String? = null
    var formattedTotalResults: String? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
