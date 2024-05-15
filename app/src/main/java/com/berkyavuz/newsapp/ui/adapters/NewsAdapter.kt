package com.berkyavuz.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.berkyavuz.newsapp.databinding.ItemNewsBinding
import com.berkyavuz.newsapp.model.response.Article
import com.berkyavuz.newsapp.ui.fragments.NewsFragmentDirections
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private val articles = mutableListOf<Article>()

    fun submitList(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    inner class ArticleViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewSummary.text = article.description
            Glide.with(binding.root.context).load(article.urlToImage).into(binding.imageViewNews)

            binding.root.setOnClickListener { view ->
                val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article)
                view.findNavController().navigate(action)
            }
        }
    }
}
