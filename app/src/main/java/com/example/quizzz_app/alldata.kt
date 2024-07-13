package com.example.quizzz_app

object Constants{
    fun getQuestions():ArrayList<questionsdataset>
    {
        val allquestions = ArrayList<questionsdataset>()
        val q1 = questionsdataset(1,"WHAT IS THE SHOWN ANIMAL",R.drawable.ea3de7ebb7a997d1b5a4aa02cae85d13,"LION","GOAT","DUCK","COW",2)
        val q2 = questionsdataset(2,"WHAT IS THE SHOWN ANIMAL",R.drawable.f0ef886fffaf85b72ab35bfe552a90d1,"CHEETAH","LION","TIGER","JAGUAR",3)
        val q3 = questionsdataset(3,"WHAT IS THE SHOWN ANIMAL",R.drawable.duck,"CHICKEN","FLAMINGO","SWAN","DUCK",4)
        val q4 = questionsdataset(4,"WHAT IS THE SHOWN ANIMAL",R.drawable.cat,"RACOON","MONGOOSE","CAT","TIGER",3)
        val q5 = questionsdataset(5,"WHAT IS THE SHOWN ANIMAL",R.drawable.cow,"COW","BUFFALO","REINDEER","WHITE ELEPHANT",1)
        val q6 = questionsdataset(6,"WHAT IS THE SHOWN ANIMAL",R.drawable.elephant,"CAMEL","MAMMOTH","DINOSAUR","ELEPHANT",4)
        allquestions.add(q1)
        allquestions.add(q2)
        allquestions.add(q3)
        allquestions.add(q4)
        allquestions.add(q5)
        allquestions.add(q6)
        return allquestions
    }
}