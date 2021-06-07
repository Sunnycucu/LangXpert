package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.uw.sunny121.langxpert.application.VocabListApplication
import edu.uw.sunny121.langxpert.databinding.ActivityVocabDetailViewBinding
import edu.uw.sunny121.langxpert.model.VocabList
import edu.uw.sunny121.langxpert.model.VocabWord


fun navigateToVocabDetailViewActivity(context: Context, positionList : Int, positionWord:Int) = with(context) {
    val intent = Intent(this, VocabDetailViewActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("positionList", positionList)
            putInt("positionWord", positionWord)
        }
        putExtras(bundle)
    }
    startActivity(intent)

}


class VocabDetailViewActivity : AppCompatActivity() {



    private lateinit var binding : ActivityVocabDetailViewBinding
    lateinit var vocabListApp : VocabListApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vocabListApp = this.applicationContext as VocabListApplication
        binding = ActivityVocabDetailViewBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            etDetailES.visibility = View.GONE
            etDetailSY.visibility = View.GONE
            etDetailDef.visibility = View.GONE
            etDetailAT.visibility =  View.GONE

            val positionList: Int = intent.getIntExtra("positionList", 0)
            val positionWord: Int = intent.getIntExtra("positionWord", 0)
            val vocabList : VocabList = vocabListApp.allVocabLists[positionList]
            val vocabWord : VocabWord? = vocabList.vocabs?.get(positionWord)

            etDetailDef.setText(vocabWord?.definition)
            etDetailES.setText(vocabWord?.exampleSentence)
            etDetailSY.setText(vocabWord?.synonym)
            etDetailAT.setText(vocabWord?.antonym)

            tvDetailDef.text = vocabWord?.definition
            tvDetailES.text = vocabWord?.exampleSentence
            tvDetailSY.text = vocabWord?.synonym
            tvDetailAT.text = vocabWord?.antonym

            btnDone.setOnClickListener {
                if(btnDone.text.toString() == "Done") {
                    tvDetailDef.text = etDetailDef.text.toString()
                    tvDetailES.text = etDetailES.text.toString()
                    tvDetailSY.text = etDetailSY.text.toString()
                    tvDetailAT.text = etDetailAT.text.toString()

                    vocabListApp.allVocabLists[positionList].vocabs?.get(positionWord)?.definition = tvDetailDef.text.toString()
                    vocabListApp.allVocabLists[positionList].vocabs?.get(positionWord)?.exampleSentence = tvDetailES.text.toString()
                    vocabListApp.allVocabLists[positionList].vocabs?.get(positionWord)?.synonym = tvDetailSY.text.toString()
                    vocabListApp.allVocabLists[positionList].vocabs?.get(positionWord)?.antonym = tvDetailAT.text.toString()

                    tvDetailDef.visibility = View.VISIBLE
                    tvDetailES.visibility = View.VISIBLE
                    tvDetailSY.visibility = View.VISIBLE
                    tvDetailAT.visibility =  View.VISIBLE

                    etDetailES.visibility = View.GONE
                    etDetailSY.visibility = View.GONE
                    etDetailDef.visibility = View.GONE
                    etDetailAT.visibility =  View.GONE
                    btnDone.text = "Edit"
                } else { // btnDone.text.toString() == "Edit"
                    tvDetailDef.visibility = View.GONE
                    tvDetailES.visibility = View.GONE
                    tvDetailSY.visibility = View.GONE
                    tvDetailAT.visibility =  View.GONE

                    etDetailES.visibility = View.VISIBLE
                    etDetailSY.visibility = View.VISIBLE
                    etDetailDef.visibility = View.VISIBLE
                    etDetailAT.visibility =  View.VISIBLE

                    btnDone.text = "Done"

                }

            }
        }
    }
}