package com.sapar.froyoapplication.utils

import android.content.res.Resources

fun Number.px(): Int {
    return (this.toFloat() * Resources.getSystem().displayMetrics.density).toInt()
}

fun Number.dp(): Int {
    return (this.toInt() / Resources.getSystem().displayMetrics.density).toInt()
}
