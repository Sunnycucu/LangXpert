package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.sunny121.langxpert.databinding.ActivityAddVocabBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVocabListBinding


fun navigateToAddVocabActivity(context: Context) = with(context) {
    val intent = Intent(this, AddVocabActivity::class.java).apply {

    }
    startActivity(intent)

}
class AddVocabActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddVocabBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAddVocabBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {
            btnCancel.setOnClickListener {
                finish()
            }

        }
    }
}