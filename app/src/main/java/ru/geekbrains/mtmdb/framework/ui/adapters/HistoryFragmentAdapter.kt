package ru.geekbrains.mtmdb.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.mtmdb.databinding.FragmentHistoryRecyclerItemBinding
import ru.geekbrains.mtmdb.framework.ui.history_fragment.HistoryFragment
import ru.geekbrains.mtmdb.model.entities.History

class HistoryFragmentAdapter(private var itemClickListener: HistoryFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<HistoryFragmentAdapter.HistoryViewHolder>() {
    private var data: List<History> = listOf()

    fun setData(data: List<History>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        FragmentHistoryRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class HistoryViewHolder(private val binding: FragmentHistoryRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: History) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                movieTitle.text = data.movie_title
                time.text = data.time
                root.setOnClickListener { itemClickListener?.onItemViewClick(data.movie_id) }
            }
        }
    }
}