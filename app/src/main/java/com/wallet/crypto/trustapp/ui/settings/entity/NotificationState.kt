package com.wallet.crypto.trustapp.ui.settings.entity

/* compiled from: NotificationState.kt */
enum class NotificationState {
    ENABLE,
    DISABLE;

    fun logicState(): Boolean {
        return this == ENABLE
    }

    fun toggle(): NotificationState {
        val notificationState = this
        val notificationState2 = ENABLE
        return if (notificationState == notificationState2) DISABLE else notificationState2
    }

    companion object {
        @JvmStatic
        fun getState(z: Boolean): NotificationState {
            return if (z) {
                NotificationState.ENABLE
            } else NotificationState.DISABLE
        }

    }
}
