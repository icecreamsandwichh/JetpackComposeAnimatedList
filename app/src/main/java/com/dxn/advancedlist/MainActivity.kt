package com.dxn.advancedlist

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dxn.advancedlist.ui.lists.AnimatedLists
import com.dxn.advancedlist.ui.lists.SwipeableLists
import com.dxn.advancedlist.ui.theme.AdvancedListTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn {
                items(50) {
                    RotateX()
                }
            }
        }
    }
}


@Composable
fun RotateX() {
    val animatedProgress = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 360f,
            animationSpec = tween(2000, easing = FastOutSlowInEasing)
        )
    }
    val animX = Modifier
        .padding(8.dp)
        .graphicsLayer(rotationX = animatedProgress.value)

    Row(modifier = animX) {
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .background(Color.Cyan)
                .weight(1f)
                .height(50.dp)
        ) {

        }
    }
}