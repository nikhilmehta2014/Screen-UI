package com.nikhil.screenui

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScreenViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<String>()

    fun validateFields(){
        if(!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            emailError.value = "Enter correct Email"
        }
        if(password.value==null){
            passwordError.value = "Field should not be null"
            return
        }
        if(confirmPassword.value==null){
            confirmPasswordError.value = "Field should not be null"
            return
        }
        if( password.value == ""){
            passwordError.value = "Field should not be null"
        }
        if(password.value!!.length <= 6){
            passwordError.value = "Password should be at least 6 characters."
        }
        if(confirmPassword.value == ""){
            confirmPasswordError.value = "Field should not be null"
        }
        if(confirmPassword.value != password.value){
            confirmPasswordError.value = "Both passwords do not match."
        }

    }

}