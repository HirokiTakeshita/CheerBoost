package com.example.cheerboost.feature_setting.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cheerboost.feature_setting.domain.model.Setting

@Database(
    entities = [Setting::class],
    version = 1
)
abstract class SettingDatabase : RoomDatabase() {
    abstract val dao: SettingDao

    companion object {
        const val DATABASE_NAME = "setting"
    }
}
