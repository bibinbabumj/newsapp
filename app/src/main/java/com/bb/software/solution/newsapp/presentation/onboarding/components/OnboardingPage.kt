package com.bb.software.solution.newsapp.presentation.onboarding.components
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bb.software.solution.newsapp.R
import com.bb.software.solution.newsapp.presentation.Dimens.mediumPadding16
import com.bb.software.solution.newsapp.presentation.Dimens.mediumPadding24
import com.bb.software.solution.newsapp.presentation.onboarding.OnboardPageModel
import com.bb.software.solution.newsapp.presentation.onboarding.onboardPages

@Composable
fun OnboardingPage(page: OnboardPageModel, modifier: Modifier=Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(mediumPadding24))
        Text(
            text = page.tilte,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding16),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(mediumPadding24))
        Text(
            text = page.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding16),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnboardingPagePreview() {
    OnboardingPage(
        page = onboardPages[1], modifier = Modifier
    )
}
