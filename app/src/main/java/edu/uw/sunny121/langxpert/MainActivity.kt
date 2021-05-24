package edu.uw.sunny121.langxpert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.sunny121.langxpert.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            titlePage.setOnClickListener{
                navigateToVocabListActivity(this@MainActivity)
            }
        }
    }
}