package edu.uw.sunny121.langxpert.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uw.sunny121.langxpert.databinding.ActivityItemVocabListsBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVoacbCardsBinding
import edu.uw.sunny121.langxpert.databinding.ActivityVocabsBinding
import edu.uw.sunny121.langxpert.model.VocabList
import edu.uw.sunny121.langxpert.model.VocabWord

class VocabWordAdapter(private var listOfVocabWords: List<VocabWord>) : RecyclerView.Adapter<VocabWordAdapter.VocabWordViewHolder>(){

    var onVocabWordClickListner : (position : Int, vocabCard : VocabWord) -> Unit = {_, _ ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabWordViewHolder {
        val binding = ActivityVoacbCardsBinding.inflate(LayoutInflater.from(parent.context))

        return VocabWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocabWordViewHolder, position: Int) {
        val vocabWord = listOfVocabWords[position]

        with(holder.binding) {
            tvVocabCardWord.text = vocabWord.word
            vocabWordRoot.setOnClickListener {
                onVocabWordClickListner(position, vocabWord)
            }
        }
    }


    override fun getItemCount(): Int = listOfVocabWords.size

    fun updateVocabWord(newWordsfList: List<VocabWord>) {
        this.listOfVocabWords = newWordsfList

        notifyDataSetChanged()
    }

    class VocabWordViewHolder(val binding: ActivityVoacbCardsBinding) : RecyclerView.ViewHolder(binding.root)

}