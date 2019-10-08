package trust.blockchain.entity

import android.os.Parcelable

interface Address : Parcelable {
    fun data(): String

    fun display(): String
}
