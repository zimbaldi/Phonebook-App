package com.zimba.phonebook.data

import android.util.Log
import androidx.lifecycle.liveData
import com.zimba.phonebook.data.remote.PhonebookApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhonebookRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhonebookApi::class.java)
    }

    fun findPhonebook() = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = restApi.findPhonebook()))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }
}