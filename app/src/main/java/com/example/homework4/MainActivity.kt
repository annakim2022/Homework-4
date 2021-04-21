package com.example.homework4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button:Button


    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        button = findViewById(R.id.button_add)

        val adapter = DreamListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        dreamViewModel.allDreams.observe(this, Observer {
            // update the cached copy of dreams in the adapter to it
                dreams -> dreams?.let{
            adapter.submitList(it)
        }
        })

        button.setOnClickListener{
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)

        }



    }
}