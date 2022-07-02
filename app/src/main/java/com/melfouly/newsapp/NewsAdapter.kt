package com.melfouly.newsapp

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintAttribute
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melfouly.newsapp.databinding.ListItemBinding

class NewsAdapter(private val activity: Activity, private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(ListItemBinding.inflate(activity.layoutInflater, parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.tv.text = articles[position].title

        val image = articles[position].urlToImage
        if (image != null)
            Glide.with(activity)
                .load(image)
                .into(holder.binding.iv)
        else {
            holder.binding.iv.setImageResource(R.drawable.ic_baseline_broken_image_24)
        }

        holder.binding.root.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, articles[position].url.toUri())
            activity.startActivity(i)
        }
    }

    override fun getItemCount(): Int = articles.size

}