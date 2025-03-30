package com.example.now

import android.os.Build
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.now.databinding.ActivityCityMapBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView

class CityMapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var binding: ActivityCityMapBinding
    private lateinit var city: City

    // Фильтры
    private lateinit var filterCafe: CheckBox
    private lateinit var filterMuseum: CheckBox
    private lateinit var filterPark: CheckBox

    // Слушатель кликов по меткам
    private val mapObjectTapListener = MapObjectTapListener { mapObject, point ->
        Toast.makeText(this, "Кликнули на метку: ${point.latitude}, ${point.longitude}", Toast.LENGTH_SHORT).show()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        binding = ActivityCityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение города
        city = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("city", City::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("city")!!
        }

        // Инициализация элементов
        mapView = binding.mapView
        filterCafe = binding.filterCafe
        filterMuseum = binding.filterMuseum
        filterPark = binding.filterPark

        // Настройка карты
        mapView.mapWindow.map.move(
            CameraPosition(Point(city.latitude, city.longitude), 12f, 0f, 0f)
        )

        // Обработчики фильтров
        filterCafe.setOnCheckedChangeListener { _, _ -> updateMapFilters() }
        filterMuseum.setOnCheckedChangeListener { _, _ -> updateMapFilters() }
        filterPark.setOnCheckedChangeListener { _, _ -> updateMapFilters() }

        updateMapFilters()
    }

    private fun updateMapFilters() {
        val map = mapView.mapWindow.map
        map.mapObjects.clear()

        city.routes.forEach { route ->
            route.places.filter { place ->
                (filterCafe.isChecked && place.type == PlaceType.CAFE) ||
                        (filterMuseum.isChecked && place.type == PlaceType.MUSEUM) ||
                        (filterPark.isChecked && place.type == PlaceType.PARK)
            }.forEach { place ->
                // Добавляем метку без кастомизации
                val placemark = map.mapObjects.addPlacemark(Point(place.lat, place.lon))

                // Добавляем слушатель кликов
                placemark.addTapListener(mapObjectTapListener)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}