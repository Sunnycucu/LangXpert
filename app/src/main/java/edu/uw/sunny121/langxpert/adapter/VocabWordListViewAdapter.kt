package edu.uw.sunny121.langxpert.adapter


import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.uw.sunny121.langxpert.MainActivity
import edu.uw.sunny121.langxpert.databinding.ActivityItemVocabListsBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVoacbCardsBinding
import edu.uw.sunny121.langxpert.model.VocabWord
import java.security.AccessController.getContext

class VocabWordListViewAdapter(private var listOfVocabWords: List<VocabWord>) : RecyclerView.Adapter<VocabWordListViewAdapter.VocabWordViewHolder>(){

    var onVocabWordListViewClickListner : (position: Int, vocabCard: VocabWord) -> Unit = { _, _ ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabWordViewHolder {
        val binding = ActivityItemVocabListsBinding.inflate(LayoutInflater.from(parent.context))

        return VocabWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocabWordViewHolder, position: Int) {
        val vocabWord = listOfVocabWords[position]
        with(holder.binding) {
            tvVocabListTitle.text = vocabWord.word
            vocabListRoot.setOnClickListener {
                onVocabWordListViewClickListner(position, vocabWord)

            }
        }
    }


    override fun getItemCount(): Int = listOfVocabWords.size

    fun updateVocabWordListView(newWordsfList: List<VocabWord>?) {
        if (newWordsfList != null) {
            this.listOfVocabWords = newWordsfList
        }

        notifyDataSetChanged()
    }



    class VocabWordViewHolder(val binding: ActivityItemVocabListsBinding) : RecyclerView.ViewHolder(binding.root)

}