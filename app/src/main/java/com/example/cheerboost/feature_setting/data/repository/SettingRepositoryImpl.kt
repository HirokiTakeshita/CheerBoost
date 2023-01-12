package com.example.cheerboost.feature_setting.data.repository

import com.example.cheerboost.feature_setting.data.data_source.SettingDao
import com.example.cheerboost.feature_setting.domain.model.Setting
import com.example.cheerboost.feature_setting.domain.repository.SettingRepository

class SettingRepositoryImpl(
    private val dao: SettingDao
) : SettingRepository {
    override suspend fun getSetting(): Setting? {
        return dao.getSetting(Setting.settingId)
    }

    override suspend fun insertSetting(setting: Setting) {
        dao.insertSetting(setting)
    }
}
