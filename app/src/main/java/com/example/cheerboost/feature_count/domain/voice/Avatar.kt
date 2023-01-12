package com.example.cheerboost.feature_count.domain.voice

abstract class Avatar {
    abstract suspend fun playVoice(avatarType: String, event: VoiceEvent)
}
