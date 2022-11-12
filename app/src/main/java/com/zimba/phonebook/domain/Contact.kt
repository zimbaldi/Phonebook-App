package com.zimba.phonebook.domain

data class Contact(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val email: String
)