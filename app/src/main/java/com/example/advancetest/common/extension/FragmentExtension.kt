package com.example.advancetest.common.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.advancetest.common.const.EMPTY_STRING
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


fun Fragment.shortToast(@StringRes id:Int){
    Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
}

fun Fragment.shortToast(msg: String = EMPTY_STRING){
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.lenghtToast(@StringRes id:Int){
    Toast.makeText(requireContext(), id, Toast.LENGTH_LONG).show()
}

fun Fragment.showDialog(dialog: DialogFragment){
    if (!dialog.isVisible)
        dialog.show(childFragmentManager, "Dialog")
}

fun Fragment.initYoutube(ytPlayer: YouTubePlayerView, videoCode:String, time: Float, actionTime: (Float) -> Unit){
    this.lifecycle.addObserver(ytPlayer)
    ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            youTubePlayer.loadVideo(videoCode, time)
            youTubePlayer.addListener(object  : YouTubePlayerListener {
                override fun onApiChange(youTubePlayer: YouTubePlayer) {
                }

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                    actionTime.invoke(second)
                }

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: PlayerConstants.PlayerError
                ) {
                }

                override fun onPlaybackQualityChange(
                    youTubePlayer: YouTubePlayer,
                    playbackQuality: PlayerConstants.PlaybackQuality
                ) {
                }

                override fun onPlaybackRateChange(
                    youTubePlayer: YouTubePlayer,
                    playbackRate: PlayerConstants.PlaybackRate
                ) {
                }

                override fun onReady(youTubePlayer: YouTubePlayer) {
                }

                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState
                ) {
                }

                override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                }

                override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
                }

                override fun onVideoLoadedFraction(
                    youTubePlayer: YouTubePlayer,
                    loadedFraction: Float
                ) {
                }

            })
        }
    })
}