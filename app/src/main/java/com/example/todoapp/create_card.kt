package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.android.synthetic.main.activity_create_card.create_priority
import kotlinx.android.synthetic.main.activity_create_card.create_title
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class create_card : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java,"To_Do"
        ).build()

        save_button.setOnClickListener{
            if(create_title.text.toString().trim{it<=' '}.isNotEmpty() &&
                     create_priority.text.toString().trim{it<=' '}.isNotEmpty()){
                var title = create_title.text.toString()
                var priority = create_priority.text.toString()
                DataObject.setData(title,priority)
                GlobalScope.launch {
                    // Coroutine run in background thread
                    database.dao().InsertTask(Entity(0,title,priority))
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}