package org.d3ifcool3076.assess1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3ifcool3076.assess1.databinding.ItemBinding

class FilmAdapter (private var filmlist: ArrayList<Film>) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
        class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(film: Film) {
                Glide.with(binding.root)
                    .load(film.image)
                    .into(binding.imageView)

                with(binding) {
                    textView.text = film.name
                    //            imageView.setImageResource(food.image)
                }
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(filmlist[position])

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                ItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun getItemCount() = filmlist.size

        @SuppressLint("NotifyDataSetChanged")
        fun filtering(filter: ArrayList<Film>) {
            filmlist = filter
            notifyDataSetChanged()
        }
}