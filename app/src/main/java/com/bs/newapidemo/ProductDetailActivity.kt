package com.bs.newapidemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bs.newapidemo.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve product details from intent extras
        val title = intent.getStringExtra("title") ?: "No Title"
        val description = intent.getStringExtra("description") ?: "No Description"
        val imageUrl = intent.getStringExtra("image")

        // Set product details in views
        binding.titleTextView.text = title
        binding.descriptionTextView.text = description

        // Load image with Glide if available
        imageUrl?.let {
            Glide.with(this)
                .load(it)
                .into(binding.productImageView)
        }
    }
}
