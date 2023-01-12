package com.example.cheerboost.feature_setting.domain.use_case

import com.example.cheerboost.feature_setting.domain.model.Setting
import com.example.cheerboost.feature_setting.domain.repository.SettingRepository

class GetSetting(
    private val repository: SettingRepository
) {
    suspend operator fun invoke(): Setting? {
        return repository.getSetting()
    }
}
