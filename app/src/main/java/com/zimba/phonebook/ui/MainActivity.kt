package com.zimba.phonebook.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zimba.phonebook.data.State
import com.zimba.phonebook.databinding.ActivityMainBinding
import com.zimba.phonebook.ui.adapter.PhonebookAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<PhonebookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        findPhonebook()

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        binding.srlContacts.setOnRefreshListener {
            findPhonebook()
        }
    }


    private fun findPhonebook() {
        viewModel.findPhonebook().observe(this) { state ->
            when (state) {
                is State.Success -> {
                    binding.rvContacts.adapter = state.data?.let { PhonebookAdapter(it) }
                    binding.srlContacts.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let {
                        Snackbar.make(binding.rvContacts, it, Snackbar.LENGTH_LONG).show()
                    }
                    binding.srlContacts.isRefreshing = false
                }
                State.Wait -> binding.srlContacts.isRefreshing = true
            }
        }
    }
}