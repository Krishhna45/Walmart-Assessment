package com.example.assessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameRegionTextView: TextView = itemView.findViewById(R.id.nameRegionTextView)
        val codeTextView: TextView = itemView.findViewById(R.id.codeTextView)
        val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.nameRegionTextView.text = "${country.name}, ${country.region}"
        holder.codeTextView.text = country.code
        holder.capitalTextView.text = country.capital
    }

    override fun getItemCount(): Int = countries.size
}
