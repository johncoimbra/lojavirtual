package com.johncoimbra.lojavirtiual.extensions

import android.app.Activity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Activity.addFragment(
    mFragmentLoaded: Fragment,
    mSupportFragmentManager: FragmentManager,
    mFrameLayout: FrameLayout
) {
    val fragment = mSupportFragmentManager.beginTransaction()
    fragment.replace(mFrameLayout.id, mFragmentLoaded)
    fragment.commit()
}