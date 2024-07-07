package com.bb.software.solution.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.bb.software.solution.newsapp.domain.model.Article
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bb.software.solution.newsapp.R
import com.bb.software.solution.newsapp.presentation.common.ArticleList

@Composable
fun BookMarkScreen (
    state: BookMarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
    modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(top = 24.dp, start = 24.dp, end = 24.dp)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        ArticleList(
            articles = state.articles,
            onClick = { navigateToDetails(it) }
        )
    }
}