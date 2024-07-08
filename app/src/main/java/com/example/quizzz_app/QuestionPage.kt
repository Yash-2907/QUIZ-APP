package com.example.quizzz_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


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
        default()
        findViewById<TextView>(R.id.questionpanel).text=questionlist[0].question
        findViewById<ImageView>(R.id.imagepanel).setImageResource(questionlist[0].image)
        findViewById<TextView>(R.id.pointpanel).text=(qnum+1).toString()+"/"+totlen.toString()
        findViewById<TextView>(R.id.o1).text=questionlist[0].option1
        findViewById<TextView>(R.id.o2).text=questionlist[0].option2
        findViewById<TextView>(R.id.o3).text=questionlist[0].option3
        findViewById<TextView>(R.id.o4).text=questionlist[0].option4
        findViewById<Button>(R.id.submitbtn).setOnClickListener {
            if(onSubmit(questionlist,totlen,qnum)) {
                answerdisplay(questionlist[qnum].correctans)
                Handler(Looper.getMainLooper()).postDelayed({
                qnum++;
                currpos++
                ask(questionlist, currpos, qnum, totlen)
                default()
                option=-1},2000)
            }
        }
    }

    fun answerdisplay(ans:Int)
    {
        if(ans==1)
        {
            findViewById<Button>(R.id.o1).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttoncorrect)
        }
        else
        {
            findViewById<Button>(R.id.o1).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttonincorrect)
        }
        if(ans==2)
        {
            findViewById<Button>(R.id.o2).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttoncorrect)
        }
        else
        {
            findViewById<Button>(R.id.o2).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttonincorrect)
        }
        if(ans==3)
        {
            findViewById<Button>(R.id.o3).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttoncorrect)
        }
        else
        {
            findViewById<Button>(R.id.o3).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttonincorrect)
        }
        if(ans==4)
        {
            findViewById<Button>(R.id.o4).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttoncorrect)
        }
        else
        {
            findViewById<Button>(R.id.o4).background=ContextCompat.getDrawable(this,R.drawable.sqrbuttonincorrect)
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
            Toast.makeText(this, "THANKS FOR PLAYING YOUR SCORE :- $score", Toast.LENGTH_LONG)
                .show()
            answerdisplay(questionlist[qnum].correctans)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
                finish() },1000)
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
            R.id.o1 -> {option=1
                selectedoptn(findViewById(R.id.o1))}
            R.id.o2 -> {option=2
            selectedoptn(findViewById(R.id.o2))}
            R.id.o3 -> {option=3
            selectedoptn(findViewById(R.id.o3))}
            R.id.o4 -> {option=4
            selectedoptn(findViewById(R.id.o4))}
        }
    }

    fun selectedoptn(currselect: View)
    {
        default()
        currselect.background=ContextCompat.getDrawable(this,R.drawable.sqrbuttonselected)
    }

    fun default()
    {
        findViewById<Button>(R.id.o1).background=ContextCompat.getDrawable(this,R.drawable.sqrbutton)
        findViewById<Button>(R.id.o2).background=ContextCompat.getDrawable(this,R.drawable.sqrbutton)
        findViewById<Button>(R.id.o3).background=ContextCompat.getDrawable(this,R.drawable.sqrbutton)
        findViewById<Button>(R.id.o4).background=ContextCompat.getDrawable(this,R.drawable.sqrbutton)
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