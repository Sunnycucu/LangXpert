package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.uw.sunny121.langxpert.databinding.ActivityAddVocabBinding
import edu.uw.sunny121.langxpert.databinding.ActivityMainBinding
import edu.uw.sunny121.langxpert.databinding.ActivityStartingScreenBinding
import edu.uw.sunny121.langxpert.model.VocabList
import kotlinx.coroutines.launch


fun navigateToStartingScreenActivity(context: Context) = with(context) {
    val intent = Intent(this, startingScreenActivity::class.java).apply {

    }
    startActivity(intent)

}
class startingScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStartingScreenBinding
    lateinit var vocabListApp : VocabListApplication
    private val dataRepository by lazy { vocabListApp.dataRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vocabListApp = this.applicationContext as VocabListApplication
        vocabListApp.initiated = true
        binding =
            ActivityStartingScreenBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {
            cvStartNew.setOnClickListener {
                navigateToVocabListActivity(this@startingScreenActivity)
            }
            cvChooseJson.setOnClickListener {
                lifecycleScope.launch {
                    runCatching {
                        val exampleVocabList: VocabList = dataRepository.getExampleVocabList()
                        vocabListApp.allVocabLists.add(exampleVocabList)
                    }.onFailure {

                        Toast.makeText(this@startingScreenActivity, "Error occurred when fetching the vocabList", Toast.LENGTH_SHORT).show()
                    }
                    navigateToVocabListActivity(this@startingScreenActivity)

                }

            }
        }
    }
}
