package com.nikhil.screenui

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScreenViewModel : ViewModel() {
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val confirmPassword = MutableLiveData<String>("")

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<String>()

    fun validateFields() {
        emailError.value = ""
        passwordError.value = ""
        confirmPasswordError.value = ""
        if (!email.value.isNullOrBlank()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.value as CharSequence).matches()) {
                emailError.value = "Enter correct Email"
            }
        } else
            emailError.value = "Email cannot be blank"
        if (!password.value.isNullOrBlank()) {
            if (password.value!!.length < 6) {
                passwordError.value = "Password should be at least 6 characters."
            }
        } else
            passwordError.value = "Password cannot be blank."
        if (!confirmPassword.value.isNullOrBlank()) {
            if (confirmPassword.value!!.length < 6) {
                confirmPasswordError.value = "Password should be at least 6 characters."
            } else if (confirmPassword.value != password.value) {
                confirmPasswordError.value = "Both passwords do not match."
            }
        } else
            confirmPasswordError.value = "Password cannot be blank."
    }

}