package com.example.todoapp

import androidx.room.*
import androidx.room.Dao

// Dao is used for creating queries in table
// dao used in creating room

//  suspend is used to run that data in background thread

@Dao
interface Dao {
    @Insert
    suspend fun InsertTask(entity: Entity)

    @Update
    suspend fun UpdateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from to_do")
    suspend fun deleteAll()

    @Query("Select * from to_do")
    suspend fun getTask():List<CardInfo>
}