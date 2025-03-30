package com.example.now

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutesAdapter(private val routes: List<Route>) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val routeName: TextView = itemView.findViewById(R.id.routeName)
        val routeDescription: TextView = itemView.findViewById(R.id.routeDescription)
        val iconCafe: ImageView = itemView.findViewById(R.id.iconCafe)
        val iconMuseum: ImageView = itemView.findViewById(R.id.iconMuseum)
        val iconPark: ImageView = itemView.findViewById(R.id.iconPark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_route, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val route = routes[position]
        holder.routeName.text = route.name
        holder.routeDescription.text = route.description

        // Скрыть все иконки по умолчанию
        holder.iconCafe.visibility = View.GONE
        holder.iconMuseum.visibility = View.GONE
        holder.iconPark.visibility = View.GONE

        // Показать иконки для типов мест в маршруте
        route.places.forEach { place ->
            when (place.type) {
                PlaceType.CAFE -> holder.iconCafe.visibility = View.VISIBLE
                PlaceType.MUSEUM -> holder.iconMuseum.visibility = View.VISIBLE
                PlaceType.PARK -> holder.iconPark.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = routes.size
}