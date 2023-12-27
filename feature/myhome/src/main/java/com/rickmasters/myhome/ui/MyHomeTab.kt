package com.rickmasters.myhome.ui

import androidx.annotation.StringRes
import com.rickmasters.myhome.R

internal sealed class MyHomeTab(
    @StringRes val titleResId: Int,
) {
    data object Cameras : MyHomeTab(titleResId = R.string.cameras)
    data object Doors : MyHomeTab(titleResId = R.string.doors)
}