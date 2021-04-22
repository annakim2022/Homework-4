package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class DetailActivity: AppCompatActivity()  {

    private lateinit var textViewTitle:TextView
    private lateinit var textViewDate:TextView
    private lateinit var textViewContent:TextView
    private lateinit var textViewReflection:TextView
    private lateinit var textViewEmotion:TextView
    private lateinit var buttonDelete:Button
    private lateinit var buttonUpdate:Button
    private lateinit var dreamLog: Array<String>
    private lateinit var test:String

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textViewTitle = findViewById(R.id.textView_title)
        textViewDate = findViewById(R.id.textView_date)
        textViewContent = findViewById(R.id.textView_content)
        textViewReflection = findViewById(R.id.textView_reflection)
        textViewEmotion = findViewById(R.id.textView_emotion)
        buttonDelete = findViewById(R.id.button_delete)
        buttonUpdate = findViewById(R.id.button_update)

        // get intent
        val intent = intent.getStringExtra("id") as String
        val id = Integer.parseInt(intent)


        dreamViewModel.select(id).observe(this, Observer {

            if (it!=null) {
                textViewTitle.text = it.title
                textViewDate.text = it.date
                textViewContent.text = it.content
                textViewReflection.text = it.reflection
                textViewEmotion.text = it.emotion
                test = it.id.toString()
                dreamLog = arrayOf(it.id.toString(), it.title, it.date, it.content, it.reflection, it.emotion)
            }

        })


        buttonDelete.setOnClickListener{
            dreamViewModel.delete(id)
            finish()
        }

        buttonUpdate.setOnClickListener{

            val intent = Intent(this@DetailActivity, UpdateActivity::class.java)

            intent.putExtra("id", test)
            intent.putExtra("title", textViewTitle.text)
            intent.putExtra("date", textViewDate.text)
            intent.putExtra("content", textViewContent.text)
            intent.putExtra("reflection", textViewReflection.text)
            intent.putExtra("emotion", textViewEmotion.text)

            startActivity(intent)

        }


    }

    }