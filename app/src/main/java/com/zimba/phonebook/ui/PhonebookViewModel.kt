package com.zimba.phonebook.ui

import androidx.lifecycle.ViewModel
import com.zimba.phonebook.data.PhonebookRepository

class PhonebookViewModel : ViewModel() {

    fun findPhonebook() = PhonebookRepository.findPhonebook()
}