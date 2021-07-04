package com.dxn.advancedlist.ui.lists


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SwipeableLists() {
    val albums = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    )
    LazyColumn {
        itemsIndexed(
            items = albums,
            itemContent = { index, album ->
                SwipeableListItem(index, album) { index ->

                }
            })
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SwipeableListItem(index: Int, album: Int, onItemSwiped: (Int) -> Unit) {
    val visible = remember(album) { mutableStateOf(true) }

    AnimatedVisibility(visible = visible.value) {
        Box() {
            BackgroundListItem(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .height(100.dp)
                    .background(
                        Color.Green
                    )
            )
            ForegroundListItem(index) {
                visible.value = false
                onItemSwiped.invoke(index)
            }
        }
    }
}

enum class SwipeState {
    SWIPED, VISIBLE
}

@ExperimentalMaterialApi
@Composable
fun ForegroundListItem(index: Int, onItemSwiped: (Int) -> Unit) {
    val swipeableState = rememberSwipeableState(
        initialValue = SwipeState.VISIBLE,
        confirmStateChange = {
            if (it == SwipeState.SWIPED) {
                onItemSwiped.invoke(index)
            }
            true
        }
    )
    val swipeAnchors = mapOf(0f to SwipeState.VISIBLE, -1000f to SwipeState.SWIPED)

    Row(
        modifier = Modifier
            .swipeable(
                state = swipeableState,
                anchors = swipeAnchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(100.dp)
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .background(Color.Cyan),
        verticalAlignment = Alignment.CenterVertically,
    ) {
    }
}

@Composable
fun BackgroundListItem(modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.End, modifier = modifier) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = null
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = null
            )
        }
    }
}