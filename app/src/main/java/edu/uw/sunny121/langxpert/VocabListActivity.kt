package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import edu.uw.sunny121.langxpert.adapter.VocabListAdapter
import edu.uw.sunny121.langxpert.databinding.ActivityVocabListBinding
import edu.uw.sunny121.langxpert.model.VocabList
import java.security.AccessController.getContext


fun navigateToVocabListActivity(context: Context) = with(context) {
    val intent = Intent(this, VocabListActivity::class.java).apply {

    }
    startActivity(intent)

}
class VocabListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVocabListBinding
    lateinit var vocabListApp : VocabListApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vocabListApp = this.applicationContext as VocabListApplication
        var addedTitle : String = ""

        binding = ActivityVocabListBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {
            val adapter = VocabListAdapter(vocabListApp.allVocabLists)
            rvVocabLists.adapter = adapter
            adapter.onVocabListClickListner = { position, vocabList ->
                navigateToVocabActivity(this@VocabListActivity, position)
            }
            rvVocabLists.addItemDecoration(DividerItemDecoration(this@VocabListActivity,
                    DividerItemDecoration.VERTICAL))


            etAddName.visibility = View.GONE
            btnAdd.setOnClickListener {
                if(btnAdd.text.toString() == "add") {
                    etAddName.visibility = View.VISIBLE
                    btnAdd.text="Done"
                } else {
                    etAddName.visibility = View.GONE
                    btnAdd.text = "add"
                    addedTitle = etAddName.text.toString()
                    var newVocabList : VocabList = VocabList(
                            title = addedTitle,
                            vocabs = null
                    )
                    vocabListApp.allVocabLists.add(createVocabList(addedTitle))
                    //Log.i("TAG", vocabListApp.allVocabLists.toString())
                    adapter.updateVocabList(vocabListApp.allVocabLists)
                    etAddName.text.clear()
                }
            }


            switchMode.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    // The toggle is enabled
                    switchMode.text = "Quiz mode"
                } else {
                    // The toggle is disable
                    switchMode.text = "Study mode"
                }
            })




    }
}


    fun createVocabList(
            title: String
    ): VocabList {
        return VocabList(
                null,
                title
        )
    }
}