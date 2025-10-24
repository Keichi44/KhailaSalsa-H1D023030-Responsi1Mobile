package com.unsoed.responsi1mobile_h1d023030

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unsoed.responsi1mobile_h1d023030.model.Coach

class CoachActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coach)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val coachData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("COACH_DATA", Coach::class.java)
        } else {
            intent.getParcelableExtra<Coach>("COACH_DATA")
        }


        val tvCoachName = findViewById<TextView>(R.id.tv_coach_name)
        val tvCoachDob = findViewById<TextView>(R.id.tv_coach_dob)
        val tvCoachNationality = findViewById<TextView>(R.id.tv_coach_nationality)
        val ivCoachPhoto = findViewById<ImageView>(R.id.iv_coach_photo)


        if (coachData != null) {

            tvCoachName.text = coachData.name
            tvCoachDob.text = coachData.dateOfBirth
            tvCoachNationality.text = coachData.nationality
        } else {

            tvCoachName.text = "Data Pelatih Tidak Ditemukan"


            tvCoachDob.text = ""
            tvCoachNationality.text = ""

        }

        ivCoachPhoto.setImageResource(R.drawable.pelatih)

    }
}