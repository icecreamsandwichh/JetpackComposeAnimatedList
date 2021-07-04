package com.dxn.advancedlist.ui.lists


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalFoundationApi
@Composable
fun AnimatedLists() {
    val tweets = listOf(
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
    )
    val animations = listOf("Fade", "Scale", "Slide", "Fade+Slide", "Slide up", "RotateX")
    Column {
        var animationIndex by remember { mutableStateOf(0) }
        LazyVerticalGrid(cells = GridCells.Fixed(3), modifier = Modifier.padding(vertical = 12.dp)) {

            itemsIndexed(animations){ index, title ->
                YoutubeChip(
                    selected = index == animationIndex,
                    text = title,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = {
                            animationIndex = index
                        })
                )
            }
        }
        LazyColumn {
            itemsIndexed(
                items = tweets,
                itemContent = { index, tweet ->
                    AnimatedListItem(tweet = tweet, index, animationIndex)
                })
        }
    }
}

@Composable
fun AnimatedListItem(tweet: Int, itemIndex: Int, animationIndex: Int) {

    val animatedModifier = when (animationIndex) {
        0 -> {
            val animatedProgress = remember { Animatable(initialValue = 0f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(600)
                )
            }
            Modifier
                .padding(8.dp)
                .alpha(animatedProgress.value)
        }
        1 -> {
            val animatedProgress = remember { Animatable(initialValue = 0.8f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(scaleY = animatedProgress.value, scaleX = animatedProgress.value)
        }
        2 -> {
            val animatedProgress = remember { Animatable(initialValue = 300f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(1000, easing = FastOutSlowInEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(translationX = animatedProgress.value)
        }
        3 -> {
            val animatedProgress = remember { Animatable(initialValue = -300f) }
            val opacityProgress = remember { Animatable(initialValue = 0f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(1000, easing = LinearEasing)
                )
                opacityProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(600)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(translationX = animatedProgress.value)
                .alpha(opacityProgress.value)
        }
        4 -> {
            val animatedProgress = remember { Animatable(initialValue = 300f) }
            val opacityProgress = remember { Animatable(initialValue = 0f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(1000, easing = LinearEasing)
                )
                opacityProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(600)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(translationY = animatedProgress.value)
                .alpha(opacityProgress.value)
        }
        5 -> {
            val animatedProgress = remember { Animatable(initialValue = 0f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(1000, easing = FastOutSlowInEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(rotationX = animatedProgress.value)
        }
        else -> {
            val animatedProgress = remember { Animatable(initialValue = 0.8f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(300)
                )
            }
            Modifier
                .padding(8.dp)
                .alpha(animatedProgress.value)
        }
    }
    Row(
        modifier = animatedModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberVectorPainter(Icons.Filled.Favorite),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(55.dp)
                .padding(4.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
                .height(50.dp)
                .background(Color.Cyan)
        )  {

        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}



@Composable
fun YoutubeChip(selected: Boolean, text: String, modifier: Modifier = Modifier) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )

    }
}