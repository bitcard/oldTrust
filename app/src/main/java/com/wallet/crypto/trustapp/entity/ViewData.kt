package com.wallet.crypto.trustapp.entity

interface ViewData {

    fun getViewType(): Int

    fun getWeight(): Int
    fun areContentsTheSame(other: ViewData): Boolean

    fun areItemsTheSame(other: ViewData): Boolean

    fun compare(other: ViewData): Int
}
