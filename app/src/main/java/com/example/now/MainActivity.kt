package com.example.now

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.now.ui.theme.NowTheme
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val cities = listOf(
        City(
            id = 1,
            name = "Москва",
            imageResId = R.drawable.moscow,
            latitude = 55.7558,
            longitude = 37.6176,
            routes = listOf(
                Route("Красная площадь", "Исторический центр",
                    listOf(Point(55.7539, 37.6208), Point(55.7525, 37.6230)),
                    listOf(Place("ГУМ", PlaceType.CAFE, 55.7549, 37.6218))),

                Route("ВДНХ", "Выставочный комплекс",
                    listOf(Point(55.8235, 37.6387), Point(55.8259, 37.6363)),
                    listOf(Place("Павильон Космос", PlaceType.MUSEUM, 55.8250, 37.6375))),

                Route("Арбат", "Пешеходная улица",
                    listOf(Point(55.7496, 37.5915), Point(55.7512, 37.5943)),
                    listOf(Place("Кафе Пушкинъ", PlaceType.CAFE, 55.7501, 37.5928))),

                Route("Парк Горького", "Парк культуры",
                    listOf(Point(55.7297, 37.6039), Point(55.7312, 37.6055)),
                    listOf(Place("Зеленое кафе", PlaceType.CAFE, 55.7305, 37.6043))),

                Route("Кремль", "Исторический комплекс",
                    listOf(Point(55.7517, 37.6178), Point(55.7526, 37.6189)),
                    listOf(Place("Оружейная палата", PlaceType.MUSEUM, 55.7500, 37.6166)))
            )
        ),

        City(
            id = 2,
            name = "Санкт-Петербург",
            imageResId = R.drawable.spb,
            latitude = 59.9343,
            longitude = 30.3351,
            routes = listOf(
                Route(
                    name = "Петропавловская крепость",
                    description = "Исторический комплекс",
                    points = listOf(
                        Point(59.9501, 30.3165)
                    ),
                    places = listOf(
                        Place("Собор Петра и Павла", PlaceType.MUSEUM, 59.9500, 30.3166)
                    )
                ),
                Route(
                    name = "Летний сад",
                    description = "Старейший парк",
                    points = listOf(
                        Point(59.9452, 30.3357)
                    ),
                    places = listOf(
                        Place("Кофейня в саду", PlaceType.CAFE, 59.9448, 30.3360)
                    )
                ),
                Route(
                    name = "Крейсер Аврора",
                    description = "Корабль-музей",
                    points = listOf(
                        Point(59.9553, 30.3377)
                    ),
                    places = listOf(
                        Place("Палуба Авроры", PlaceType.MUSEUM, 59.9552, 30.3379)
                    )
                )
            )
        ),

        City(
             id = 3,
             name = "Новосибирск",
             imageResId = R.drawable.novosibirsk,
             latitude = 55.0084,
             longitude = 82.9357,
             routes = listOf(
                 Route(
                     name = "Театр оперы и балета",
                     description = "Крупнейший театр Сибири",
                     points = listOf(
                         Point(55.0296, 82.9201),
                         Point(55.0290, 82.9195)
                     ),
                     places = listOf(
                         Place("Кафе у театра", PlaceType.CAFE, 55.0292, 82.9198)
                     )
                 ),
                 Route(
                     name = "Новосибирский зоопарк",
                     description = "Один из крупнейших зоопарков России",
                     points = listOf(
                         Point(55.0591, 82.9134),
                         Point(55.0603, 82.9147)
                     ),
                     places = listOf(
                         Place("Кафе «ЗооГрад»", PlaceType.CAFE, 55.0595, 82.9138)
                     )
                 ),
                 Route(
                     name = "Академгородок",
                     description = "Научный центр Сибирского отделения РАН",
                     points = listOf(
                         Point(54.8445, 83.0953),
                         Point(54.8452, 83.0967)
                     ),
                     places = listOf(
                         Place("Парк «Университетский»", PlaceType.PARK, 54.8448, 83.0960)
                     )
                 ),
                 Route(
                     name = "Парк «Берёзовая роща»",
                     description = "Зона отдыха в центре города",
                     points = listOf(
                         Point(55.0432, 82.9276),
                         Point(55.0425, 82.9289)
                     ),
                     places = listOf(
                         Place("Кафе «Лесная поляна»", PlaceType.CAFE, 55.0428, 82.9282)
                     )
                 ),
                 Route(
                     name = "Музей железнодорожной техники",
                     description = "Экспозиция старинных поездов",
                     points = listOf(
                         Point(55.0367, 82.8964),
                         Point(55.0372, 82.8971)
                     ),
                     places = listOf(
                         Place("Музейный киоск", PlaceType.MUSEUM, 55.0369, 82.8967)
                     )
                 )
             )
        ),

        City(
             id = 4,
             name = "Екатеринбург",
             imageResId = R.drawable.ekaterinburg,
             latitude = 56.8389,
             longitude = 60.6057,
             routes = listOf(
                 Route(
                     name = "Плотина городского пруда",
                     description = "Историческое сердце города",
                     points = listOf(
                         Point(56.8422, 60.6054),
                         Point(56.8415, 60.6068)
                     ),
                     places = listOf(
                         Place("Кафе «У плотины»", PlaceType.CAFE, 56.8418, 60.6059)
                     )
                 ),
                 Route(
                     name = "Храм на Крови",
                     description = "Памятник на месте дома Ипатьева",
                     points = listOf(
                         Point(56.8435, 60.6142),
                         Point(56.8430, 60.6135)
                     ),
                     places = listOf(
                         Place("Музей истории", PlaceType.MUSEUM, 56.8432, 60.6138)
                     )
                 ),
                 Route(
                     name = "Улица Вайнера",
                     description = "Пешеходная улица с магазинами",
                     points = listOf(
                         Point(56.8376, 60.5978),
                         Point(56.8382, 60.5991)
                     ),
                     places = listOf(
                         Place("Кофейня «Арт-Вайнер»", PlaceType.CAFE, 56.8379, 60.5985)
                     )
                 ),
                 Route(
                     name = "Музей истории Екатеринбурга",
                     description = "Экспозиция о развитии города",
                     points = listOf(
                         Point(56.8350, 60.6127),
                         Point(56.8354, 60.6132)
                     ),
                     places = listOf(
                         Place("Музейное кафе", PlaceType.CAFE, 56.8352, 60.6130)
                     )
                 ),
                 Route(
                     name = "Парк «Зелёная Роща»",
                     description = "Зона отдыха с озёрами",
                     points = listOf(
                         Point(56.8273, 60.6225),
                         Point(56.8268, 60.6239)
                     ),
                     places = listOf(
                         Place("Кафе «У озера»", PlaceType.CAFE, 56.8270, 60.6232)
                     )
                 )
             )
        ),

        City(
            id = 5,
            name = "Казань",
            imageResId = R.drawable.kazan,
            latitude = 55.7961,
            longitude = 49.1064,
            routes = listOf(
                Route(
                    name = "Казанский Кремль",
                    description = "Объект Всемирного наследия ЮНЕСКО",
                    points = listOf(
                        Point(55.7981, 49.1053),
                        Point(55.8002, 49.1075)
                    ),
                    places = listOf(
                        Place("Мечеть Кул-Шариф", PlaceType.MUSEUM, 55.7985, 49.1050),
                        Place("Музей ислама", PlaceType.MUSEUM, 55.7990, 49.1062)
                    )
                ),
                Route(
                    name = "Улица Баумана",
                    description = "Пешеходная улица с историей",
                    points = listOf(
                        Point(55.7905, 49.1148),
                        Point(55.7932, 49.1176)
                    ),
                    places = listOf(
                        Place("Кафе «Дом чая»", PlaceType.CAFE, 55.7918, 49.1160)
                    )
                ),
                Route(
                    name = "Храм всех религий",
                    description = "Уникальный архитектурный комплекс",
                    points = listOf(
                        Point(55.7602, 49.1573),
                        Point(55.7608, 49.1581)
                    ),
                    places = listOf(
                        Place("Парк у храма", PlaceType.PARK, 55.7605, 49.1577)
                    )
                ),
                Route(
                    name = "Озеро Кабан",
                    description = "Живописная зона отдыха",
                    points = listOf(
                        Point(55.7819, 49.1387),
                        Point(55.7834, 49.1402)
                    ),
                    places = listOf(
                        Place("Кафе «У озера»", PlaceType.CAFE, 55.7825, 49.1395)
                    )
                ),
                Route(
                    name = "Центр семьи «Казан»",
                    description = "Дворец бракосочетаний в форме котла",
                    points = listOf(
                        Point(55.8173, 49.1225),
                        Point(55.8178, 49.1233)
                    ),
                    places = listOf(
                        Place("Музей семьи", PlaceType.MUSEUM, 55.8175, 49.1229)
                    )
                )
            )
        ),

        City(
            id = 6,
            name = "Нижний Новгород",
            imageResId = R.drawable.nizhny_novgorod,
            latitude = 56.3287,
            longitude = 44.0020,
            routes = listOf(
                Route(
                    name = "Нижегородский Кремль",
                    description = "Исторический центр города",
                    points = listOf(
                        Point(56.3278, 44.0045),
                        Point(56.3291, 44.0062)
                    ),
                    places = listOf(
                        Place("Кафе «Кремлёвский дворик»", PlaceType.CAFE, 56.3283, 44.0050)
                    )
                ),
                Route(
                    name = "Чкаловская лестница",
                    description = "Самая длинная лестница в России",
                    points = listOf(
                        Point(56.3302, 44.0087),
                        Point(56.3325, 44.0123)
                    ),
                    places = listOf(
                        Place("Парк «Верхневолжский»", PlaceType.PARK, 56.3310, 44.0105)
                    )
                ),
                Route(
                    name = "Улица Большая Покровская",
                    description = "Пешеходная улица с памятниками",
                    points = listOf(
                        Point(56.3174, 44.0007),
                        Point(56.3201, 43.9985)
                    ),
                    places = listOf(
                        Place("Кафе «Покровский дворик»", PlaceType.CAFE, 56.3188, 43.9996)
                    )
                ),
                Route(
                    name = "Нижегородская ярмарка",
                    description = "Исторический выставочный комплекс",
                    points = listOf(
                        Point(56.3356, 43.9337),
                        Point(56.3362, 43.9345)
                    ),
                    places = listOf(
                        Place("Музей ярмарки", PlaceType.MUSEUM, 56.3359, 43.9341)
                    )
                ),
                Route(
                    name = "Парк «Швейцария»",
                    description = "Крупнейший парк города",
                    points = listOf(
                        Point(56.3095, 43.9854),
                        Point(56.3108, 43.9872)
                    ),
                    places = listOf(
                        Place("Кафе «Лесная сказка»", PlaceType.CAFE, 56.3101, 43.9863)
                    )
                )
            )
        ),

        City(
            id = 7,
            name = "Челябинск",
            imageResId = R.drawable.chelyabinsk,
            latitude = 55.1599,
            longitude = 61.4026,
            routes = listOf(
                Route(
                    name = "Парк Гагарина",
                    description = "Центральный парк культуры и отдыха",
                    points = listOf(
                        Point(55.1612, 61.3927),
                        Point(55.1625, 61.3943)
                    ),
                    places = listOf(
                        Place("Кафе «Лукоморье»", PlaceType.CAFE, 55.1618, 61.3935)
                    )
                ),
                Route(
                    name = "Кировка",
                    description = "Пешеходная улица с историческими зданиями",
                    points = listOf(
                        Point(55.1667, 61.4011),
                        Point(55.1679, 61.4035)
                    ),
                    places = listOf(
                        Place("Музей истории", PlaceType.MUSEUM, 55.1673, 61.4023)
                    )
                ),
                Route(
                    name = "Театр оперы и балета",
                    description = "Крупнейший театр Урала",
                    points = listOf(
                        Point(55.1634, 61.4088),
                        Point(55.1638, 61.4095)
                    ),
                    places = listOf(
                        Place("Кофейня «Артист»", PlaceType.CAFE, 55.1636, 61.4091)
                    )
                ),
                Route(
                    name = "Торговый комплекс «Синегорье»",
                    description = "Крупнейший ТЦ в регионе",
                    points = listOf(
                        Point(55.1555, 61.3852),
                        Point(55.1561, 61.3867)
                    ),
                    places = listOf(
                        Place("Ресторан «Синяя птица»", PlaceType.CAFE, 55.1558, 61.3859)
                    )
                ),
                Route(
                    name = "Челябинский краеведческий музей",
                    description = "Экспозиция об истории Южного Урала",
                    points = listOf(
                        Point(55.1601, 61.3975),
                        Point(55.1606, 61.3982)
                    ),
                    places = listOf(
                        Place("Музейное кафе", PlaceType.CAFE, 55.1603, 61.3978)
                    )
                )
            )
        ),

        City(
            id = 8,
            name = "Самара",
            imageResId = R.drawable.samara,
            latitude = 53.1959,
            longitude = 50.1002,
            routes = listOf(
                Route(
                    name = "Набережная Волги",
                    description = "Самая длинная набережная в России",
                    points = listOf(
                        Point(53.1984, 50.0957),
                        Point(53.2001, 50.0932)
                    ),
                    places = listOf(
                        Place("Кафе «Волжские дали»", PlaceType.CAFE, 53.1992, 50.0945)
                    )
                ),
                Route(
                    name = "Площадь Куйбышева",
                    description = "Крупнейшая площадь Европы",
                    points = listOf(
                        Point(53.1911, 50.0978),
                        Point(53.1923, 50.0965)
                    ),
                    places = listOf(
                        Place("Музей модерна", PlaceType.MUSEUM, 53.1917, 50.0971)
                    )
                ),
                Route(
                    name = "Бункер Сталина",
                    description = "Подземный музей времён ВОВ",
                    points = listOf(
                        Point(53.1876, 50.0872),
                        Point(53.1879, 50.0878)
                    ),
                    places = listOf(
                        Place("Кафе «История»", PlaceType.CAFE, 53.1877, 50.0875)
                    )
                ),
                Route(
                    name = "Жигулёвские ворота",
                    description = "Живописный участок Волги",
                    points = listOf(
                        Point(53.4123, 49.4987),
                        Point(53.4130, 49.4995)
                    ),
                    places = listOf(
                        Place("Кафе «У причала»", PlaceType.CAFE, 53.4126, 49.4991)
                    )
                ),
                Route(
                    name = "Парк имени Гагарина",
                    description = "Парк с аттракционами и зоопарком",
                    points = listOf(
                        Point(53.2055, 50.1503),
                        Point(53.2068, 50.1519)
                    ),
                    places = listOf(
                        Place("Кафе «Космос»", PlaceType.CAFE, 53.2061, 50.1511)
                    )
                )
            )
        ),

        City(
            id = 9,
            name = "Омск",
            imageResId = R.drawable.omsk,
            latitude = 54.9914,
            longitude = 73.3645,
            routes = listOf(
                Route(
                    name = "Омская крепость",
                    description = "Исторический комплекс XVIII века",
                    points = listOf(
                        Point(54.9876, 73.3692),
                        Point(54.9883, 73.3701)
                    ),
                    places = listOf(
                        Place("Музей крепости", PlaceType.MUSEUM, 54.9879, 73.3696)
                    )
                ),
                Route(
                    name = "Театр драмы",
                    description = "Один из старейших театров Сибири",
                    points = listOf(
                        Point(54.9845, 73.3784),
                        Point(54.9849, 73.3790)
                    ),
                    places = listOf(
                        Place("Кафе «Театральное»", PlaceType.CAFE, 54.9847, 73.3787)
                    )
                ),
                Route(
                    name = "Парк Победы",
                    description = "Мемориальный комплекс",
                    points = listOf(
                        Point(54.9742, 73.3917),
                        Point(54.9750, 73.3925)
                    ),
                    places = listOf(
                        Place("Парковое кафе", PlaceType.CAFE, 54.9746, 73.3921)
                    )
                ),
                Route(
                    name = "Набережная Иртыша",
                    description = "Живописная зона отдыха",
                    points = listOf(
                        Point(54.9953, 73.3578),
                        Point(54.9961, 73.3584)
                ),
                    places = listOf(
                        Place("Кафе «Речной бриз»", PlaceType.CAFE, 54.9957, 73.3581)
                    )
                ),
                Route(
                    name = "Любинский проспект",
                    description = "Пешеходная улица в центре",
                    points = listOf(
                        Point(54.9851, 73.3723),
                        Point(54.9865, 73.3740)
                ),
                    places = listOf(
                        Place("Кофейня «Любава»", PlaceType.CAFE, 54.9858, 73.3732)
                    )
                )
            )
        ),

        City(
            id = 10,
            name = "Ростов-на-Дону",
            imageResId = R.drawable.rostov_na_donu,
            latitude = 47.2225,
            longitude = 39.7187,
            routes = listOf(
                Route(
                    name = "Театральная площадь",
                    description = "Центральная площадь города",
                    points = listOf(
                        Point(47.2220, 39.7165),
                        Point(47.2228, 39.7173)
                    ),
                    places = listOf(
                        Place("Кафе «У фонтана»", PlaceType.CAFE, 47.2224, 39.7169)
                    )
                ),
                Route(
                    name = "Набережная Дона",
                    description = "Популярное место для прогулок",
                    points = listOf(
                        Point(47.2178, 39.7132),
                        Point(47.2185, 39.7140)
                    ),
                    places = listOf(
                        Place("Ресторан «Рыбацкий причал»", PlaceType.CAFE, 47.2181, 39.7136)
                    )
                ),
                Route(
                    name = "Парк Горького",
                    description = "Зелёная зона в центре города",
                    points = listOf(
                        Point(47.2284, 39.7076),
                        Point(47.2291, 39.7084)
                    ),
                    places = listOf(
                        Place("Кафе «Парковое»", PlaceType.CAFE, 47.2287, 39.7080)
                    )
                ),
                Route(
                    name = "Собор Рождества Пресвятой Богородицы",
                    description = "Главный храм города",
                    points = listOf(
                        Point(47.2203, 39.7058),
                        Point(47.2209, 39.7065)
                    ),
                    places = listOf(
                        Place("Музей религии", PlaceType.MUSEUM, 47.2206, 39.7061)
                    )
                ),
                Route(
                    name = "Ростовский зоопарк",
                    description = "Один из крупнейших зоопарков России",
                    points = listOf(
                        Point(47.2356, 39.7312),
                        Point(47.2363, 39.7320)
                    ),
                    places = listOf(
                        Place("Кафе «Зооград»", PlaceType.CAFE, 47.2359, 39.7316)
                    )
                )
            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.citiesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val adapter = CityAdapter(this, cities) { city ->
            val intent = Intent(this, CityMapActivity::class.java)
            intent.putExtra("city", city)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}

