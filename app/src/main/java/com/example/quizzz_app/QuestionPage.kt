package com.example.quizzz_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible


class QuestionPage : AppCompatActivity() {
    var option: Int=-1
    var score=0;
    lateinit var usernamegot : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val it : Intent=intent
        usernamegot=it.getStringExtra("Username").toString()
        val questionlist = Constants.getQuestions()
        val totlen = questionlist.size
        val pb:ProgressBar=findViewById(R.id.progresspanel)
        pb.max=totlen-1
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
            findViewById<Button>(R.id.submitbtn).isEnabled=false
            findViewById<TextView>(R.id.o1).isEnabled=false
            findViewById<TextView>(R.id.o2).isEnabled=false
            findViewById<TextView>(R.id.o3).isEnabled=false
            findViewById<TextView>(R.id.o4).isEnabled=false
            if(onSubmit(questionlist,totlen,qnum)) {
                answerdisplay(questionlist[qnum].correctans)
                Handler(Looper.getMainLooper()).postDelayed({
                qnum++
                    pb.progress=qnum
                currpos++
                ask(questionlist, currpos, qnum, totlen)
                    findViewById<ImageView>(R.id.anssymbol).visibility=View.GONE
                default()
                    findViewById<Button>(R.id.submitbtn).isEnabled=true
                    findViewById<TextView>(R.id.o1).isEnabled=true
                    findViewById<TextView>(R.id.o2).isEnabled=true
                    findViewById<TextView>(R.id.o3).isEnabled=true
                    findViewById<TextView>(R.id.o4).isEnabled=true
                option=-1 },1500)
            }
            else{
                findViewById<Button>(R.id.submitbtn).isEnabled=true
                findViewById<TextView>(R.id.o1).isEnabled=true
                findViewById<TextView>(R.id.o2).isEnabled=true
                findViewById<TextView>(R.id.o3).isEnabled=true
                findViewById<TextView>(R.id.o4).isEnabled=true
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
                answerSymbol(true)
                score++
            }
            else{
                answerSymbol(false)
            }
            answerdisplay(questionlist[qnum].correctans)
            Handler(Looper.getMainLooper()).postDelayed({
                val inte :Intent=Intent(this,ResultPage::class.java)
                inte.putExtra("Username",usernamegot)
                inte.putExtra("Score",score)
                inte.putExtra("total",totlen)
                startActivity(inte)
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
                answerSymbol(true)
                score++
            }
            else{
                answerSymbol(false)
            }
            return true;
        }
    }

    fun answerSymbol(ans: Boolean)
    {
        val img= findViewById<ImageView>(R.id.anssymbol)
        if(ans)
        {
            img.setImageResource(R.drawable.tick)
        }
        else{
            img.setImageResource(R.drawable.cross)
        }
        img.visibility=View.VISIBLE
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