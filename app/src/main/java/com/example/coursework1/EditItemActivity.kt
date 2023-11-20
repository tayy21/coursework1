package com.example.coursework1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.app.Activity


class EditItemActivity : AppCompatActivity() {
    private lateinit var itemNameEditText: EditText
    private lateinit var itemDescriptionEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        // Initialize views
        itemNameEditText = findViewById(R.id.editTextNames)
        itemDescriptionEditText = findViewById(R.id.editTextItemDescription)
        saveButton = findViewById(R.id.buttonSaveItem)

        // Inside EditItemActivity in onCreate or similar method
        val itemName = intent.getStringExtra("item_name")
        val itemDescription = intent.getStringExtra("item_description")

// Populate the UI fields with the retrieved data for editing
        itemNameEditText.setText(itemName)
        itemDescriptionEditText.setText(itemDescription)

        // Set up click listener for the Save button
        saveButton.setOnClickListener {
            val editedItemName = itemNameEditText.text.toString()
            val editedItemDescription = itemDescriptionEditText.text.toString()

            // Prepare the result intent
            val resultIntent = Intent()
            resultIntent.putExtra("updated_item_name", editedItemName)
            resultIntent.putExtra("updated_item_description", editedItemDescription)

            // Set the result and finish the activity
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}