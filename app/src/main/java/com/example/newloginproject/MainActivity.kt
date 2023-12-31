package com.example.newloginproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newloginproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        binding.Register.setOnClickListener {
            val intent =Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.Login.setOnClickListener {
            if(checking()){
                val email= binding.Email.text.toString()
                val password=  binding.Password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            var intent =Intent(this,LoggedIn::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun checking():Boolean
    {
        if( binding.Email.text.toString().trim{it<=' '}.isNotEmpty()
            &&  binding.Password.text.toString().trim{it<=' '}.isNotEmpty())
        {
            return true
        }
        return false
    }
}