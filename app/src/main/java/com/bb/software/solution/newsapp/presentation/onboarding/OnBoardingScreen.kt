package com.bb.software.solution.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bb.software.solution.newsapp.presentation.Dimens.mediumPadding16
import com.bb.software.solution.newsapp.presentation.Dimens.width52
import com.bb.software.solution.newsapp.presentation.common.NewsButton
import com.bb.software.solution.newsapp.presentation.common.NewsTextButton
import com.bb.software.solution.newsapp.presentation.common.PageIndicator
import com.bb.software.solution.newsapp.presentation.onboarding.components.OnboardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(event: (OnBoardingEvents, Boolean) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {

        // Remember the state of the pager, initialized to the first page
        val pageState = rememberPagerState(initialPage = 0) {
            onboardPages.size
        }

        // Remember the coroutine scope for performing asynchronous operations
        val scope = rememberCoroutineScope()

        // Derived state of the button labels based on the current page
        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }

        }

        // Horizontal pager to display onboarding pages
        HorizontalPager(state = pageState) { index ->
            OnboardingPage(page = onboardPages[index])

        }
        Spacer(modifier = Modifier.weight(1f))

        // Row for displaying the page indicator and navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding16)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Page indicator to show the current page position
            PageIndicator(
                modifier = Modifier.width(width52),
                pageSize = onboardPages.size,
                selectedPage = pageState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

// Conditional display of the "Back" button
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pageState.animateScrollToPage(page = pageState.currentPage - 1)
                            }

                        }
                    )
                }

                // "Next" or "Get Started" button

                NewsButton(label = buttonState.value[1], onClick = {
                    scope.launch {
                        if (pageState.currentPage == 2) {
                            event(OnBoardingEvents.saveAppEntry, true)
                        } else {
                            pageState.animateScrollToPage(page = pageState.currentPage + 1)
                        }

                    }

                })

            }
        }

    }

}