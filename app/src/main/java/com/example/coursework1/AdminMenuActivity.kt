package com.example.coursework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

class AdminMenuActivity : AppCompatActivity(), ClickListener {

    private lateinit var menuItems: MutableList<MenuItem>
    private lateinit var recyclerView: RecyclerView
    private val editItemActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                handleEditItemResult(data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        recyclerView = findViewById(R.id.recyclerViewMenu)
        recyclerView.layoutManager = LinearLayoutManager(this)

        menuItems = mutableListOf(
            MenuItem("Drink 1", "Refreshing beverage"),
            MenuItem("Cake 1", "Delicious dessert"),
            // Add more menu items as needed
        )

        val menuAdapter = MenuAdapter(menuItems, this) // Pass 'this' as the ClickListener
        recyclerView.adapter = menuAdapter
    }

    override fun onItemClick(clickedItem: MenuItem) {
        val editIntent = Intent(this, EditItemActivity::class.java)
        editIntent.putExtra("item_name", clickedItem.itemName)
        editIntent.putExtra("item_description", clickedItem.itemDescription)
        editItemActivityResultLauncher.launch(editIntent)
    }


    private fun handleEditItemResult(data: Intent?) {
        if (data != null) {
            val updatedItemName = data.getStringExtra("updated_item_name")
            val updatedItemDescription = data.getStringExtra("updated_item_description")


            if (updatedItemName != null && updatedItemDescription != null) {
                val indexOfItemToUpdate = menuItems.indexOfFirst { it.itemName == updatedItemName }

                if (indexOfItemToUpdate != -1) {
                    menuItems[indexOfItemToUpdate] = MenuItem(updatedItemName, updatedItemDescription)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }
}