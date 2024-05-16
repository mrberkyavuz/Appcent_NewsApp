package com.berkyavuz.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.berkyavuz.newsapp.databinding.FragmentFavoritesBinding
import com.berkyavuz.newsapp.ui.adapters.NewsAdapter
import com.berkyavuz.newsapp.ui.viewmodels.FavoritesViewModel
import com.berkyavuz.newsapp.model.database.AppDatabase
import com.berkyavuz.newsapp.repository.ArticleRepository
import com.berkyavuz.newsapp.network.RetrofitClient
import com.berkyavuz.newsapp.model.response.Article

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var articleRepository: ArticleRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ArticleDao ve NewsService'yi al
        val articleDao = AppDatabase.getDatabase(requireContext()).articleDao()
        val newsService = RetrofitClient.instance

        // ArticleRepository'yi başlat
        articleRepository = ArticleRepository(articleDao, newsService)

        // ViewModel'i başlat
        viewModel = ViewModelProvider(this, FavoritesViewModel.Factory(articleRepository)).get(FavoritesViewModel::class.java)

        setupRecyclerView()

        viewModel.getFavoriteArticles().observe(viewLifecycleOwner, Observer { articleEntities ->
            val articles = articleEntities.map { entity ->
                Article(
                    source = com.berkyavuz.newsapp.model.response.Source(id = null, name = entity.source),
                    author = null,
                    title = entity.title,
                    description = entity.description,
                    url = entity.url,
                    urlToImage = entity.urlToImage,
                    publishedAt = entity.publishedAt,
                    content = null
                )
            }
            newsAdapter.submitList(articles)
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(article)
            findNavController().navigate(action)
        }
        binding.recyclerViewFavorites.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
