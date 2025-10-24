package com.unsoed.responsi1mobile_h1d023030.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobile_h1d023030.R
import com.unsoed.responsi1mobile_h1d023030.model.Player

class PlayerAdapter(
    private val playerList: List<Player>,
    private val listener: OnPlayerClickListener
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    interface OnPlayerClickListener {
        fun onPlayerClick(player: Player)
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.tv_player_name)
        val playerNationality: TextView = itemView.findViewById(R.id.tv_player_nationality)
        val playerCardColor: CardView = itemView.findViewById(R.id.card_player_color)
        val playerCard: CardView = itemView.findViewById(R.id.card_player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]

        holder.playerName.text = player.name
        holder.playerNationality.text = player.nationality


        val color = when (player.position) {

            "Goalkeeper" -> "#FFD700"


            "Defender", "Defence", "Centre-Back", "Right-Back", "Left-Back" -> "#0000FF"


            "Midfielder", "Midfield", "Defensive Midfield", "Attacking Midfield", "Central Midfield" -> "#008000"


            "Attacker", "Forward", "Attack", "Offence", "Right Winger", "Centre-Forward" -> "#FF0000"


            else -> "#CCCCCC"
        }
        // ---------------------------------------------

        holder.playerCardColor.setCardBackgroundColor(Color.parseColor(color))

        holder.playerCard.setOnClickListener {
            listener.onPlayerClick(player)
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}