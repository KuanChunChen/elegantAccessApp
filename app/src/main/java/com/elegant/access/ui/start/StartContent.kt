package com.elegant.access.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elegant.access.R
import com.elegant.access.ui.theme.EaYellow


@Composable
fun StartContent(modifier: Modifier = Modifier, onButtonAction:()->Unit={}) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    0.dp,
                    0.dp,
                    0.dp,
                    150.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoCard()
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(18.dp, 0.dp, 18.dp, 28.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StartButton {
                onButtonAction()
            }
        }
    }

}

@Composable
fun LogoCard(){
    Image(
        painter = painterResource(R.drawable.kc_cover_logo),
        contentDescription = "Logo",
        modifier = Modifier
            .wrapContentHeight()
            .width(280.dp)

    )
    Text(
        text = "Enjoy!",
        modifier = Modifier
            .wrapContentWidth()
            .height(40.dp),
        fontSize = 14.sp
    )
}
@Composable
fun StartButton(onButtonAction:()->Unit={}){


    Button(
        onClick = {
            onButtonAction.invoke()
        },
        modifier = Modifier
            .height(50.dp)
            .wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = EaYellow)


    ) {
        Text(
            text = "START",
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .fillMaxHeight(),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }

}

@Preview
@Composable
fun PreviewStartPage(){
    StartContent()
}

@Preview
@Composable
fun PreviewStartButton(){
    StartButton()

}


