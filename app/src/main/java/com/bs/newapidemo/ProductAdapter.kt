package com.bs.newapidemo
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhumit.coroutinesdemo.Product
import com.bs.newapidemo.databinding.ItemProductBinding
import com.bumptech.glide.Glide

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvTitle.text = product.title
            Glide.with(binding.productImageView.context)
                .load(product.image)
                .into(binding.productImageView)

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ProductDetailActivity::class.java).apply {
                    putExtra("title", product.title)
                    putExtra("description", product.description)
                    putExtra("image", product.image)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}
