package com.elegant.access.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elegant.access.R
import com.elegant.access.ui.theme.ElegantAccessAppTheme
import com.elegant.access.ui.theme.BorderGray
import com.elegant.access.ui.theme.Gray
import com.elegant.access.ui.theme.Orange
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.elegant.access.ui.theme.EaYellow

@Composable
fun CustomButton(
    contentText: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    backgroundColor: Color,
    textColor: Color = Color.White
) {


    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        onClick = onClick,
        modifier = Modifier
            .height(50.dp)
            .width(156.dp),
    ) {
        Text(
            text = contentText.uppercase(),
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = textColor,

        )
    }
}

@Composable
fun GrayBorderEditText(
    content: String = "",
    hint: String = "",
    borderColor: Color
) {
    var text by rememberSaveable { mutableStateOf(content) }

    TextField(
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
            cursorColor = Orange,
            placeholderColor = BorderGray
        ),
        value = text,
        placeholder = { Text(hint) },
        onValueChange = { text = it },
        modifier = Modifier
            .border(4.dp, borderColor)
            .fillMaxWidth()
    )
}

@Composable
fun NavigateTitleBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = { },
    title: @Composable ColumnScope.() -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column(
        Modifier.background(backgroundColor.copy(alpha = 0.95f))
    ) {
        TopAppBar(
            backgroundColor = EaYellow,
            elevation = 0.dp, // No shadow needed
            contentColor = MaterialTheme.colors.onSurface,
            actions = actions,
            title = {
                Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    title() }
                    },
            navigationIcon = {

                Icon(
                    tint = Color.White,
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = stringResource(id = R.string.app_logo),
                    modifier = Modifier
                        .clickable(onClick = onNavIconPressed)
                        .padding(horizontal = 16.dp),
                )



            }
        )

        Divider()
    }
}

@Composable
fun ElegantAccessAppBar(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {

        TopAppBar(
            modifier = modifier,
            backgroundColor = Color.White,
            elevation = 0.dp, // No shadow needed
            contentColor = MaterialTheme.colors.onSurface,

            ) {


            Image(
                painter = painterResource(id = R.drawable.kc_cover_logo),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .width(118.dp)
                    .height(18.dp),
            )


        }

        Divider(thickness = 1.dp, color = Gray)
    }

}

@Preview
@Composable
fun PreviewWmAppBar(){
    ElegantAccessAppTheme(){
        ElegantAccessAppBar()

    }


}

@Preview
@Composable
fun PreviewNavigateBar(){
    ElegantAccessAppTheme(){
        NavigateTitleBar(title = {
            Text(
                color = Color.White,
                text = "Title",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) })

    }
}

@Preview
@Composable
fun PreViewGrayBorderEditText(){
    GrayBorderEditText(
        hint = "You can get initial password in the manual.",
        borderColor = BorderGray
    )
}

@Preview
@Composable
fun PreviewCustomButton() {
    Row() {
        CustomButton(
            contentText = "Cancel",
            backgroundColor = Gray,
            textColor = Color.Black
        )

        CustomButton(
            contentText = "Start",
            backgroundColor = EaYellow
        )

    }
}
