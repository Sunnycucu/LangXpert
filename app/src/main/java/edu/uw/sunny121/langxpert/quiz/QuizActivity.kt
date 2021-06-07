package edu.uw.sunny121.langxpert.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import edu.uw.sunny121.langxpert.R
import edu.uw.sunny121.langxpert.application.VocabListApplication
import edu.uw.sunny121.langxpert.databinding.ActivityQuizBinding
import edu.uw.sunny121.langxpert.model.VocabList
import edu.uw.sunny121.langxpert.navigateToVocabListActivity
import kotlin.random.Random

fun navigateToQuizActivity(context: Context, position : Int) = with(context) {
    val intent = Intent(this, QuizActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("position", position)
        }
        putExtras(bundle)
    }
    startActivity(intent)

}
class QuizActivity : AppCompatActivity() {


    private lateinit var binding : ActivityQuizBinding
    lateinit var vocabListApp : VocabListApplication
    var questionNum : Int = 1
    var randNum : Int = 0
    var pickedInt: MutableSet<Int> = mutableSetOf()
    var numOfTrials : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickedInt.clear()
        numOfTrials = 0
        randNum = 0
        questionNum = 1

        vocabListApp = this.applicationContext as VocabListApplication
        binding = ActivityQuizBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {
            val position: Int = intent.getIntExtra("position", 0)
            val vocabList : VocabList = vocabListApp.allVocabLists[position]
            val lengthOfList : Int = vocabList.vocabs?.size ?: 0
            btnCheckAnswer.visibility = View.GONE
            tvQuestionCount.text = "$questionNum/$lengthOfList\nQuestions"
            randNum = Random.nextInt(0, lengthOfList)
            pickedInt.add(randNum)
            tvVocabCard.text = vocabList.vocabs?.get(randNum)?.word
            etAnswer.setOnClickListener {
                etAnswer.text.clear()
            }
            btnCheckAnswer.setOnClickListener {
                etAnswer.setText(vocabList.vocabs?.get(randNum)?.definition)
            }

            ibHomeButton.setOnClickListener {
                navigateToVocabListActivity(this@QuizActivity)
            }


            btnSubmit.setOnClickListener {
                if(questionNum == lengthOfList && btnSubmit.text == "Next") {
                    btnSubmit.text = "view result"
                } else if(questionNum == lengthOfList && btnSubmit.text == "view result") {
                    navigateToQuizResultViewActivity(this@QuizActivity, lengthOfList, numOfTrials, position)
                }else {
                    if(btnSubmit.text == "Submit") {
                        if(vocabList.vocabs?.get(randNum)?.definition == etAnswer.text.toString()) { //Correct answer
                            btnCheckAnswer.visibility = View.GONE
                            tvQuizInfo.text = "You are correct!"
                            tvQuizInfo.setTextColor(0xff519c54.toInt())
                            btnSubmit.text = "Next"
                        } else {
                            tvQuizInfo.text = "Please try again"
                            btnCheckAnswer.visibility = View.VISIBLE
                        }
                        numOfTrials += 1
                        Log.i("sunny", "$numOfTrials")
                    } else { // when next
                        btnSubmit.text = "Submit"
                        etAnswer.setText("")
                        tvQuizInfo.text = "Type the definition"
                        tvQuizInfo.setTextColor(0xff000000.toInt())
                        questionNum += 1
                        tvQuestionCount.text="$questionNum/$lengthOfList\nQuestions"

                        while(pickedInt.contains(randNum)) {
                            randNum = Random.nextInt(0, lengthOfList)
                            Log.i("sunny", "randNum: $randNum")
                        }
                        pickedInt.add(randNum)
                        tvVocabCard.text = vocabList.vocabs?.get(randNum)?.word
                    }

                }
            }
        }

    }
}