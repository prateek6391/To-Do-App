package com.example.todoapp

import androidx.room.Database
import androidx.room.RoomDatabase

// database used in creating room

@Database(entities = [Entity::class], version=1)
abstract class myDatabase : RoomDatabase(){
    abstract fun dao():Dao
}