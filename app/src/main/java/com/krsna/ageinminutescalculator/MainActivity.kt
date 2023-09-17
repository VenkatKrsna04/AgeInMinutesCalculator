package com.krsna.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateText: TextView? = null

    private var resultText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSelectDate: Button = findViewById(R.id.buttonSelectDate)
        selectedDateText = findViewById(R.id.selectedDateText)
        resultText = findViewById(R.id.resultText)

        buttonSelectDate.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        /**
         * DatePickerDialog Implementation
         */

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, { _, year, month, dayOfMonth ->

                Toast.makeText(
                    this, "Year:$year , Month:${month + 1}, day:$dayOfMonth", Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$dayOfMonth/${month + 1}/$year"

                selectedDateText?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val date = simpleDateFormat.parse(selectedDate)

                date?.let {
                    val selectedDateInMinutes = date.time / 60000

                    val currentDate =
                        simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                    currentDate?.let {

                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        resultText?.text = differenceInMinutes.toString()
                    }
                }
            }, year, month, day
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000

        datePickerDialog.show()

    }
}