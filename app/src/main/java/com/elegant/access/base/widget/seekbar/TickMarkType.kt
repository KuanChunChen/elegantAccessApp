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

interface TickMarkType {
    companion object {
        /**
         * don't show the tickMarks
         */
        const val NONE = 0

        /**
         * show tickMarks shape as regular oval
         */
        const val OVAL = 1

        /**
         * show tickMarks shape as regular square
         */
        const val SQUARE = 2

        /**
         * show tickMarks shape as vertical line , line'size is 2 dp.
         */
        const val DIVIDER = 3
    }
}