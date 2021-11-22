package com.renditriyadi.gamesuitver2.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.renditriyadi.gamesuitver2.model.User
import com.renditriyadi.gamesuitver2.model.network.RetrofitConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackBarText= MutableLiveData<String>()
    val snackBarText: LiveData<String> = _snackBarText


    fun register(username: String, email: String, password: String) {
        _isLoading.value = true
        val registerUser = User(username, email, password)

        RetrofitConfig.getApiService().register(registerUser).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                _isLoading.value = false
                if (response.code() == 201) {
                    _snackBarText.value = response.message()
                }
                else {
                    _snackBarText.value = response.message()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

}