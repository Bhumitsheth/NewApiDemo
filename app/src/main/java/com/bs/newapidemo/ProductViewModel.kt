package com.bs.newapidemo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhumit.coroutinesdemo.Product
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>> = _products

    fun fetchProducts() {
        _products.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.getProducts()
            _products.value = result
        }
    }
}
