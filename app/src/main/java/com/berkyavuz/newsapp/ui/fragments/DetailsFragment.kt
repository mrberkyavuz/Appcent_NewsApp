package com.berkyavuz.newsapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.lifecycleScope
import com.berkyavuz.newsapp.databinding.FragmentDetailsBinding
import com.berkyavuz.newsapp.model.response.Article
import com.bumptech.glide.Glide
import com.berkyavuz.newsapp.model.entity.ArticleEntity
import com.berkyavuz.newsapp.repository.ArticleRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var articleRepository: ArticleRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        binding.articleTitle.text = article.title
        binding.articleDescription.text = article.description
        Glide.with(this).load(article.urlToImage).into(binding.articleImage)

        binding.btnReadMore.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToWebViewFragment(article.url)
            findNavController().navigate(action)
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, article.url)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        binding.btnFavorite.setOnClickListener {
            val articleEntity = ArticleEntity(
                url = article.url,
                title = article.title,
                description = article.description ?: "",  // Eğer description null ise boş string kullan
                urlToImage = article.urlToImage,
                publishedAt = article.publishedAt,
                source = article.source.name
            )
            insertArticleToFavorites(articleEntity)
        }
    }

    private fun insertArticleToFavorites(article: ArticleEntity) {
        lifecycleScope.launch {
            articleRepository.insertArticle(article)
            Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

