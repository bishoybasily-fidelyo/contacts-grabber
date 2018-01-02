package com.fidelyo.contactsgrabber


import com.fidelyo.contactsgrabber.databinding.ItemContactBinding
import com.fidelyo.contactsgrabber.model.entity.Contact
import com.fidelyo.recyclerview.RecyclerViewBindingViewHolder

/**
 * Created by bishoy on 1/2/18.
 */

class ContactViewHolder(val binding: ItemContactBinding) : RecyclerViewBindingViewHolder<Contact, ItemContactBinding>(binding) {

    override fun onAttached(contact: Contact) {
        binding.contact = contact
    }

    override fun onDetached(contact: Contact) {

    }
}
