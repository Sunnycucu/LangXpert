package edu.uw.sunny121.langxpert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.langxpert.adapter.VocabListAdapter
import edu.uw.sunny121.langxpert.adapter.VocabWordAdapter
import edu.uw.sunny121.langxpert.databinding.ActivityVoacbCardsBinding
import edu.uw.sunny121.langxpert.model.VocabList




class VoacbCards : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voacb_cards)
    }
}