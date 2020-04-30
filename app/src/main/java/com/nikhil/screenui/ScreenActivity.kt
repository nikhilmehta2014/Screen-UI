package com.nikhil.screenui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nikhil.screenui.databinding.ActivityScreenBinding

class ScreenActivity : AppCompatActivity() {

    lateinit var activityScreenBinding: ActivityScreenBinding
    val viewModel: ScreenViewModel by lazy {
        ViewModelProvider(this, ScreenViewModelFactory()).get(ScreenViewModel::class.java)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        activityScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_screen)
        activityScreenBinding.viewModel = viewModel
        activityScreenBinding.lifecycleOwner = this

        //TODO: move this to ViewModel
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }

        viewModel.emailError.observe(this, Observer {
            activityScreenBinding.etEmailLayout.error = it
        })

        viewModel.passwordError.observe(this, Observer {
            activityScreenBinding.etPasswordLayout.error = it
        })

        viewModel.confirmPasswordError.observe(this, Observer {
            activityScreenBinding.etPasswordConfirmLayout.error = it
        })
    }

    fun showDialog(view: View) {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Welcome ${viewModel.email.value}")
            setPositiveButton("OK", positiveButtonClick)      //TODO: get from strings.xml
            show()
        }
    }

    private val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext, "OK", Toast.LENGTH_SHORT).show()     //TODO: get from strings.xml
    }

    fun validateFields(v:View){
        viewModel.validateFields()
//        showDialog(v)
    }



}
