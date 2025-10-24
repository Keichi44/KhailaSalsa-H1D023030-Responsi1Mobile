package com.unsoed.responsi1mobile_h1d023030

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobile_h1d023030.adapter.PlayerAdapter
import com.unsoed.responsi1mobile_h1d023030.model.Player

class SquadActivity : AppCompatActivity(), PlayerAdapter.OnPlayerClickListener {

    private lateinit var cardPlayerDetail: CardView
    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailDob: TextView
    private lateinit var tvDetailPosition: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_squad)

        val mainLayout = findViewById<ConstraintLayout>(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val squadList: ArrayList<Player>? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableArrayListExtra("SQUAD_DATA", Player::class.java)
        } else {
            intent.getParcelableArrayListExtra<Player>("SQUAD_DATA")
        }


        val rvPlayers = findViewById<RecyclerView>(R.id.rv_players)

        cardPlayerDetail = findViewById(R.id.card_player_detail)
        tvDetailName = findViewById(R.id.tv_detail_name)
        tvDetailDob = findViewById(R.id.tv_detail_dob)
        tvDetailPosition = findViewById(R.id.tv_detail_position)


        if (squadList != null && squadList.isNotEmpty()) {


            squadList.sortBy { player ->
                when (player.position) {
                    // 1. Goalkeeper
                    "Goalkeeper" -> 1

                    // 2. Defender
                    "Defender", "Defence", "Centre-Back", "Right-Back", "Left-Back" -> 2

                    // 3. Midfielder
                    "Midfielder", "Midfield", "Defensive Midfield", "Attacking Midfield", "Central Midfield" -> 3

                    // 4. Attacker
                    "Attacker", "Forward", "Attack", "Offence", "Right Winger", "Centre-Forward" -> 4

                    // 5. Lainnya
                    else -> 5
                }
            }

            // Buat Adapter-nya (sekarang dengan squadList yang sudah terurut)
            val playerAdapter = PlayerAdapter(squadList, this)
            rvPlayers.adapter = playerAdapter
            rvPlayers.layoutManager = LinearLayoutManager(this)

        } else {

        }


        mainLayout.setOnClickListener {
            cardPlayerDetail.visibility = View.GONE
        }

        cardPlayerDetail.setOnClickListener {

        }
    }


    override fun onPlayerClick(player: Player) {


        val color = when (player.position) {
            "Goalkeeper" -> "#FFD700"
            "Defender", "Defence", "Centre-Back", "Right-Back", "Left-Back" -> "#87CEEB"
            "Midfielder", "Midfield", "Defensive Midfield", "Attacking Midfield", "Central Midfield" -> "#90EE90"
            "Attacker", "Forward", "Attack", "Offence", "Right Winger", "Centre-Forward" -> "#FA8072"
            else -> "#888888"
        }

        cardPlayerDetail.setCardBackgroundColor(Color.parseColor(color))

        tvDetailName.text = player.name
        tvDetailDob.text = player.dateOfBirth
        tvDetailPosition.text = player.position ?: "N/A"


        cardPlayerDetail.visibility = View.VISIBLE
    }
}