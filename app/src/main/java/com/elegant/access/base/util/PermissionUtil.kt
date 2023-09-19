package com.elegant.access.base.util

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

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasBluetoothPermissions(): Boolean {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        hasPermission(Manifest.permission.BLUETOOTH_CONNECT) &&
                hasPermission(Manifest.permission.BLUETOOTH_SCAN) &&
                hasPermission(Manifest.permission.BLUETOOTH_ADVERTISE)
    } else {
        hasPermission(Manifest.permission.BLUETOOTH) &&
                hasPermission(Manifest.permission.BLUETOOTH_ADMIN) &&
                hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

fun Activity.requirePermission(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

fun Activity.requireBluetoothPermissions(requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        requirePermission(
            arrayOf(
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_ADVERTISE
            ), requestCode = requestCode
        )
    } else {
        requirePermission(
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), requestCode = requestCode
        )
    }
}

fun ActivityResultLauncher<Array<out String>>.requireBluetoothPermissions() {
    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
    this.launch(permissions)
}