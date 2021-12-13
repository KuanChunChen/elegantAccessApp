package com.oringnet.wm.ui.deviceList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.oringnet.wm.R
import com.oringnet.wm.data.exampleDeviceListData
import com.oringnet.wm.data.getSecondBleList
import com.oringnet.wm.ui.base.WmAppBar
import com.oringnet.wm.ui.theme.WmGray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun DeviceListContent(
    deviceViewModel: DeviceViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navigateToLogin: (BleDevice) -> Unit = {},
    onSwipeRefresh: () -> Unit = {},
) {

    val scrollState = rememberLazyListState()
    val devices: List<BleDevice> by deviceViewModel.devices.observeAsState(listOf())

    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxHeight()) {
            WmAppBar()

            Text(
                text = "Device List",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 8.dp, top = 16.dp),
                fontSize = 24.sp,
                color = Color.Black
            )



            BleDeviceCards(
                bleDevice = devices,
                navigateToLogin = navigateToLogin,
                scrollState = scrollState,
                deviceViewModel = deviceViewModel,
                onSwipeRefresh = onSwipeRefresh
            )

        }
    }
}

@Preview
@Composable
fun PreviewDeviceListContent() {
    DeviceListContent(deviceViewModel = DeviceViewModel(), modifier = Modifier) {}
}

@Composable
fun BleDeviceCards(
    bleDevice: List<BleDevice>,
    navigateToLogin: (BleDevice) -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    deviceViewModel: DeviceViewModel,
    onSwipeRefresh: () -> Unit = {},
) {

    val isRefreshing by deviceViewModel.isRefreshing.observeAsState()


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing!!),
        onRefresh = {
            onSwipeRefresh()
        },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                contentColor = Color.Black,
                arrowEnabled = true,
                fade = true,
                scale = true,
                backgroundColor = MaterialTheme.colors.primary,
            )
        },
    ) {
        LazyColumn(
            state = scrollState,
            modifier = modifier.fillMaxHeight()
        ) {
            item {
                bleDevice.forEach { bleDevice ->
                    BleDeviceCard(bleDevice, navigateToLogin)

                }
            }
        }
    }


}

@Composable
fun BleDeviceCard(bleDevice: BleDevice, navigateToLogin: (BleDevice) -> Unit) {


    val padding = 8.dp
    Column(
        modifier = Modifier
            .clickable(onClick = { navigateToLogin(bleDevice) })
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White)
            .padding(padding)
    ) {
        Text(
            text = bleDevice.deviceName,
            color = Color.Black,
            style = MaterialTheme.typography.h6

        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = bleDevice.bleAddress,
                color = WmGray,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .weight(2f)
                    .wrapContentHeight()
            )

            Text(
                text = stringResource(id = R.string.ble_signal_dBm, bleDevice.signalValue),
                color = WmGray,
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = getPainterResource(bleDevice.signalValue),
                contentDescription = "signal picture",
                modifier = Modifier
                    .height(12.dp)
                    .width(18.dp)

            )


        }

    }
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(start = 8.dp, end = 8.dp)
    )





}

@Composable
fun getPainterResource(signalValue: Int): Painter {

    when {
        signalValue > -70 -> {
            return painterResource(R.drawable.wm_rssi_4)
        }
        signalValue >= -85 -> {
            return painterResource(R.drawable.wm_rssi_3)
        }
        signalValue >= -100 -> {
            return painterResource(R.drawable.wm_rssi_2)
        }
        signalValue >= -110 -> {
            return painterResource(R.drawable.wm_rssi_1)
        }
        else -> {
            return painterResource(R.drawable.wm_rssi_0)
        }

    }

}

@Preview
@Composable
fun PreviewBleDeviceCard() {
    BleDeviceCard(BleDevice(null,"device 1","00:00:00:a1:2b:cc",-87)) {}
}