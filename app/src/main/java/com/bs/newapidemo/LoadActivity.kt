package com.bs.newapidemo

import ApiService
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bs.newapidemo.databinding.ActivityLoadBinding

class LoadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadBinding
    private val productViewModel by lazy {
        ViewModelProvider(
            this,
            ProductViewModelFactory(ProductRepository(ApiService.create()))
        ).get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        productViewModel.products.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    binding.recyclerView.adapter = ProductAdapter(resource.data)
                }
                is Resource.Error -> showError(resource.exception)
            }
        }
        productViewModel.fetchProducts()
    }

    private fun showLoading() {
        SnackbarHelper.showLoading(binding.root)
    }

    private fun hideLoading() {
        SnackbarHelper.showSuccess(binding.root, "Loaded successfully!")
    }

    private fun showError(exception: Exception) {
        SnackbarHelper.showError(binding.root, "Error: ${exception.message}")
    }
}
