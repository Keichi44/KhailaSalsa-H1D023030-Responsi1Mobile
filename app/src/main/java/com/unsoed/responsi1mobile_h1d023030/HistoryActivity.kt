package com.unsoed.responsi1mobile_h1d023030

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivHistoryHeader = findViewById<ImageView>(R.id.iv_history_header)
        val tvHistoryTitle = findViewById<TextView>(R.id.tv_history_title)
        val tvHistoryContent = findViewById<TextView>(R.id.tv_history_content)

        ivHistoryHeader.setImageResource(R.drawable.foto)

        tvHistoryTitle.text = "Tottenham Hotspur F.C. History"


        val historyText = """
            Tottenham Hotspur Football Club didirikan pada tahun 1882, bermula dari sekelompok anak sekolah di All Hallows Church. Klub ini dikenal dengan julukan 'Spurs' dan moto "Audere Est Facere" (Berani Berbuat adalah Melakukan).
           
            Era The Double (1960-an):
            Prestasi paling bersejarah terjadi pada musim 1960–61, ketika Spurs menjadi klub Inggris pertama di abad ke-20 yang memenangkan <i>The Double</i> (gelar Divisi Pertama dan Piala FA di musim yang sama). Di bawah kepemimpinan Bill Nicholson, Spurs kemudian menjadi tim Inggris pertama yang memenangkan trofi Eropa (European Cup Winners' Cup) pada tahun 1963.
           
            
            Periode 1980-an hingga 2000-an
            Klub ini terus meraih kesuksesan di kompetisi piala, memenangkan beberapa Piala FA dan Piala Liga. Periode ini menekankan pada tradisi klub untuk selalu memainkan sepak bola yang menarik dan menyerang.
           
  
            Era Modern (2010 – Saat Ini 2025):
            Tottenham menjadi kekuatan tetap di Liga Primer dan sering bersaing di Liga Champions UEFA. Pada tahun 2019, Spurs mencapai final Liga Champions UEFA untuk pertama kalinya dalam sejarah klub. Transformasi besar terjadi dengan pembukaan stadion baru, <b>Tottenham Hotspur Stadium</b>, pada tahun 2019, yang dianggap sebagai salah satu stadion sepak bola terbaik di dunia. 
            
            Meskipun masih mengejar gelar domestik besar pertamanya sejak 2008, Spurs tetap menjadi salah satu tim papan atas di Liga Primer di bawah kepemimpinan pelatih seperti Ange Postecoglou (2025). Filosofi klub tetap berakar pada sepak bola menyerang yang menarik bagi penggemar.
        """.trimIndent()

        tvHistoryContent.text = historyText
    }
}