package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.hardware.SensorEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var contador = 0;

    // equivalente al pedometer
    private lateinit var sensorManager: SensorManager;
    private var sensorPasos: Sensor? = null;

    private lateinit var tvContador: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        // importante
        // casi lo mismo que lo de flutter
        super.onCreate(savedInstanceState)
        // inicializa todo
        setContentView(R.layout.activity_main)


        tvContador = findViewById<TextView>(R.id.tvContador);
        val btnSumar = findViewById<Button>(R.id.btnSumar);

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
                100
            )

        } else {
            configurarSensor();

        }

        btnSumar.setOnClickListener {
            contador++;
            tvContador.text = "Numero de pasos: $contador";
        }

        val btnReinicar = findViewById<Button>(R.id.btnReiniciar)

        btnReinicar.setOnClickListener {
            contador = 0;
            tvContador.text = "Numero de pasos: $contador";
        }

        val btnDetalle = findViewById<Button>(R.id.btnDetalle)

        btnDetalle.setOnClickListener {
            val intent = Intent(this, Detail::class.java);

            intent.putExtra("PASOS", contador);
            startActivity(intent);

        }

        val btnActividades = findViewById<Button>(R.id.btnActividades)
        btnActividades.setOnClickListener {
            val intent1 = Intent(this, Actividades :: class.java);
            startActivity(intent1)
        }

        // var puede cambiar pero val se usa para objetos que no van
        // a cambiar, ya que no se reemplaza el elemento como tal

    }


    private fun configurarSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager;
        sensorPasos = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (sensorPasos == null) {
            tvContador.text = "Error al configurar el sensor de pasos";
        }

    }

    override fun onResume() {
        super.onResume()
        sensorPasos.let {
            sensorManager.registerListener(
                this, it, SensorManager.SENSOR_DELAY_UI
            )

        }

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val pasosTotales = event.values[0].toInt();
            tvContador.text = "Pasos de Hoy $pasosTotales"
        }

    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configurarSensor();
            } else {
                tvContador.text = "Permiso de sensor no concedido";
            }
        }


    }
}
