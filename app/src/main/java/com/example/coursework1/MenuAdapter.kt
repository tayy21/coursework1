package com.example.coursework1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class MenuAdapter(private val menuItems: List<MenuItem>, private val clickListener: ClickListener) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item_layout, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.bind(menuItem, clickListener)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.textViewItemName)
        private val itemDescriptionTextView: TextView = itemView.findViewById(R.id.textViewItemDescription)
        private val editButton: Button = itemView.findViewById(R.id.buttonEditItem)
        private val deleteButton: Button = itemView.findViewById(R.id.buttonDeleteItem)

        fun bind(menuItem: MenuItem, clickListener: ClickListener) {
            itemNameTextView.text = menuItem.itemName
            itemDescriptionTextView.text = menuItem.itemDescription

            // Implement click listeners for edit and delete buttons
            editButton.setOnClickListener {
                // Handle edit button click
                // For example, you can open an edit screen with the details of the selected item
                clickListener.onItemClick(menuItem)
                val editIntent = Intent(itemView.context, EditItemActivity::class.java)
                editIntent.putExtra("item_name", menuItem.itemName)
                editIntent.putExtra("item_description", menuItem.itemDescription)
                itemView.context.startActivity(editIntent)
            }

            deleteButton.setOnClickListener {
                // Handle delete button click
                // For example, you can show a confirmation dialog and delete the item if confirmed

            }
        }
    }
}
