package edu.uw.sunny121.langxpert.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.uw.sunny121.langxpert.MainActivity
import edu.uw.sunny121.langxpert.databinding.ActivityVoacbCardsBinding
import edu.uw.sunny121.langxpert.model.VocabWord
import java.security.AccessController.getContext

class VocabWordAdapter(private var listOfVocabWords: List<VocabWord>) : RecyclerView.Adapter<VocabWordAdapter.VocabWordViewHolder>(){

    var onVocabWordClickListner : (position: Int, vocabCard: VocabWord) -> Unit = { _, _ ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabWordViewHolder {
        val binding = ActivityVoacbCardsBinding.inflate(LayoutInflater.from(parent.context))

        return VocabWordViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: VocabWordViewHolder, position: Int) {
        val vocabWord = listOfVocabWords[position]
        with(holder.binding) {
            tvVocabCardWord.text = vocabWord.word
            vocabWordRoot.setOnClickListener {
                onVocabWordClickListner(position, vocabWord)
                if(tvVocabCardWord.text.toString() == vocabWord.word) {
                    tvVocabCardWord.text = vocabWord.definition
                } else {
                    tvVocabCardWord.text = vocabWord.word


                }
            }
        }
    }


    override fun getItemCount(): Int = listOfVocabWords.size

    fun updateVocabWord(newWordsfList: List<VocabWord>?) {
        if (newWordsfList != null) {
            this.listOfVocabWords = newWordsfList
        }

        notifyDataSetChanged()
    }

    fun changeWordToDefinition(newWordsfList: List<VocabWord>?, position: Int) {

    }

    class VocabWordViewHolder(val binding: ActivityVoacbCardsBinding) : RecyclerView.ViewHolder(binding.root)

}