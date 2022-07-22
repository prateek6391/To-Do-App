package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.android.synthetic.main.activity_update_card.create_priority
import kotlinx.android.synthetic.main.activity_update_card.create_title
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class update_card : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            create_title.setText(title)
            create_priority.setText(priority)

            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    // Coroutine run in background thread
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }

            update_button.setOnClickListener {
                if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty() &&
                    create_priority.text.toString().trim { it <= ' ' }.isNotEmpty()
                ) {
                    var newtitle = create_title.text.toString()
                    var newpriority = create_priority.text.toString()
                    DataObject.updateData(pos, newtitle, newpriority)

                    GlobalScope.launch {
                        // Coroutine run in background thread
                        database.dao().UpdateTask(
                            Entity(pos + 1, newtitle, newpriority)
                        )
                    }
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}