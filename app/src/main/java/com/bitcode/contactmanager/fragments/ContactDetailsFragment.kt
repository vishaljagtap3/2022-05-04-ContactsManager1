package com.bitcode.contactmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcode.contactmanager.databinding.ContactDetailsFragmentBinding
import com.bitcode.contactmanager.models.Contact

class ContactDetailsFragment : Fragment() {

    private lateinit var binding : ContactDetailsFragmentBinding
    private lateinit var contact : Contact

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ContactDetailsFragmentBinding.inflate(inflater)

        contact = arguments?.getSerializable("contact") as Contact

        binding.imgContact.setImageResource(contact.imageId!!)
        binding.contact = contact

        return binding.root
    }
}