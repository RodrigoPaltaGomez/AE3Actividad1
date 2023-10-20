package com.ae3actividad1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var rutEditText: EditText
    private lateinit var nombreEditText: EditText
    private lateinit var correoEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rutEditText = findViewById(R.id.rut)
        nombreEditText = findViewById(R.id.nombre)
        correoEditText = findViewById(R.id.correo)

        val BotonGuardar: Button = findViewById(R.id.btnguardar)
        val BotonBuscar: Button = findViewById(R.id.btnbuscar)

        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        BotonGuardar.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("${rutEditText.text}_name", nombreEditText.text.toString())
            editor.putString("${rutEditText.text}_email", correoEditText.text.toString())
            editor.apply()

            rutEditText.setText("")
            nombreEditText.setText("")
            correoEditText.setText("")
        }

        BotonBuscar.setOnClickListener {
            val rut = rutEditText.text.toString()
            val name = sharedPreferences.getString("${rut}_name", "")
            val email = sharedPreferences.getString("${rut}_email", "")

            if (name?.length == 0 || email?.length == 0) {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                    .setTitle("Alerta")
                    .setMessage("Usuario no encontrado.")
                    .setPositiveButton("Ok", null)
                    .create()
                alertDialog.show()
            } else {
                nombreEditText.setText(name)
                correoEditText.setText(email)
            }
        }
    }
}
