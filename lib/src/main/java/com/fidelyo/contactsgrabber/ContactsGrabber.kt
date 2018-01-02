package com.fidelyo.contactsgrabber

import android.app.Activity
import android.content.Intent
import com.fidelyo.contactsgrabber.model.entity.Contact
import io.reactivex.Observable

/**
 * Created by bishoy on 12/26/17.
 */

class ContactsGrabber {

    fun with(activity: Activity): Grabber {
        return Grabber(activity)
    }

    open class Grabber(val activity: Activity) {

        val TAG = javaClass.simpleName

        fun grab(): Observable<ArrayList<Contact>> {
            return Observable.create { e ->
                getFragment(activity).setEmitter(e).startActivityForResult(Intent(activity, ActivityContactsGrabber::class.java), CODE)
            }
        }

        private fun getFragment(activity: Activity): ContactsGrabberFragment {
            val fragmentManager = activity.fragmentManager
            var fragment = fragmentManager.findFragmentByTag(TAG)
            if (fragment == null) {
                fragment = ContactsGrabberFragment()
                fragmentManager
                        .beginTransaction()
                        .add(fragment, TAG)
                        .commitAllowingStateLoss()
                fragmentManager.executePendingTransactions()
            }
            return fragment as ContactsGrabberFragment
        }
    }


    companion object {

        val CODE = 40
        val EXTRA = "extra_path"
    }
}
