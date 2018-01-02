package com.fidelyo.contactsgrabber.model.interactor

import android.content.Context
import android.provider.ContactsContract
import com.fidelyo.contactsgrabber.model.entity.Contact
import io.reactivex.Observable

/**
 * Created by bishoy on 12/31/17.
 */

class InteractorContacts(val context: Context) {

    fun findAll(): Observable<ArrayList<Contact>> {

        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf<String>(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.RawContacts.ACCOUNT_TYPE)
        val selection = "${ContactsContract.Contacts.HAS_PHONE_NUMBER} = ?"
        val selectionArgs = arrayOf("1")
        val sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC"

        return Observable.create {

            val result = ArrayList<Contact>()

            val cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
            while (cursor.moveToNext()) {

                val id = cursor.getString(cursor.getColumnIndex(projection[0]))
                val name = cursor.getString(cursor.getColumnIndex(projection[1]))
                val number = cursor.getString(cursor.getColumnIndex(projection[2]))
                val type = cursor.getString(cursor.getColumnIndex(projection[3]))

                result.add(Contact().apply {
                    this@apply.id = id
                    this@apply.name = name
                    this@apply.number = number
                    this@apply.type = type
                })

            }

            cursor.close()

            it.onNext(result)
            it.onComplete()
        }

    }

}
