package com.fidelyo.contactsgrabber

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fidelyo.contactsgrabber.model.entity.Contact
import com.fidelyo.recyclerview.RecyclerViewAdapter

/**
 * Created by bishoy on 1/2/18.
 */
class AdapterContacts() : RecyclerViewAdapter<Contact, ContactViewHolder>() {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_contact, parent, false))
    }

}