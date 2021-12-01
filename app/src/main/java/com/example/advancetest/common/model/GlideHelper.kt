package com.example.advancetest.common.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.advancetest.R

class GlideHelper {
    companion object {
        operator fun invoke(
            url:String,
            ima: ImageView
        ){
            ima.apply {
                Glide.with(context)
                    .load(url)
                    .placeholder(ProgressItem(context))
                    .listener(glideList())
                    .into(ima)
            }
        }


        private fun ProgressItem(context: Context) = CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            setColorSchemeColors(
                ContextCompat.getColor(
                    context,
                    R.color.secondaryColor
                )
            )
            centerRadius = 30f
            start()
        }

        private fun glideList() = object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }
    }
}