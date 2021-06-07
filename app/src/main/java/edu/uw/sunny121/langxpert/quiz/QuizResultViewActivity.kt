package edu.uw.sunny121.langxpert.quiz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.sunny121.langxpert.R
import edu.uw.sunny121.langxpert.application.VocabListApplication
import edu.uw.sunny121.langxpert.databinding.ActivityQuizBinding
import edu.uw.sunny121.langxpert.databinding.ActivityQuizResultViewBinding
import edu.uw.sunny121.langxpert.navigateToVocabListActivity


fun navigateToQuizResultViewActivity(context: Context, vocabListLength : Int, numOfTrials : Int, position : Int) = with(context) {
    val intent = Intent(this, QuizResultViewActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("vocabListLength", vocabListLength)
            putInt("numOfTrials", numOfTrials)
            putInt("position", position)
        }
        putExtras(bundle)
    }
    startActivity(intent)

}
class QuizResultViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQuizResultViewBinding
    lateinit var vocabListApp : VocabListApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultViewBinding.inflate(layoutInflater).apply{setContentView(root)}
        val vocabListLength: Int = intent.getIntExtra("vocabListLength", 0)
        val numOfTrials: Int = intent.getIntExtra("numOfTrials", 0)
        val position: Int = intent.getIntExtra("position", 0)
        with(binding) {
            btnCorrect.text = "Number of words : $vocabListLength\n Number of trials : $numOfTrials"
            if(numOfTrials == vocabListLength) {
                btnCorrect.setBackgroundColor(Color.GREEN)
            }
            ibHomeButton.setOnClickListener {
                navigateToVocabListActivity(this@QuizResultViewActivity)
            }
            btnRetake.setOnClickListener{
                navigateToQuizActivity(this@QuizResultViewActivity, position)
            }
        }
    }
}