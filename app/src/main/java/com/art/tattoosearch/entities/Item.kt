package com.art.tattoosearch.entities

import java.util.HashMap

open class Item {

    var kind: String? = null
    var title: String? = null
    var htmlTitle: String? = null
    var link: String? = null
    var displayLink: String? = null
    var snippet: String? = null
    var htmlSnippet: String? = null
    var mime: String? = null
    var image: Image? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
