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

interface IndicatorType {
    companion object {
        /**
         * don't have indicator to show.
         */
        const val NONE = 0

        /**
         * the indicator shape like water-drop
         */
        const val CIRCULAR_BUBBLE = 1

        /**
         * the indicator corners is rounded shape
         */
        const val ROUNDED_RECTANGLE = 2

        /**
         * the indicator corners is square shape
         */
        const val RECTANGLE = 3

        /**
         * set custom indicator you want
         */
        const val CUSTOM = 4
    }
}