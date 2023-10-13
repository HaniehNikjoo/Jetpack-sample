package ir.mahsan.challenge.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.mahsan.challenge.model.datasource.NewsApiDatasource
import ir.mahsan.challenge.model.repository.pagination.NewsPagingSource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiDatasource: NewsApiDatasource
): NewsRepository {
    override fun getList() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApiDatasource)
        }
    ).flow
}