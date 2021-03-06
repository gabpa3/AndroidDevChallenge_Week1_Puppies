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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alorma.puppies.data.PuppyProvider
import com.alorma.puppies.ui.base.modifier.primaryClick
import com.alorma.puppies.ui.model.PuppyItemModel
import com.alorma.puppies.ui.theme.PuppiesTheme
import java.util.Locale

@Composable
fun PuppyItem(
    puppy: PuppyItemModel,
    onClick: () -> Unit,
) {
    val avatarHeight = 140.dp
    val avatarWidth = 140.dp
    Box(
        modifier = Modifier.padding(horizontal = 8.dp),
    ) {
        PuppyItemCard(avatarHeight, avatarWidth, puppy, onClick = onClick)
        PuppyAvatar(
            modifier = Modifier
                .height(avatarHeight)
                .width(avatarWidth),
            puppy = puppy,
            shape = MaterialTheme.shapes.medium,
        )
    }
}

@Composable
fun PuppyItemCard(
    avatarHeight: Dp,
    avatarWidth: Dp,
    puppy: PuppyItemModel,
    onClick: () -> Unit,
) {
    val verticalDistanceFromAvatar = 12.dp
    Card(
        modifier = Modifier
            .padding(
                top = verticalDistanceFromAvatar,
                start = avatarWidth / 2,
                bottom = verticalDistanceFromAvatar,
            )
            .height(avatarHeight - verticalDistanceFromAvatar * 2)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier.primaryClick(onClick = onClick)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 8.dp,
                        end = 12.dp,
                    )
            ) {
                Icon(
                    painter = painterResource(id = puppy.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.40f)
                )
            }
            Box(
                modifier = Modifier.padding(
                    top = 8.dp,
                    start = avatarWidth / 2 + 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = puppy.name,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = puppy.breed.name,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.40f)
                    )
                }

                Row(
                    modifier = Modifier.align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${puppy.age} years",
                        style = MaterialTheme.typography.caption,
                    )
                    Text(
                        text = " · ",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = puppy.gender.name.capitalize(Locale.getDefault()),
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPuppy() {
    PuppiesTheme {
        val puppy = PuppyProvider.randomPuppy()
        PuppyItem(puppy = puppy) { }
    }
}
