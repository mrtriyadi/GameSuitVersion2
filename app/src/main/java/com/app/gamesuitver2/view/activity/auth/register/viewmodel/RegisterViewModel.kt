package com.app.gamesuitver2.view.activity.auth.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.gamesuitver2.repository.RegisterRepository
import com.app.gamesuitver2.view.activity.auth.register.model.Register
import kotlinx.coroutines.*

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val register = MutableLiveData<Register>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception Handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun register(username: String, email: String, password: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.value = true
            val response = repository.register()
            withContext(Dispatchers.Main) {
                if (response?.isSuccessful == true) {
                    loading.value = false
                } else {
                    onError("Error: ${response?.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}