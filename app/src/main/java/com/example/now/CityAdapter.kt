package com.example.now

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(
    private val context: Context,
    private val cities: List<City>,
    private val onClick: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.cityImage)
        private val name: TextView = itemView.findViewById(R.id.cityName)

        fun bind(city: City) {
            image.setImageResource(city.imageResId)
            name.text = city.name
            itemView.setOnClickListener { onClick(city) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_city, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CityMapActivity::class.java)
            intent.putExtra("city", city)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = cities.size
}