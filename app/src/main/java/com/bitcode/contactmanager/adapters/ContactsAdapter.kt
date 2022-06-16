package com.bitcode.contactmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.contactmanager.R
import com.bitcode.contactmanager.databinding.ContactViewBinding
import com.bitcode.contactmanager.fragments.ContactDetailsFragment
import com.bitcode.contactmanager.models.Contact

class ContactsAdapter(
  private var contactsList : ArrayList<Contact>
  //private var fragmentManager : FragmentManager
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    interface OnContactEventListener {
        fun onContactClick(position: Int, contact : Contact)
        fun onContactLongClick(position: Int, contact : Contact)
    }

    var onContactEventListener : OnContactEventListener? = null


    inner class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
         var binding : ContactViewBinding = ContactViewBinding.bind(itemView)

        init {

            binding.root.setOnClickListener {
                onContactEventListener?.onContactClick(adapterPosition, contactsList[adapterPosition])
            }

            binding.root.setOnLongClickListener(
                object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        onContactEventListener?.onContactLongClick(
                            adapterPosition, contactsList[adapterPosition]
                        )

                        return false
                    }
                }
            )

            /*binding.imgContact.setOnClickListener {

            }*/
            //not good
           /* binding.root.setOnClickListener {
                var contactDetailsFragment = ContactDetailsFragment()

                fragmentManager.beginTransaction()
                    .add(R.id.mainContainer, contactDetailsFragment, ContactDetailsFragment::class.java.name)
                    .commit()
            }*/

        }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_view, null)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.contact = contactsList[position]
    }
}