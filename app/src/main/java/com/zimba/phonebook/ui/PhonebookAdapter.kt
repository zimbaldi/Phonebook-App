package com.zimba.phonebook.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zimba.phonebook.databinding.ItemContactBinding
import com.zimba.phonebook.domain.Contact

class PhonebookAdapter(private val dataSet: List<Contact>) :
    RecyclerView.Adapter<PhonebookAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Contact) = with(binding) {
            tvNome.text = item.name
            tvTelefone.text = item.phoneNumber
            tvEmail.text = item.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size
}