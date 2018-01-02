package com.fidelyo.contactsgrabber

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.fidelyo.contactsgrabber.model.entity.Contact
import com.fidelyo.contactsgrabber.model.interactor.InteractorContacts
import com.fidelyo.recyclerview.LinearHorizontalSpacingItemDecoration
import com.fidelyo.recyclerview.LinearVerticalSpacingItemDecoration
import com.fidelyo.recyclerview.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_contacts_grabber.*
import kotlinx.android.synthetic.main.content_activity_contacts_grabber.*

class ActivityContactsGrabber : AppCompatActivity() {

    lateinit var interactorContacts: InteractorContacts

    val contacts = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_grabber)
        setSupportActionBar(toolbar)

        interactorContacts = InteractorContacts(this)

        val adapterContacts = AdapterContacts()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val spacing = LinearVerticalSpacingItemDecoration(26)

        recyclerContacts.adapter = adapterContacts
        recyclerContacts.layoutManager = layoutManager
        recyclerContacts.addItemDecoration(spacing)

        adapterContacts.clickListener = object : RecyclerViewAdapter.OnItemClickListener<Contact> {
            override fun onClicked(i: Contact, view: View) {
                i.selected = !i.selected
                if (i.selected) contacts.add(i)
                else contacts.remove(i)
            }
        }

        interactorContacts.findAll().subscribe { adapterContacts.showAll(it) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_contacts_grabber, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_done -> {
                setResult(RESULT_OK, intent.putExtra(ContactsGrabber.EXTRA, contacts)); finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
