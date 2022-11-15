package com.zimba.phonebook.data.remote

import com.zimba.phonebook.domain.Contact
import retrofit2.http.GET

interface PhonebookApi {

    @GET("/contacts")
    suspend fun findPhonebook(): List<Contact>
}