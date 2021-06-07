package edu.uw.sunny121.langxpert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.sunny121.langxpert.application.VocabListApplication
import edu.uw.sunny121.langxpert.databinding.ActivityMainBinding





class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var vocabListApp : VocabListApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vocabListApp = this.applicationContext as VocabListApplication

        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            titlePage.setOnClickListener{
                if(vocabListApp.initiated) {
                    navigateToVocabListActivity(this@MainActivity)
                } else{
                    navigateToStartingScreenActivity(this@MainActivity)
                }
            }
        }
    }
}