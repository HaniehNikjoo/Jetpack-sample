package ir.mahsan.challenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.view.ui.BottomSheet
import ir.mahsan.challenge.view.ui.NewsList
import ir.mahsan.challenge.view.ui.theme.SystemTheme
import ir.mahsan.challenge.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var newsList: Flow<PagingData<Article>>
    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
//        val window = requireActivity().window
//        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            var isBottomSheetVisible by remember { mutableStateOf(false) }
            if (isBottomSheetVisible) {
                BottomSheet(article) {
                    isBottomSheetVisible = false
                }
            }

            SystemTheme {
                NewsList(
                    data = newsList,
                    onItemClicked = {
                        article = it
                        isBottomSheetVisible = true
                    },
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsList = viewModel.getList()
    }
}