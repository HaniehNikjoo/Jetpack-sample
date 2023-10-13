package ir.mahsan.challenge.model.repository

import androidx.paging.PagingData
import ir.mahsan.challenge.model.dto.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getList() : Flow<PagingData<Article>>
}