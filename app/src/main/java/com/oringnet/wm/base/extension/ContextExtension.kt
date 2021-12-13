package com.oringnet.wm.base.extension

import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment


const val DENIED = "DENIED"
const val EXPLAINED = "EXPLAINED"

inline fun Fragment.requestMultiplePermissions(
    vararg permissions: String,
    crossinline allGranted: () -> Unit = {},
    crossinline denied: (List<String>) -> Unit = {},
    crossinline explained: (List<String>) -> Unit = {}
) {

    if (Build.VERSION.SDK_INT < 23) {
        allGranted.invoke()
        return
    }

    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
        //過濾 value 為 false 的元素並轉換為 list



        val deniedList = result.filter { !it.value }.map { it.key }
        when {
            deniedList.isNotEmpty() -> {
                //對被拒絕全選列表進行分組，分組條件為是否勾選不再詢問
                val map = deniedList.groupBy { permission ->
                    if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
                }
                //被拒接且沒勾選不再詢問
                map[DENIED]?.let { denied.invoke(it) }
                //被拒接且勾選不再詢問
                map[EXPLAINED]?.let { explained.invoke(it) }
            }
            else -> allGranted.invoke()
        }
    }.launch(permissions)
}
