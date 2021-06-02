package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import edu.uw.sunny121.langxpert.adapter.VocabWordAdapter
import edu.uw.sunny121.langxpert.databinding.ActivityAddVocabBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVocabListBinding
import edu.uw.sunny121.langxpert.model.VocabList
import edu.uw.sunny121.langxpert.model.VocabWord


fun navigateToAddVocabActivity(context: Context, position : Int) = with(context) {
    val intent = Intent(this, AddVocabActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("position", position)
        }
        putExtras(bundle)
    }
    startActivity(intent)

}
class AddVocabActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddVocabBinding
    lateinit var vocabListApp : VocabListApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vocabListApp = this.applicationContext as VocabListApplication
        binding = ActivityAddVocabBinding.inflate(layoutInflater).apply{setContentView(root)}

        val position: Int = intent.getIntExtra("position", 0)
        if(vocabListApp.allVocabLists[position].vocabs == null) {
            vocabListApp.allVocabLists[position].vocabs =  mutableListOf<VocabWord>()
        }

        with(binding) {
            btnCancel.setOnClickListener {
                finish()
            }
            btnDone.setOnClickListener {

                if(etWord.text.toString() == ""|| etDef.text.toString() == "") {
                    Toast.makeText(this@AddVocabActivity,"word and definition is required", Toast.LENGTH_SHORT).show()
                } else {
                    val word : String = etWord.text.toString()
                    val def : String = etDef.text.toString()
                    var es : String = ""
                    var sy : String = ""
                    var at : String = ""
                    if(etES.text.toString() != "") {
                        es = etES.text.toString()
                    }
                    if(etSY.text.toString() != "") {
                        sy = etSY.text.toString()
                    }
                    if(etAT.text.toString() != "") {
                        at = etAT.text.toString()
                    }
                    vocabListApp.allVocabLists[position].vocabs = vocabListApp.allVocabLists[position].vocabs?.plus(createVocabWord(word, def, es, sy,at))

                }

                navigateToVocabActivity(this@AddVocabActivity, position)

               Log.i("hihi", vocabListApp.allVocabLists[position].vocabs?.get(0).toString())

            }

        }
    }

    fun createVocabWord(
        word: String,
        def : String,
        es : String,
        sy : String,
        at : String
    ): VocabWord {
        return VocabWord(
            word,
            def,
            es,
            sy,
            at
        )
    }
}