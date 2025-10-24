package com.unsoed.responsi1mobile_h1d023030

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobile_h1d023030.model.Coach
import com.unsoed.responsi1mobile_h1d023030.model.Player
import com.unsoed.responsi1mobile_h1d023030.model.TeamResponse
import com.unsoed.responsi1mobile_h1d023030.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var coachData: Coach? = null
    private var squadData: ArrayList<Player>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnHistory = findViewById<CardView>(R.id.btn_history)
        val btnCoach = findViewById<CardView>(R.id.btn_coach)
        val btnSquad = findViewById<CardView>(R.id.btn_squad)

        btnCoach.isEnabled = false
        btnSquad.isEnabled = false

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        btnCoach.setOnClickListener {
            val intent = Intent(this, CoachActivity::class.java)
            intent.putExtra("COACH_DATA", coachData)
            startActivity(intent)
        }

        btnSquad.setOnClickListener {
            val intent = Intent(this, SquadActivity::class.java)
            intent.putParcelableArrayListExtra("SQUAD_DATA", squadData)
            startActivity(intent)
        }


        val clubId = 73 // ID Klub Anda: Tottenham Hotspur FC

        val ivClubLogo = findViewById<ImageView>(R.id.iv_club_logo)
        val tvClubName = findViewById<TextView>(R.id.tv_club_name)
        val ivHeaderImage = findViewById<ImageView>(R.id.iv_header_image)
        val tvClubDescription = findViewById<TextView>(R.id.tv_club_description)

        ivHeaderImage.setImageResource(R.drawable._915818)

        tvClubDescription.text = "Tottenham Hotspur FC, didirikan pada tahun 1882, adalah salah satu klub paling bersejarah di London Utara. Dikenal dengan julukan 'Spurs' dan moto 'Audere Est Facere' (Berani Berbuat adalah Melakukan), Spurs adalah klub pertama di Inggris yang meraih gelar liga dan Piala FA di abad ke-20. Klub ini terkenal dengan filosofi sepak bola menyerang yang menarik dan modern."



        RetrofitClient.instance.getTeamDetail(clubId).enqueue(object : Callback<TeamResponse> {

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    val teamData = response.body()

                    coachData = teamData?.headCoach
                    squadData = ArrayList(teamData?.squad ?: emptyList())


                    tvClubName.text = teamData?.name


                    Glide.with(this@MainActivity)
                        .load(teamData?.crest)
                        .into(ivClubLogo)


                    btnCoach.isEnabled = true
                    btnSquad.isEnabled = true

                } else {
                    tvClubName.text = "Error: ${response.code()}"
                    Log.e("MainActivity", "Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                tvClubName.text = "Failure: ${t.message}"
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}