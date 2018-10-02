package com.codezfox.exchangerates.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity
@Root(name = "Currency", strict = false)
class Currency {
    @PrimaryKey
    @field:Attribute(name = "Id")
    var id: String = ""

    @field:Element(name = "NumCode")
    var numCode: String? = null

    @field:Element(name = "CharCode")
    var charCode: String? = null

    @field:Element(name = "Scale")
    var scale: Int? = null

    @field:Element(name = "Name")
    var name: String? = null

    @field:Element(name = "Rate")
    var rate: String? = null

    fun descriptionUI() = "$rate $charCode = $scale BYN"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Currency

        if (id != other.id) return false
        if (numCode != other.numCode) return false
        if (charCode != other.charCode) return false
        if (scale != other.scale) return false
        if (name != other.name) return false
        if (rate != other.rate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (numCode?.hashCode() ?: 0)
        result = 31 * result + (charCode?.hashCode() ?: 0)
        result = 31 * result + (scale ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (rate?.hashCode() ?: 0)
        return result
    }


}

