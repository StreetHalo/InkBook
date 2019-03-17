package com.example.tattoosearch.jsonModel

import java.util.HashMap

class Image {

    var contextLink: String? = null
    var height: Int? = null
    var width: Int? = null
    var byteSize: Int? = null
    var thumbnailLink: String? = null
    var thumbnailHeight: Int? = null
    var thumbnailWidth: Int? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
