package edu.uw.sunny121.langxpert.application

import android.app.Application
import edu.uw.sunny121.langxpert.model.VocabList
import edu.uw.sunny121.langxpert.model.VocabWord
import edu.uw.sunny121.langxpert.repository.DataRepository

class VocabListApplication : Application() {

    lateinit var allVocabLists : MutableList<VocabList>
    var initiated : Boolean = false
    lateinit var dataRepository : DataRepository

    override fun onCreate() {
        super.onCreate()
        allVocabLists = mutableListOf<VocabList>()
        dataRepository = DataRepository()
    }
}