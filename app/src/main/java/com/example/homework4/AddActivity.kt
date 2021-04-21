package com.example.homework4

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddActivity: AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextContent: EditText
    private lateinit var editTextReflection: EditText
    private lateinit var spinner: Spinner
    private lateinit var buttonSave: Button
    var emotions = arrayOf("HOW DID YOU FEEL?", "fear", "panic", "loss of self", "grief", "freedom", "love", "joy", "vulnerability", "confused", "sad")

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


        // catch user input error in front end
        buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(editTextTitle.text) || TextUtils.isEmpty(editTextContent.text) ||
                    TextUtils.isEmpty(editTextReflection.text) || spinner.selectedItem.toString() == "HOW DID YOU FEEL?") {
                toastError("Missing field(s)")

            } else {
                // grab the text and make it into a Dream type
                // call the insert function to insert into my database
                val dream = Dream(0, editTextTitle.text.toString(), editTextContent.text.toString(),
                                    editTextReflection.text.toString(), spinner.selectedItem.toString())
                dreamViewModel.insert(dream)
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
