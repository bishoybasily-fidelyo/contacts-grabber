package com.fidelyo.contactsgrabber.model.entity

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.fidelyo.contactsgrabber.BR
import com.fidelyo.recyclerview.RecyclerViewAdapter
import java.io.Serializable

/**
 * Created by bishoy on 12/31/17.
 */

class Contact : BaseObservable(), RecyclerViewAdapter.Item, Serializable {

    var id: String? = null
    var name: String? = null
    var type: String? = null
    var number: String? = null
    @field:[Bindable]
    var selected: Boolean = false
        set(value) {
            field = value; notifyPropertyChanged(BR.selected)
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Contact

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}
