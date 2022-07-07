package ru.itis.bozor

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.bozor.databinding.ActivityMainBinding
import ru.itis.bozor.room.model.ShopListAdapter
import ru.itis.bozor.room.model.ShopListListener
import ru.itis.bozor.room.model.ShopListsService



class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var controller: NavController
    private lateinit var preferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val bottomView = binding.bNav
        bottomView.setupWithNavController(controller)


    }



}

/* TODO
        3) Shop - каталог продуктов разбитый на категории
        4) List открывает экран со списком покупок пользователя (создать список, посмотреть историю, отслеживать текущий)
        5) Profile - информация о пользователе:
           5.1.) Account: Name, Login, Password, Payment, Location,
           5.2.) Setting: Notification, Language, Support, Security, AppInfo
        6) В верхнем меню есть 2 пункта: Search/Filter и Purchase
        7) В пункте Purchase вы видите товары добавленные в корзину и можете заказать их
        8) Search/Filter - ищет нужный продукт
        9) У товара будет индикатор его состояния (доступен или нет) и кнопки: AddToBuy, AddToFavorite, Rate */