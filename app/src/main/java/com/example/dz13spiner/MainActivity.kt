package com.example.dz13spiner

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain:Toolbar
    private lateinit var spiner_RoleS:Spinner
    private lateinit var spinerText:String

    private var role=mutableListOf(
    "Должность",
    "Инженер",
    "Программист",
    "Оператор",
    "Аналитик",
    "Тестировщик",
    "Сисадмин"
    )

    private lateinit var nameET: EditText
    private lateinit var surNameET: EditText
    private lateinit var ageET: EditText

    private lateinit var saveBTN: Button
    private lateinit var listRoleLV: ListView
    private var persons: MutableList<Person> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Инициализация Тулбар
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = " Подбор персонала"
        toolbarMain.subtitle = " Вер.1.Главная страница"
        toolbarMain.setLogo(R.drawable.bd)

        //Привязка переменных
        nameET = findViewById(R.id.nameET)
        surNameET = findViewById(R.id.surNameET)
        ageET = findViewById(R.id.ageET)
        spiner_RoleS = findViewById(R.id.spiner_RoleS)
        saveBTN = findViewById(R.id.saveBTN)
        listRoleLV = findViewById(R.id.listRoleLV)

        // Лист Адаптер
        val listAdapter = ListAdapter(this, persons)

        //Инициализация Спинера
        spiner_RoleS=findViewById(R.id.spiner_RoleS)
        var adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            role
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner_RoleS.adapter=adapter

        // Преобразование Спинера в Текст
        val itemSelectListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    viev: View?,
                    position: Int,
                    id: Long) {
                    val item = parent?.getItemAtPosition(position) as String
                    spinerText = item
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        spiner_RoleS.onItemSelectedListener = itemSelectListener

        //Обработка кнопки Сохранить
        saveBTN.setOnClickListener {
            if (nameET.text.isEmpty() ||
                surNameET.text.isEmpty() ||
                ageET.text.isEmpty()
            ) return@setOnClickListener

            val personName = nameET.text.toString()
            val personSurName = surNameET.text.toString()
            val personAge = ageET.text.toString()
            val person = Person(personName, personSurName, personAge, spinerText)
            persons.add(person)
            listRoleLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            nameET.text.clear()
            surNameET.text.clear()
            ageET.text.clear()
            spiner_RoleS.setSelection(0)
        }

    }
    
    // Активация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Создан 29.11.2024",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}