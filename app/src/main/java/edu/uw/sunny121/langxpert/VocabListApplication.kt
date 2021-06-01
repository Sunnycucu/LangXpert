package edu.uw.sunny121.langxpert

import android.app.Application
import edu.uw.sunny121.langxpert.model.VocabList
import edu.uw.sunny121.langxpert.model.VocabWord

class VocabListApplication : Application() {

    lateinit var allVocabLists : MutableList<VocabList>

    override fun onCreate() {
        super.onCreate()
        allVocabLists = mutableListOf<VocabList>()
//        val vl : VocabList = VocabList(
//                vocabs = null,
//            title = "vocablist1"
//            //vocabs = listOf<VocabWord>()
//        )
//        allVocabLists.add(vl)
    }
}