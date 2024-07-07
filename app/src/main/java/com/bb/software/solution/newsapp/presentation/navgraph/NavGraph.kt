package com.bb.software.solution.newsapp.presentation.navgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bb.software.solution.newsapp.presentation.bookmark.BookMarkScreen
import com.bb.software.solution.newsapp.presentation.bookmark.BookmarkViewModel
import com.bb.software.solution.newsapp.presentation.home.HomScreen
import com.bb.software.solution.newsapp.presentation.home.HomeViewModel
import com.bb.software.solution.newsapp.presentation.news_navigator.NewsNavigator
import com.bb.software.solution.newsapp.presentation.onboarding.OnBoardingScreen
import com.bb.software.solution.newsapp.presentation.onboarding.OnBoardingViewModel
import com.bb.software.solution.newsapp.presentation.search.SearchScreen
import com.bb.software.solution.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ) {

            composable(Route.OnboardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = { onBoardingEvent, booleanValue ->
                        viewModel.onEvent(onBoardingEvent, booleanValue)
                    }
                )

                //can use both
//               OnBoardingScreen(
//                   event = viewModel::onEvent
//               )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(Route.NewsNavigatorScreen.route) {
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomScreen(articles, navigate = {
//
//                })
//                val searchViewModel: SearchViewModel = hiltViewModel()
//                SearchScreen(state = searchViewModel.state.value, events = {}) {
//
//                }

//                val viewModel: BookmarkViewModel = hiltViewModel()
//                BookMarkScreen(state = viewModel.state.value, navigateToDetails = {})


                NewsNavigator()


            }
        }

    }

}