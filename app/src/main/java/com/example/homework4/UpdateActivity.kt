package com.example.homework4

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class UpdateActivity: AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextContent: EditText
    private lateinit var editTextReflection: EditText
    private lateinit var spinner: Spinner
    private lateinit var buttonSave: Button
    var emotions = arrayOf("choose emotion", "fear", "panic", "loss of self", "grief", "freedom", "love", "joy", "vulnerability", "confused", "sad")

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextTitle= findViewById(R.id.edit_title)
        editTextContent= findViewById(R.id.edit_content)
        editTextReflection= findViewById(R.id.edit_reflection)
        spinner = findViewById(R.id.spinner)
        buttonSave = findViewById(R.id.button_save)
        spinner = findViewById(R.id.spinner)
        searchSpinner()

        val old = intent.getStringExtra("id") as String
        val id: Int = Integer.parseInt(old)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val reflection = intent.getStringExtra("reflection")
        val emotion = intent.getStringExtra("emotion")

        editTextTitle.setText(title, TextView.BufferType.EDITABLE);
        editTextContent.setText(content, TextView.BufferType.EDITABLE)
        editTextReflection.setText(reflection, TextView.BufferType.EDITABLE)

        //editTextTitle.text = title as Editable
        //editTextContent.text = content as Editable
        //editTextReflection.text = reflection as Editable

        if (emotion == "fear"){
            spinner.setSelection(1)
        }
        if (emotion == "panic"){
            spinner.setSelection(2)
        }
        if (emotion == "loss of self"){
            spinner.setSelection(3)
        }
        if (emotion == "grief"){
            spinner.setSelection(4)
        }
        if (emotion == "freedom"){
            spinner.setSelection(5)
        }
        if (emotion == "love"){
            spinner.setSelection(6)
        }
        if (emotion == "joy"){
            spinner.setSelection(7)
        }
        if (emotion == "vulnerability"){
            spinner.setSelection(8)
        }
        if (emotion == "confused"){
            spinner.setSelection(9)
        }
        if (emotion == "sad"){
            spinner.setSelection(10)
        }

        // catch user input error in front end
        buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(editTextTitle.text) || TextUtils.isEmpty(editTextContent.text) ||
                    TextUtils.isEmpty(editTextReflection.text) || spinner.selectedItem.toString() == "choose emotion") {
                toastError("Missing field(s)")

            } else {

                dreamViewModel.update(id, editTextTitle.text.toString(), editTextContent.text.toString(),
                        editTextReflection.text.toString(), spinner.selectedItem.toString())
                finish()
            }
        }
    }

    private fun toastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun searchSpinner(){
        val searchMethod = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, emotions)
        searchMethod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = searchMethod
    }
}
