package com.bb.software.solution.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.bb.software.solution.newsapp.R

data class OnboardPageModel(
    val tilte: String,
    val description: String,
    @DrawableRes val image: Int
)

val onboardPages = listOf(
    OnboardPageModel(
        tilte = "Dummy News Page Screen",
        description = "Ensure your app theme includes a window background that will be shown while the splash screen is displayed. ",
        image = R.drawable.onboard1
    ),
    OnboardPageModel(
        tilte = "Dummy News Page Screen",
        description = "Ensure your app theme includes a window background that will be shown while the splash screen is displayed. ",
        image = R.drawable.onboard2

    ),
    OnboardPageModel(
        tilte = "Dummy News Page Screen",
        description = "Ensure your app theme includes a window background that will be shown while the splash screen is displayed. ",
        image = R.drawable.onboard3
    )

)