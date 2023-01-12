package com.example.cheerboost.feature_data.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cheerboost.feature_data.data.Converters
import com.example.cheerboost.feature_data.domain.model.WorkoutSession

@Database(
    entities = [WorkoutSession::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WorkoutSessionDatabase : RoomDatabase() {
    abstract val dao: WorkoutSessionDao

    companion object {
        const val DATABASE_NAME = "workout_session"
    }
}
