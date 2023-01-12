package com.example.cheerboost.feature_setting.data.data_source

import androidx.room.*
import com.example.cheerboost.feature_setting.domain.model.Setting

@Dao
interface SettingDao {
    @Query("SELECT * FROM setting WHERE id = :id")
    suspend fun getSetting(id: Int): Setting?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetting(setting: Setting)
}
