package ir.jetpack.challenge.model.repository

import androidx.paging.PagingData
import ir.jetpack.challenge.model.dto.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getList() : Flow<PagingData<Article>>
}