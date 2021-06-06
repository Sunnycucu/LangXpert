package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.uw.sunny121.langxpert.databinding.ActivityQuizBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVocabDetailViewBinding
import kotlin.random.Random

fun navigateToQuizActivity(context: Context) = with(context) {
    val intent = Intent(this, QuizActivity::class.java).apply { }
    startActivity(intent)
}

class QuizActivity : AppCompatActivity() {
    lateinit var vocabListApp : VocabListApplication
    private lateinit var binding : ActivityQuizBinding
    var currQuesNo: Int = 0
    var totalQuestions: Int = 0
    private var numLists = vocabListApp.allVocabLists.size
    private val randomNumber = Random.nextInt(0, numLists)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        vocabListApp = this.applicationContext as VocabListApplication
        binding = ActivityQuizBinding.inflate(layoutInflater).apply{setContentView(root)}

        val listOfLists = vocabListApp.allVocabLists.get(randomNumber)
        val wordList = listOfLists.vocabs
        with(binding) {
            if (wordList != null) {
                totalQuestions = wordList.size
                //tvQuestionCount.text = totalQuestions.toString()
            }
            if (wordList != null) {
                for (i in wordList) {
                    currQuesNo++
                    val actualMeaning = i.definition
                    val enteredMeaning = etAnswer.text
                    tvQuestionCount.text = "${currQuesNo}/$totalQuestions Questions"
                    tvVocabCard.text = i.definition.toString()
                    if(actualMeaning.equals(enteredMeaning)) {
                        //Show correct Answer UI

                    } else {
                        //Show incorrect Answer UI

                    }
                }
            }
        }
    }
}