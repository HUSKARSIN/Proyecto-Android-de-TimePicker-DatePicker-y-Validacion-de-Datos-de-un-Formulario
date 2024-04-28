package com.example.timepickerydatepickercristhianandresmamanimujica
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val reloj = findViewById<Button>(R.id.Reloj)
        reloj.setOnClickListener {
            TimePickerFragment().show(supportFragmentManager, "timePicker")}
        val calendario = findViewById<Button>(R.id.Calendario)
        calendario.setOnClickListener {
            DatePickerFragment(this).show(supportFragmentManager, "datePicker") }
        val sharedPreferences: SharedPreferences = getSharedPreferences("mis preferencias", Context.MODE_PRIVATE)
        val guardar = findViewById<Button>(R.id.guardar)
        val mostrar = findViewById<Button>(R.id.mostrar)
        val caja_text = findViewById<EditText>(R.id.caja_text)
        val myButton = findViewById<Button>(R.id.myButton)
        val myButton2 = findViewById<Button>(R.id.myButton)
        val myForm = findViewById<LinearLayout>(R.id.myForm)
        myButton.setOnClickListener {
            if (myForm.visibility == View.GONE) {
                myForm.visibility = View.VISIBLE } else {
                myForm.visibility = View.GONE } }
        guardar.setOnClickListener{
            val palabra = caja_text.text.toString()
            with(sharedPreferences.edit()){
                putString("Clave",palabra)
                apply() } }
        mostrar.setOnClickListener{
            val llaveleida = sharedPreferences.getString("Clave","No encontrado")
            Toast.makeText(this,"La llave leida es: "+llaveleida,Toast.LENGTH_LONG).show() } }
    class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            return TimePickerDialog(
                activity,
                this,
                hour,
                minute,
                DateFormat.is24HourFormat(activity))}
        override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {} }
    class DatePickerFragment(val contexto: Context) : DialogFragment(),
        DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(contexto, this, year, month, day) }
        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            Toast.makeText(
                contexto,
                "El año es $year, meses son $month y dias son $day",
                Toast.LENGTH_LONG
            ).show() } } }