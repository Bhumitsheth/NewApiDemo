package com.bs.newapidemo

import ApiService
import com.bhumit.coroutinesdemo.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val service: ApiService) {
    suspend fun getProducts(): Resource<List<Product>> {
        return try {
            val response = withContext(Dispatchers.IO) { service.getProducts() }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
