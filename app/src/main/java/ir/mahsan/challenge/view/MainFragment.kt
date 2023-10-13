package ir.mahsan.challenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import ir.mahsan.challenge.R
import ir.mahsan.challenge.common.Constants.BUNDLE_ID
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.view.ui.ManiTheme
import ir.mahsan.challenge.view.ui.NewsList
import ir.mahsan.challenge.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var article: Flow<PagingData<Article>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ManiTheme {
                NewsList(
                    data = article,
                    onItemClicked = {
                        val bundle = Bundle()
                        bundle.putString(BUNDLE_ID, it)
                        NavHostFragment.findNavController(this@MainFragment)
                            .navigate(R.id.action_nav_main_fragment_to_nav_detail_fragment, bundle)
                    },
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = viewModel.getList()
    }
}