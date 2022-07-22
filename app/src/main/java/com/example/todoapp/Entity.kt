package com.example.todoapp

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey

//  Entity class is used to create table
// entity used in creating room

@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var priority:String
)