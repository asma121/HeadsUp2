package com.example.headsup2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    lateinit var addButton: Button
    lateinit var etname2: EditText
    lateinit var ettaboo12: EditText
    lateinit var ettaboo22: EditText
    lateinit var ettaboo32: EditText
    var newCele=""
    var T1=""
    var T2=""
    var T3=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        addButton=findViewById(R.id.addButton)
        etname2=findViewById(R.id.etname2)
        ettaboo12=findViewById(R.id.ettaboo12)
        ettaboo22=findViewById(R.id.ettaboo22)
        ettaboo32=findViewById(R.id.ettaboo32)

        addButton.setOnClickListener {
            newCele=etname2.text.toString()
            T1=ettaboo12.text.toString()
            T2=ettaboo22.text.toString()
            T3=ettaboo32.text.toString()
            val dbHelper=DbHelper(applicationContext)
            var status=dbHelper.addCelebrity(newCele,T1,T2,T3)
            Toast.makeText(applicationContext,"Celebrity Added "+status,Toast.LENGTH_LONG).show()
        }
    }
}