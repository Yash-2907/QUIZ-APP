package com.example.quizzz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class ResultPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val inte:Intent=intent
        findViewById<TextView>(R.id.usernamepanel).text="Hey "+inte.getStringExtra("Username").toString()
                findViewById<TextView>(R.id.finalresultpanel).text="You have scored ${inte.getIntExtra("Score",0)} out of ${inte.getIntExtra("total",0)} points"
        findViewById<Button>(R.id.finishbtn).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}