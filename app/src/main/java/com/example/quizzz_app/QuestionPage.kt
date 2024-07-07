package com.example.quizzz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import com.google.android.material.color.utilities.Score

class QuestionPage : AppCompatActivity() {
    var option: Int=-1
    var score=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)
        val questionlist = Constants.getQuestions()
        val totlen = questionlist.size
        var qnum=0;
        var currpos=0;
        findViewById<TextView>(R.id.questionpanel).text=questionlist[0].question
        findViewById<ImageView>(R.id.imagepanel).setImageResource(questionlist[0].image)
        findViewById<TextView>(R.id.pointpanel).text=(qnum+1).toString()+"/"+totlen.toString()
        findViewById<TextView>(R.id.o1).text=questionlist[0].option1
        findViewById<TextView>(R.id.o2).text=questionlist[0].option2
        findViewById<TextView>(R.id.o3).text=questionlist[0].option3
        findViewById<TextView>(R.id.o4).text=questionlist[0].option4
        findViewById<Button>(R.id.submitbtn).setOnClickListener {
            if(onSubmit(questionlist,totlen,qnum)) {
                qnum++;
                currpos++
                ask(questionlist, currpos, qnum, totlen)
                option=-1
            }
        }
    }

    fun onSubmit(questionlist: ArrayList<questionsdataset>,totlen: Int,qnum: Int):Boolean
    {
        if((totlen-1)==qnum) {
            if(questionlist[qnum].correctans==option)
            {
                Toast.makeText(this, "YAY!! CORRECT ANS", Toast.LENGTH_SHORT).show()
                score++
            }
            else{
                Toast.makeText(this, "WOOPS!! WRONG ANS", Toast.LENGTH_SHORT).show()
            }
            Thread.sleep(1000)
            Toast.makeText(this, "THANKS FOR PLAYING YOUR SCORE :- $score", Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            return false;
        }
        else if(option==-1){
            Toast.makeText(this, "OOPS!! NO OPTION SELECTED", Toast.LENGTH_SHORT).show()
            return false;
        }
        else{
            if(questionlist[qnum].correctans==option)
            {
                Toast.makeText(this, "YAY!! CORRECT ANS", Toast.LENGTH_SHORT).show()
                score++
            }
            else{
                Toast.makeText(this, "WOOPS!! WRONG ANS", Toast.LENGTH_SHORT).show()
            }
            return true;
        }
    }

    fun optionselect(view:View)
    {
        when(view.id)
        {
            R.id.o1 -> {option=1}
            R.id.o2 -> {option=2}
            R.id.o3 -> {option=3}
            R.id.o4 -> {option=4}
        }
    }
    fun ask(questionlist : ArrayList<questionsdataset>,currpos:Int,qnum:Int,totlen:Int){
            findViewById<TextView>(R.id.questionpanel).text=questionlist[currpos].question
            findViewById<ImageView>(R.id.imagepanel).setImageResource(questionlist[currpos].image)
            findViewById<TextView>(R.id.o1).text=questionlist[currpos].option1
            findViewById<TextView>(R.id.o2).text=questionlist[currpos].option2
            findViewById<TextView>(R.id.o3).text=questionlist[currpos].option3
            findViewById<TextView>(R.id.o4).text=questionlist[currpos].option4
            findViewById<TextView>(R.id.pointpanel).text=(qnum+1).toString()+"/"+totlen.toString()
    }
}