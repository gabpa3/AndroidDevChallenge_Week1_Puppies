package com.alorma.puppies.ui.widget

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.puppies.R
import com.alorma.puppies.ui.model.PuppyItemModel
import com.alorma.puppies.ui.theme.PuppiesTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyAvatar(
    modifier: Modifier = Modifier,
    puppy: PuppyItemModel,
) {
    key(puppy.image) {
        val color = MaterialTheme.colors.onSurface.copy(alpha = 0.20f)
        val compositeColor = color.compositeOver(MaterialTheme.colors.surface)
        val avatarModifier = modifier.background(compositeColor)

        Box(modifier = avatarModifier) {
            if (puppy.image == null) {
                PuppyEmptyIcon(contentDescription = "${puppy.name} picture empty")
            } else {
                CoilImage(
                    data = puppy.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    loading = {
                        PuppyLoadingIcon(contentDescription = "${puppy.name} picture loading")
                    },
                    error = { error ->
                        Log.w("PuppyAvatar", error.throwable)
                        PuppyErrorIcon(contentDescription = "${puppy.name} picture error")
                    }
                )
            }
        }

    }
}

@Composable
fun PuppyEmptyIcon(contentDescription: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.secondary) {
            PuppyPlaceholderIcon(contentDescription = contentDescription)
        }
    }
}

@Composable
fun PuppyLoadingIcon(contentDescription: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)
        )
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colors.primary.copy(alpha = 0.5f)
        ) {
            PuppyPlaceholderIcon(contentDescription = contentDescription)
        }
    }
}

@Composable
fun PuppyErrorIcon(contentDescription: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.error) {
            PuppyPlaceholderIcon(contentDescription = contentDescription)
        }
    }
}

@Composable
fun BoxScope.PuppyPlaceholderIcon(contentDescription: String) {
    Icon(
        modifier = Modifier
            .size(40.dp)
            .align(Alignment.Center),
        painter = painterResource(id = R.drawable.ic_pawn),
        contentDescription = contentDescription,
    )
}

@Preview(showBackground = true, name = "Empty")
@Composable
fun EmptyPreviewPuppyImage() {
    PuppiesTheme {
        Box(modifier = Modifier.size(80.dp)) {
            PuppyEmptyIcon(contentDescription = "")
        }
    }
}

@Preview(showBackground = true, name = "Loading")
@Composable
fun LoadingPreviewPuppyImage() {
    PuppiesTheme {
        Box(modifier = Modifier.size(80.dp)) {
            PuppyLoadingIcon(contentDescription = "")
        }
    }
}

@Preview(
    showBackground = true,
    name = "Error",
)
@Composable
fun ErrorPreviewPuppyImage() {
    PuppiesTheme {
        Box(modifier = Modifier.size(80.dp)) {
            PuppyErrorIcon(contentDescription = "")
        }
    }
}