package com.elegant.access.ui.splash

/**
 * This file is part of an Android project developed by elegant.access.
 *
 * For more information about this project, you can visit our website:
 * {@link https://elegantaccess.org/2021/11/12/android-kotlin-bluetooth-gatt-client}
 *
 * This project demonstrates how to use Bluetooth GATT in Android with Kotlin.
 * It includes examples of connecting to a Bluetooth device, discovering services,
 * reading characteristics, and receiving notifications.
 *
 * Please note that this project is for educational purposes only and is not intended
 * for use in production environments.
 *
 * @author Willy.Chen
 * @version 1.0
 * @since 2020~2023
 */

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elegant.access.R
import com.elegant.access.ui.theme.EaYellow

@ExperimentalAnimationApi
@Composable
fun SplashContent(event: () -> Unit = {}) {



    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .background(
                        color = EaYellow
                    )
                    .fillMaxSize(),
            ) {

                SplashLogo(event = event)
            }
        }
    )
}


@ExperimentalAnimationApi
@Composable
fun SplashLogo(event:()->Unit= {}){
    val startDurationMillis = 1500
    val exitDurationMillis = 1200
    val delayMillis = startDurationMillis + exitDurationMillis + 200L


    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
            Handler(Looper.getMainLooper()).postDelayed({
                event.invoke()
            }, delayMillis)

        }
    }
    Column(
        verticalArrangement =
        Arrangement.Center,
        horizontalAlignment =
        Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 150.dp)
            .background(
                color = Color.White
            )
            .fillMaxSize()
    ) {

        AnimatedVisibility(
            visibleState = state,
            enter = expandHorizontally(
                animationSpec = tween(
                    durationMillis = startDurationMillis,
                    easing = LinearEasing
                ),
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = exitDurationMillis,
                    easing = LinearEasing
                ),
            )
        ) {
            Image(
                painter = painterResource(R.drawable.kc_cover_logo),
                contentDescription = "Splash logo",
                modifier = Modifier
                    .wrapContentHeight()
                    .width(240.dp)

            )
            state.targetState = false


        }


    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewSplashCard(){
    SplashContent()
}