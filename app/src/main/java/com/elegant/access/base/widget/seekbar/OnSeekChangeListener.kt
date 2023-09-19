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

interface OnSeekChangeListener {
    /**
     * Notification that the progress level has changed.
     *
     *
     * Clients can use the fromUser parameter to distinguish user-initiated changes from
     * those that occurred programmatically, also, if the seek bar type is discrete series,
     * clients can use the thumbPosition parameter to check the thumb position on ticks and
     * tick text parameter to get the tick text which located at current thumb below.
     *
     * @param seekParams the params info about the seeking bar
     */
    fun onSeeking(seekParams: SeekParams)

    /**
     * Notification that the user has started a touch gesture. Clients may want to use this
     * to disable advancing the seek bar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    fun onStartTrackingTouch(seekBar: SeekBarKt)

    /**
     * Notification that the user has finished a touch gesture. Clients may want to use this
     * to re-enable advancing the seek bar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    fun onStopTrackingTouch(seekBar: SeekBarKt)
}