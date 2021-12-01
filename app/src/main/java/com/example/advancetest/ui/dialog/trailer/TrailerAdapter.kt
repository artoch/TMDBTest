package com.example.advancetest.ui.dialog.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.databinding.ItemTrailerBinding

class TrailerAdapter (
    private val items: List<MovieTrailersDomain>,
    private val action: (MovieTrailersDomain) -> Unit
) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], action)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val itemBinding: ItemTrailerBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: MovieTrailersDomain, action: (MovieTrailersDomain) -> Unit? = { }) {
            itemBinding.apply {
                tvTitle.text = item.name
                cvButton.setOnClickListener {
                    action.invoke(item)
                }
            }
        }
    }
}