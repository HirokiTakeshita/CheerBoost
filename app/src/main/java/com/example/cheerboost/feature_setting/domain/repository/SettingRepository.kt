package com.example.cheerboost.feature_setting.domain.repository

import com.example.cheerboost.feature_setting.domain.model.Setting

interface SettingRepository {
    suspend fun getSetting(): Setting?
    suspend fun insertSetting(setting: Setting)
}
