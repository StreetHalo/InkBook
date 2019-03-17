package com.example.tattoosearch.jsonModel

import java.util.HashMap

class Queries {

    var request: List<Request>? = null
    var nextPage: List<NextPage>? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
