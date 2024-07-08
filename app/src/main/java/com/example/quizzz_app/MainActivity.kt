package com.example.quizzz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.quizzz_app.databinding.ActivityMainBinding

lateinit var Binding:ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
    }
    lateinit var userName:String
    fun onclick(view: View)
    {
        if(Binding.namebox.text.toString().isEmpty()) {
            Toast.makeText(this, "Name Cant Be Empty", Toast.LENGTH_SHORT).show()
        }
        else{
            userName=Binding.namebox.text.toString()
            Toast.makeText(this, "Hello $userName!!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,QuestionPage::class.java))
            finish()
        }
    }
}