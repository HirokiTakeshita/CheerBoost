package com.example.cheerboost.feature_data.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSessionDao {
    @Query("SELECT * FROM workoutSession")
    fun getWorkoutSessions(): Flow<List<WorkoutSession>>

    @Query("SELECT * FROM workoutSession WHERE id = :id")
    suspend fun getWorkoutSessionById(id: Int): WorkoutSession?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutSession(session: WorkoutSession)

    @Delete
    suspend fun deleteWorkoutSession(session: WorkoutSession)
}
