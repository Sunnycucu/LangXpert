package edu.uw.sunny121.langxpert.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class VocabList (
    var vocabs : List<VocabWord>?,
    var title : String
) : Parcelable