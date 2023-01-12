package com.example.cheerboost.feature_setting.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Singleton

@Entity
@Singleton
class Setting(
    val avatarType: String,
    val isCountOnlyMode: Boolean,
    @PrimaryKey val id: Int = settingId
) {
    companion object {
        const val settingId = -1
    }
}
