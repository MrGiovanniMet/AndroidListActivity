package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.google.android.material.card.MaterialCardView

class Actividades : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_actividades)

        val tvActividades = findViewById<TextView>(R.id.tvActividades);
        val btnRegresar = findViewById<Button>(R.id.btnRegresar1);



        btnRegresar.setOnClickListener {
            finish();
        }

        val natacion = findViewById<MaterialCardView>(R.id.Natacion)
        val natacionText = findViewById<TextView>(R.id.NatacionTexto)
        val comida = findViewById<MaterialCardView>(R.id.Comida)
        val comidaText = findViewById<TextView>(R.id.ComidaText)
        val gym = findViewById<MaterialCardView>(R.id.Gym)
        val gymText = findViewById<TextView>(R.id.GymTexto)

        comida.setOnClickListener {
            comida.isChecked = !comida.isChecked
        }
        natacion.setOnClickListener {
            natacion.isChecked = !natacion.isChecked
        }
        gym.setOnClickListener {
            gym.isChecked = !gym.isChecked
        }

            /*if (natacion.isChecked) {
                natacion.setCardBackgroundColor(Color.GREEN)
                natacionText.text = "¡Completado!"
            } else {
                natacion.setCardBackgroundColor(Color.WHITE)
                natacionText.text = "Pendiente"
            }
            */

        }
    }


