package ir.mahsan.challenge

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.mahsan.challenge.view.MainFragment
import ir.mahsan.challenge.view.ui.NewsList
import ir.mahsan.challenge.view.ui.theme.SystemTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            SystemTheme {
                NewsList(data = emptyFlow(), onItemClicked = {})
            }
        }
    }

    @Test
    fun renderNewsList_emptyState() {
        val navController = mock(NavController::class.java)
        val titleScenario = launchFragmentInContainer<MainFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        composeRule.onNodeWithTag("NewsListEmptyState").assertIsDisplayed()
    }
}