package edu.uw.sunny121.langxpert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uw.sunny121.langxpert.databinding.ActivityItemVocabListsBinding
import edu.uw.sunny121.langxpert.model.VocabList

class VocabListAdapter(private var listOfVocabLists: List<VocabList>) : RecyclerView.Adapter<VocabListAdapter.VocabListViewHolder>() {


//    var onSongClickListner : (position : Int, song : Song) -> Unit = {_, _ ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabListViewHolder {
        val binding = ActivityItemVocabListsBinding.inflate(LayoutInflater.from(parent.context))

        return VocabListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocabListViewHolder, position: Int) {
        val vocabList = listOfVocabLists[position]

        with(holder.binding) {
            tvVocabListTitle.text = vocabList.title
//            itemRoot.setOnClickListener {
//                onSongClickListner(position, song)
//            }
        }
    }


    override fun getItemCount(): Int = listOfVocabLists.size

    fun updateSong(newListOfSong: List<VocabList>) {
        this.listOfVocabLists = newListOfSong

        notifyDataSetChanged()
    }

    class VocabListViewHolder(val binding: ActivityItemVocabListsBinding) : RecyclerView.ViewHolder(binding.root)
}