package edu.uw.sunny121.langxpert.repository

import edu.uw.sunny121.langxpert.model.VocabList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

class DataRepository {
    private val exampleVocabListService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ExampleVocabListService::class.java)

    suspend fun getExampleVocabList() : VocabList = exampleVocabListService.getExampleVocabList();

}
interface ExampleVocabListService{
    @GET("Sunnycucu/LangXpert/master/exampleVocabList.json")
    suspend fun getExampleVocabList() : VocabList
}