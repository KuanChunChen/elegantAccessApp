package com.elegant.access.base.widget.seekbar

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

class SeekParams internal constructor(seekBar: SeekBarKt) {
    //for continuous series seek bar
    // The SeekBar whose progress has changed
    var seekBar: SeekBarKt = seekBar

    //The current progress level.The default value for min is 0, max is 100.
    var progress = 0

    //The current progress level.The default value for min is 0.0, max is 100.0.
    var progressFloat = 0f

    //True if the progress change was initiated by the user, otherwise by setProgress() programmatically.
    var fromUser = false

    //for discrete series seek bar
    //the thumb location on tick when the section changed, continuous series will be zero.
    var thumbPosition = 0

    //the text below tick&thumb when the section changed.
    var tickText: String? = null

}
