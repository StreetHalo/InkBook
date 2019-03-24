package com.art.tattoosearch.jsonModel


import java.util.HashMap

class Example {

    var kind: String? = null
    var url: Url? = null
    var queries: Queries? = null
    var context: Context? = null
    var searchInformation: SearchInformation? = null
    var items: List<Item>? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
