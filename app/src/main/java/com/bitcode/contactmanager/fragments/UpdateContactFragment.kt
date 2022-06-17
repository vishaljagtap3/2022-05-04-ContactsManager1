package com.bitcode.contactmanager.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bitcode.contactmanager.R
import com.bitcode.contactmanager.databinding.AddContactFragmentBinding
import com.bitcode.contactmanager.databinding.UpdateContactFragmentBinding
import com.bitcode.contactmanager.models.Contact

class UpdateContactFragment : Fragment() {

    private lateinit var binding : UpdateContactFragmentBinding

    interface OnContactUpdateListener {
        fun onContactUpdated(newContact: Contact)
    }

    var onContactUpdateListener : OnContactUpdateListener? = null
    lateinit var contact : Contact

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = UpdateContactFragmentBinding.inflate(inflater)

        initDataAndViews()
        setUpListeners()

        return binding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun initDataAndViews() {
        contact = arguments!!.getSerializable("contact") as Contact

        binding.imgContact.setImageResource(contact.imageId!!)
        binding.contact = contact
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()

        (activity as AppCompatActivity).supportActionBar?.title = "Update Contact"
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

        binding.btnUpdateContact.setOnClickListener {

            var newContact = Contact(
                binding.edtContactId.text.toString().toInt(),
                binding.edtContactName.text.toString(),
                binding.edtContactPhoneNumber.text.toString(),
                binding.edtContactEmail.text.toString(),
                R.mipmap.ic_launcher
            )

            onContactUpdateListener?.onContactUpdated(newContact)

            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }
}