package com.sachith.smartremainder.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Context
import androidx.compose.ui.platform.setContent
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.sachith.authpreflibrary.AuthPreference
import com.sachith.authpreflibrary.Headers
import com.sachith.smartremainder.composables.loginPage
import java.util.concurrent.TimeUnit

class ConnectActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var storedVerificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loginPage()
        }

        //Setup UI
        init()
    }

    //<editor-fold desc="Private Methods">
    //Setup UI
    private fun init(){

        //Hide status bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //Check user state
        if(AuthPreference.getUserState(this)){
          openHomeActivity()
        }
    }

    //Open Home Activity
    private fun openHomeActivity(){
        startActivity(Intent(this@ConnectActivity,HomeActivity::class.java))
        finish()
    }

    //Get OTP
    fun getOTP(context: Context, number: String){
        firebaseAuth = FirebaseAuth.getInstance()

        //Callbacks
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(context,"Auth Failed",Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                storedVerificationId = verificationId
                resendToken = token

                val headers = ArrayList<Headers>()
                headers.add(Headers("storedVerificationId", storedVerificationId))
                AuthPreference.saveUserHeaders(context, headers)
                Toast.makeText(context,"Mobile OTP Sent",Toast.LENGTH_LONG).show()
            }
        }

        //Send OTP
        PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+94$number")
            .setTimeout(120, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(callbacks)
            .build())
    }

    //Connect user
    fun connectUser(context: Context, otp: String){
        val headers = ArrayList<String>()
        headers.add("storedVerificationId")
        val credential = PhoneAuthProvider.getCredential(AuthPreference.getUserHeaders(context,headers)[0].value, otp)
        signInWithPhoneAuthCredential(credential, context)
    }

    //Firebase sign in
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, context: Context) {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    AuthPreference.authUserdata(context, 1,true)
                    context.startActivity(Intent(context,HomeActivity::class.java))
                } else {
                    Toast.makeText(context,"Auth Failed",Toast.LENGTH_LONG).show()
                }
            }
    }

    //</editor-fold>

}




