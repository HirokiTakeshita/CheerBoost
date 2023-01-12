package com.example.cheerboost.feature_count.domain.voice

import android.content.Context
import android.media.MediaPlayer
import com.example.cheerboost.core.util.VoiceAvatarType
import kotlinx.coroutines.delay

class VoiceAvatar(
    private val context: Context
) : Avatar() {
    override suspend fun playVoice(avatarType: String, event: VoiceEvent) {
        val avatarId = when (avatarType) {
            VoiceAvatarType.YOUNG_MAN -> 1
            VoiceAvatarType.LADY -> 2
            VoiceAvatarType.GIRL -> 3
            else -> -1
        }
        when (event) {
            is VoiceEvent.Count -> {
                val maxNum = 100
                val currentNum = if (event.count % maxNum == 0) maxNum else event.count % maxNum
                val resId = context.resources.getIdentifier(
                    "id${avatarId}_count$currentNum",
                    "raw",
                    context.packageName
                )
                val mediaPlayer = MediaPlayer.create(context, resId)
                mediaPlayer.start()
                delay(1000L)
                mediaPlayer.release()
            }
            is VoiceEvent.Encourage -> {
                val maxNum = 30
                val currentNum = (1..maxNum).random()
                val resId = context.resources.getIdentifier(
                    "id${avatarId}_encourage$currentNum",
                    "raw",
                    context.packageName
                )
                val mediaPlayer = MediaPlayer.create(context, resId)
                mediaPlayer.start()
                delay(4000L)
                mediaPlayer.release()
            }
        }
    }
}
