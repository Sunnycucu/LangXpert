package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.uw.sunny121.langxpert.adapter.VocabWordAdapter
import edu.uw.sunny121.langxpert.databinding.ActivityVoacbCardsBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVocabsBinding
import edu.uw.sunny121.langxpert.model.VocabList


fun navigateToVocabActivity(context: Context, position : Int) = with(context) {
    val intent = Intent(this, VocabsActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("position", position)
        }
        putExtras(bundle)
    }
    startActivity(intent)

}
class VocabsActivity : AppCompatActivity() {



    private lateinit var binding : ActivityVocabsBinding
    lateinit var vocabListApp : VocabListApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vocabListApp = this.applicationContext as VocabListApplication
        binding = ActivityVocabsBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {
            val position: Int = intent.getIntExtra("position", 0)
            val vocabList : VocabList = vocabListApp.allVocabLists[position]
            val adapter = vocabList.vocabs?.let { VocabWordAdapter(it) }
            if (vocabList != null) {
                tvVocabListTitle.text = vocabList.title
            }
            btnAddNewWord.setOnClickListener {
                navigateToAddVocabActivity(this@VocabsActivity)
            }


        }
    }

}