package com.example.cheerboost.feature_count.domain.voice

sealed class VoiceEvent {
    data class Count(val count: Int) : VoiceEvent()
    object Encourage : VoiceEvent()
}
