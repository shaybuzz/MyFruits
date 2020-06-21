package com.tut.mywebsocket.view.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:colorBackground")
    fun updateColor(view: View, color: String?) {
        color?.let {
            val color = Color.parseColor(color)
            if(view.background is GradientDrawable) {
                val gradientDrawable = view.background as GradientDrawable
                gradientDrawable.setColor(color)
            }
        }
    }
}