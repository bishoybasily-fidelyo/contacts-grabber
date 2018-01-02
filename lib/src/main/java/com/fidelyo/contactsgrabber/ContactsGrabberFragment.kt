package com.fidelyo.contactsgrabber

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import com.fidelyo.contactsgrabber.model.entity.Contact
import io.reactivex.ObservableEmitter

class ContactsGrabberFragment : Fragment() {

    private var emitter: ObservableEmitter<ArrayList<Contact>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun setEmitter(emitter: ObservableEmitter<ArrayList<Contact>>): ContactsGrabberFragment {
        this.emitter = emitter
        return this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ContactsGrabber.CODE) {
                if (data != null) {
                    if (emitter != null) {
                        emitter!!.onNext(data.getSerializableExtra(ContactsGrabber.EXTRA) as ArrayList<Contact>)
                        emitter!!.onComplete()
                    }
                }
            }
        }
    }

}