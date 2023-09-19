# elegantAccessApp
elegant_access_app

<div class="c-border-main-title-2">前言</div>

我花了一些時間複習之前工作所實作的低功耗藍牙連接。<br>
由於我擔心會忘記，<br>
所以想重新回顧一下並做個紀錄，<br>
希望也能幫助到需要實作的各位。<br>

Android 12之後新增了 權限相關處理，大家可以注意一下！<br> 
這邊是我處理的方式，大家可以參考：
```
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

```
