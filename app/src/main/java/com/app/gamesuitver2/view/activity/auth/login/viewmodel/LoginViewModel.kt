package com.app.gamesuitver2.view.activity.auth.login.viewmodel

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.gamesuitver2.repository.LoginRepository
import com.app.gamesuitver2.view.activity.DashboardActivity
import com.app.gamesuitver2.view.activity.auth.login.model.Login
import kotlinx.coroutines.*


class LoginViewModel(private val repository: LoginRepository): ViewModel() {
    val errorMsg = MutableLiveData<String>()
    val login = MutableLiveData<Login>()
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
        onError("Exception Handled: ${throwable.localizedMessage}")
    }

    fun login(email: String, password: String){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.value = true
            val response = repository.login()
            withContext(Dispatchers.IO){
                if(response?.isSuccessful == true){
                    loading.value =false
                }else{
                    onError("Error: ${response?.message()}")
                }
            }
        }
    }


    private fun onError(msg: String){
        errorMsg.value = msg
        loading.value = false
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}