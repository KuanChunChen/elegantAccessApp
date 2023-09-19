package com.oringnet.wm.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.oringnet.wm.ui.base.CustomButton
import com.oringnet.wm.ui.base.GrayBorderEditText
import com.oringnet.wm.ui.base.WmAppBar
import com.oringnet.wm.ui.theme.BorderGray
import com.oringnet.wm.ui.theme.WmGray
import com.oringnet.wm.ui.theme.WmOrange


@Composable
fun LoginPageContent(
    deviceName: String = "",
    isHavePassword: Boolean = false,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
    ) {

        WmAppBar()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = deviceName,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))

        LoginTextField(
            title = if (isHavePassword) {
                "Password"
            } else {
                "Initial Password"
            },
            borderColor = BorderGray,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
        ) {

            CustomButton(
                contentText = "Cancel",
                backgroundColor = WmGray,
                textColor = Color.Black,
                onClick = onCancel
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                contentText = if (isHavePassword) {
                    "Login"
                } else {
                    "Start"
                },
                backgroundColor = WmOrange,
                onClick = onConfirm
            )
        }
    }
}
@Composable
fun SetNewDeviceContent(
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {}) {

    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxHeight()
        ) {

        WmAppBar()

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Set New Device Name",
            fontSize = 24.sp ,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LoginTextField(
            title = "Device Name",
            borderColor = BorderGray,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(38.dp))

        LoginTextField(
            title = "New password",
            borderColor = BorderGray,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))

        LoginTextField(
            title = "Verify password",
            borderColor = BorderGray,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 20.dp)
        ) {

            CustomButton(
                contentText = "Cancel",
                backgroundColor = WmGray,
                textColor = Color.Black,
                onClick = onCancel
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                contentText = "Ok",
                backgroundColor = WmOrange,
                onClick = onConfirm

            )
        }
    }

}


@Composable
fun LoginTextField(
    title: String = "",
    editContent: String = "",
    hint: String = "",
    borderColor: Color,
    modifier: Modifier
) {


    Column(modifier = modifier) {
        Text(text = title,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))
        GrayBorderEditText(
            content = editContent,
            hint = hint,
            borderColor = borderColor
        )

    }

}


@Preview


@Composable
fun PreviewLoginTextField(){
    LoginTextField(
        "Initial Password",
        "You can get initial password in the manual.",
        borderColor = BorderGray,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    )

}


@Preview
@Composable
fun PreviewSetNewDeviceContent(){
    SetNewDeviceContent()

}

@Preview
@Composable
fun PreviewLoginPageContent(){
    LoginPageContent(deviceName = "Demo001")

}
