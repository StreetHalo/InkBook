package com.example.tattoosearch.jsonModel

import java.util.HashMap

class Request {

    var title: String? = null
    var totalResults: String? = null
    var searchTerms: String? = null
    var count: Int? = null
    var startIndex: Int? = null
    var inputEncoding: String? = null
    var outputEncoding: String? = null
    var safe: String? = null
    var cx: String? = null
    var searchType: String? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
