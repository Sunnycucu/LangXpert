package edu.uw.sunny121.langxpert.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VocabWord(
    var id : Int,
    var word: String,
    var definition : String,
    var exampleSentence : List<String>,
    var synonym : String,
    var antonym : String
): Parcelable