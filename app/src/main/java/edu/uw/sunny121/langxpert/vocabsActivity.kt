package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import edu.uw.sunny121.langxpert.adapter.VocabWordAdapter
import edu.uw.sunny121.langxpert.adapter.VocabWordListViewAdapter
import edu.uw.sunny121.langxpert.application.VocabListApplication
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
            val positionList: Int = intent.getIntExtra("position", 0)
            val vocabList : VocabList = vocabListApp.allVocabLists[positionList]

            val adapterCardView = vocabList.vocabs?.let { VocabWordAdapter(it) }
            val adapterListView = vocabList.vocabs?.let {VocabWordListViewAdapter(it)}

            rvVocabs.addItemDecoration(DividerItemDecoration(this@VocabsActivity,
                    DividerItemDecoration.VERTICAL))

            rvVocabs.adapter = adapterCardView
            if (adapterCardView != null) {
                adapterCardView.onVocabWordClickListner = {position, vocabList ->

                }
            }




            switchVocab.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    // The toggle is enabled
                    switchVocab.text = "List mode"
                    rvVocabs.adapter = adapterListView
                    if (adapterListView != null) {
                        adapterListView.onVocabWordListViewClickListner = {position, vocabList ->
                            navigateToVocabDetailViewActivity(this@VocabsActivity, positionList, position)
                        }
                    }


                } else {
                    // The toggle is disable
                    switchVocab.text = "Card mode"
                    rvVocabs.adapter = adapterCardView
                    if (adapterCardView != null) {
                        adapterCardView.onVocabWordClickListner = {position, vocabList ->

                        }
                    }

                }
            })








            if (vocabList != null) {
                tvVocabListTitle.text = vocabList.title
            }
            btnAddNewWord.setOnClickListener {
                navigateToAddVocabActivity(this@VocabsActivity, positionList)
            }



        }
    }

}