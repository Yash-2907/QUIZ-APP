package com.example.quizzz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.quizzz_app.databinding.ActivityMainBinding

lateinit var Binding:ActivityMainBinding
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    lateinit var userName:String
    fun onclick(view: View)
    {
        if(Binding.namebox.text.toString().isEmpty()) {
            Toast.makeText(this, "Name Cant Be Empty", Toast.LENGTH_SHORT).show()
        }
        else{
            userName=Binding.namebox.text.toString()
            Toast.makeText(this, "Hello $userName!!", Toast.LENGTH_SHORT).show()
            val it : Intent = Intent(this,QuestionPage::class.java)
            it.putExtra("Username",userName)
            startActivity(it)
            finish()
        }
    }
}