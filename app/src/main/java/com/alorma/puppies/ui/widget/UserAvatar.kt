/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alorma.puppies.ui.widget

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.puppies.ui.model.UserModel
import com.alorma.puppies.ui.theme.PuppiesTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    user: UserModel,
) {
    key(user.avatar) {
        val color = MaterialTheme.colors.onSurface.copy(alpha = 0.20f)
        val compositeColor = color.compositeOver(MaterialTheme.colors.surface)

        Card(
            modifier = modifier,
            elevation = 8.dp,
            backgroundColor = compositeColor,
        ) {
            CoilImage(
                data = user.avatar,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                loading = {
                    UserLoadingIcon(contentDescription = "${user.name} picture loading")
                },
                error = { error ->
                    Log.w("UserAvatar", error.throwable)
                    UserErrorIcon(contentDescription = "${user.name} picture error")
                }
            )
        }
    }
}

@Composable
fun UserEmptyIcon(contentDescription: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.primary) {
            UserPlaceholderIcon(contentDescription = contentDescription)
        }
    }
}

@Composable
fun UserLoadingIcon(contentDescription: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)
        )
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colors.primary.copy(alpha = 0.5f)
        ) {
            UserPlaceholderIcon(contentDescription = contentDescription)
        }
    }
}

@Composable
fun UserErrorIcon(contentDescription: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.error) {
            UserPlaceholderIcon(contentDescription = contentDescription)
        }
    }
}

@Composable
fun BoxScope.UserPlaceholderIcon(contentDescription: String) {
    Icon(
        modifier = Modifier
            .size(40.dp)
            .align(Alignment.Center),
        imageVector = Icons.Default.AccountCircle,
        contentDescription = contentDescription,
    )
}

@Preview(showBackground = true, name = "Empty")
@Composable
fun EmptyPreviewUserImage() {
    PuppiesTheme {
        Box(modifier = Modifier.size(80.dp)) {
            UserEmptyIcon(contentDescription = "")
        }
    }
}

@Preview(showBackground = true, name = "Loading")
@Composable
fun LoadingPreviewUserImage() {
    PuppiesTheme {
        Box(modifier = Modifier.size(80.dp)) {
            UserLoadingIcon(contentDescription = "")
        }
    }
}

@Preview(
    showBackground = true,
    name = "Error",
)
@Composable
fun ErrorPreviewUserImage() {
    PuppiesTheme {
        Box(modifier = Modifier.size(80.dp)) {
            UserErrorIcon(contentDescription = "")
        }
    }
}
