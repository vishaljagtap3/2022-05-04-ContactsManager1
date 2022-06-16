package com.bitcode.contactmanager.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bitcode.contactmanager.R
import com.bitcode.contactmanager.databinding.AddContactFragmentBinding
import com.bitcode.contactmanager.models.Contact

class AddContactFragment : Fragment() {

    private lateinit var binding : AddContactFragmentBinding

    interface OnContactAddedListener {
        fun onContactAdded(contact: Contact)
    }

    var onContactAddedListener : OnContactAddedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = AddContactFragmentBinding.inflate(inflater)

        setUpListeners()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()

        (activity as AppCompatActivity).supportActionBar?.title = "New Contact"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_back)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            android.R.id.home ->
                //removeCurrentFragment()
            parentFragmentManager.popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpListeners() {

        binding.btnAddContact.setOnClickListener {

            var newContact = Contact(
                binding.edtContactId.text.toString().toInt(),
                binding.edtContactName.text.toString(),
                binding.edtContactPhoneNumber.text.toString(),
                binding.edtContactEmail.text.toString(),
                R.mipmap.ic_launcher
            )

            onContactAddedListener?.onContactAdded(newContact)

            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }
}