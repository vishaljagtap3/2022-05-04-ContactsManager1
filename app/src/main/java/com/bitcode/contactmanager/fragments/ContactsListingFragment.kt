package com.bitcode.contactmanager.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitcode.contactmanager.R
import com.bitcode.contactmanager.adapters.ContactsAdapter
import com.bitcode.contactmanager.databinding.ContactsListingFragmentBinding
import com.bitcode.contactmanager.models.Contact

class ContactsListingFragment : Fragment() {

    private lateinit var binding: ContactsListingFragmentBinding
    private var contactsList = ArrayList<Contact>()
    private lateinit var contactsAdapter : ContactsAdapter

    private val MENU_ADD_CONTACT = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = ContactsListingFragmentBinding.inflate(inflater)

        initData()
        initRecyclerViewAndAdapter()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        var menuItem = menu.add(0, MENU_ADD_CONTACT, 0, R.string.menu_add_contact)
        menuItem.setIcon(android.R.drawable.ic_menu_add)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == MENU_ADD_CONTACT) {
            addAddContactFragment()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addAddContactFragment() {
        var addContactFragment = AddContactFragment()

        addContactFragment.onContactAddedListener = MyOnContactAddedListener()

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, addContactFragment, AddContactFragment::class.java.name)
            .commit()
    }

    inner class MyOnContactAddedListener : AddContactFragment.OnContactAddedListener {
        override fun onContactAdded(contact: Contact) {
            contactsList.add(3, contact)
            //contactsAdapter.notifyDataSetChanged()
            //contactsAdapter.notifyItemInserted(contactsList.size-1)
            contactsAdapter.notifyItemInserted(3)
        }
    }


    private fun initRecyclerViewAndAdapter() {

        binding.recyclerContacts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        contactsAdapter = ContactsAdapter(contactsList)
        binding.recyclerContacts.adapter = contactsAdapter
    }

    private fun initData() {
        contactsList.add(Contact(1, "AA", "98810000", null, R.mipmap.ic_launcher))
        contactsList.add(Contact(1, "BB", "98810000", null, R.mipmap.ic_launcher))
        contactsList.add(Contact(1, "CC", "98810000", null, R.mipmap.ic_launcher))
        contactsList.add(Contact(1, "DD", "98810000", null, R.mipmap.ic_launcher))
        contactsList.add(Contact(1, "EE", "98810000", null, R.mipmap.ic_launcher))
        contactsList.add(Contact(1, "FF", "98810000", null, R.mipmap.ic_launcher))
        contactsList.add(Contact(1, "GG", "98810000", null, R.mipmap.ic_launcher))

    }
}