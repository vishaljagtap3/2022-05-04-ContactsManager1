package com.bitcode.contactmanager.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bitcode.contactmanager.R
import com.bitcode.contactmanager.databinding.ContactDetailsFragmentBinding
import com.bitcode.contactmanager.models.Contact

class ContactDetailsFragment : Fragment() {

    private val MENU_UPDATE: Int = 11
    private val MENU_DELETE: Int = 10

    private lateinit var binding: ContactDetailsFragmentBinding
    private lateinit var contact: Contact

    interface OnContactUpdateDeleteListener {
        fun onContactDelete(contact: Contact)
        fun onContactUpdate(oldContact : Contact, newContact : Contact)
    }

    var onContactUpdateDeleteListener: OnContactUpdateDeleteListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = ContactDetailsFragmentBinding.inflate(inflater)

        contact = arguments?.getSerializable("contact") as Contact

        binding.imgContact.setImageResource(contact.imageId!!)
        binding.contact = contact

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()

        var menuItemDelete = menu.add(1, MENU_DELETE, 0, "Delete")
        menuItemDelete.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItemDelete.setIcon(android.R.drawable.ic_menu_delete)

        var menuItemUpdate = menu.add(1, MENU_UPDATE, 0, "Update")
        menuItemUpdate.setIcon(android.R.drawable.ic_menu_edit)
        menuItemUpdate.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_DELETE -> {
                deleteCurrentContact()
            }
            MENU_UPDATE -> {
                updateCurrentContact()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun deleteCurrentContact() {

        AlertDialog.Builder(context!!)
            .setTitle("Delete Contact!")
            .setMessage(R.string.title_contact_deletition_confirmation)
            .setPositiveButton(
                R.string.label_delete_contact_yes
            ) { dialog, which ->
                onContactUpdateDeleteListener?.onContactDelete(contact)
                removeCurrentFragment()
            }
            .setNegativeButton(
                R.string.label_delete_contact_cancel
            ) { dialog, which ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun updateCurrentContact() {
        var updateContactFragment = UpdateContactFragment()

        updateContactFragment.onContactUpdateListener =
            object : UpdateContactFragment.OnContactUpdateListener {
                override fun onContactUpdated(newContact: Contact) {
                    binding.imgContact.setImageResource(newContact.imageId!!)
                    binding.contact = newContact

                    onContactUpdateDeleteListener?.onContactUpdate(
                        contact,
                        newContact
                    )

                    removeCurrentFragment()
                }
            }

        var input = Bundle()
        input.putSerializable("contact", contact)
        updateContactFragment.arguments =  input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, updateContactFragment, UpdateContactFragment::class.java.name)
            .addToBackStack(UpdateContactFragment::class.java.name)
            .commit()
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }
}