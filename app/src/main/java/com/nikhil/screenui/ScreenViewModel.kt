package com.nikhil.screenui

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScreenViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val valid = MediatorLiveData<Boolean>().apply {
        addSource(email) {
            value = isFormValid(email.value, password.value, confirmPassword.value)
        }
        addSource(password){
            value = isFormValid(email.value, password.value, confirmPassword.value)
        }
        addSource(confirmPassword){
            value = isFormValid(email.value, password.value, confirmPassword.value)
        }
    }

    fun isFormValid(emailAddress: String?, password: String?, confirmPassword: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress.toString()).matches() &&
                password!=null && password != "" && password.length > 3 &&
                confirmPassword!=null && confirmPassword != "" && confirmPassword.length > 3 &&
                password == confirmPassword
    }

}