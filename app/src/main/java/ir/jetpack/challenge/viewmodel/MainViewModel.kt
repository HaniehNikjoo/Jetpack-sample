package ir.jetpack.challenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.jetpack.challenge.model.dto.*
import ir.jetpack.challenge.model.repository.NewsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    fun getList(): Flow<PagingData<Article>> =
        repository.getList().cachedIn(viewModelScope)

}