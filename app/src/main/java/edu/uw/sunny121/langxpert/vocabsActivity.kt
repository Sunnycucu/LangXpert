package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import edu.uw.sunny121.langxpert.adapter.VocabWordAdapter
import edu.uw.sunny121.langxpert.databinding.ActivityVocabsBinding
import edu.uw.sunny121.langxpert.model.VocabList


fun navigateToVocabActivity(context: Context, position: Int) = with(context) {
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
            rvVocabs.adapter = adapter
            //rvVocabs.addItemDecoration(DividerItemDecoration(this@VocabsActivity, DividerItemDecoration.VERTICAL))







            if (vocabList != null) {
                tvVocabListTitle.text = vocabList.title
            }
            btnAddNewWord.setOnClickListener {
                navigateToAddVocabActivity(this@VocabsActivity, position)
            }



        }
    }

}