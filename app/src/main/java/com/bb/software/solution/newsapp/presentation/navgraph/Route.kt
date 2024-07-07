package com.bb.software.solution.newsapp.presentation.navgraph

sealed class Route(val route: String) {
    object OnboardingScreen : Route("onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object SearchScreen : Route("searchScreen")
    object BookMarkScreen: Route("bookMarkScreen")
    object DetailScreen: Route("detailScreen")
    object NewsNavigatorScreen: Route("newsNavigator")
    object AppStartNavigation  :Route("appStartNavigation")
    object NewsNavigation:Route("newsNavigation")
}