package com.example.assessment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val errorText: TextView = findViewById(R.id.errorText)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the ViewModel
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        viewModel.countries.observe(this) { countryList ->
            recyclerView.adapter = CountryAdapter(countryList)
        }

        viewModel.loading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            errorText.visibility = if (error != null) View.VISIBLE else View.GONE
            errorText.text = error
        }
    }
}
