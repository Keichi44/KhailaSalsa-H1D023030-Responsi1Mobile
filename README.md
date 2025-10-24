Responsi 1 - Prak. Pemrograman Mobile 
Khaila Salsa Marfah Bilqis
H1D023030
Shift H / Shift C 

Demo Aplikasi 
![Demo Aplikasi ](record/DemoAplikasi.mp4)

Penjelasan Alur Data Aplikasi Responsi 1 Mobile: Aplikasi ini menggunakan arsitektur klien-server untuk mengambil data klub sepakbola Tottenham Hotspur FC (ID 73) dari API pihak ketiga (football-data.org) dan menampilkannya di tiga layar utama.

1. Pemanggilan Data dari API (MainActivity)
Inisiasi: Saat MainActivity dibuat, aplikasi membuat objek RetrofitClient yang sudah dikonfigurasi dengan URL dasar dan converter GSON.
Permintaan API: Aplikasi segera menjalankan permintaan HTTP GET secara asinkron (non-blocking) ke endpoint: https://api.football-data.org/v4/teams/73.
Autentikasi: Permintaan ini diotentikasi dengan menyertakan header X-Auth-Token, yang berisi token API unik Anda.
Penerimaan Data: Jika koneksi berhasil (onResponse dengan response.isSuccessful), data JSON yang diterima secara otomatis diubah oleh GSON menjadi objek Kotlin TeamResponse.
Penyimpanan Data Global: Data utama dipecah dan disimpan ke variabel global Activity (coachData dan squadData):
coachData: Menerima data pelatih. Untuk ID 73, nilai ini adalah null dari API.
squadData: Menerima daftar pemain (yang kemudian diubah menjadi ArrayList<Player>).
Aktivasi UI: Setelah data tersimpan, tombol navigasi ("Head Coach" dan "Team Squad") diaktifkan (.isEnabled = true), mencegah pengguna mengklik sebelum data siap.

2. Alur Data Halaman Skuad Tim (Team Squad)
Halaman ini menunjukkan alur data yang paling kompleks, termasuk pengurutan dan pewarnaan kondisional.
Pengiriman Data: Saat tombol "Team Squad" diklik, MainActivity mengirimkan squadData (sebagai ArrayList<Player>) melalui Intent menggunakan fungsi putParcelableArrayListExtra.
Penerimaan Data: SquadActivity menerima daftar pemain tersebut sebagai squadList.
Pengurutan Data (Logika Bisnis): Sebelum ditampilkan, squadList diurutkan menggunakan logika pengurutan berdasarkan posisi (.sortBy):
Goalkeeper = 1 (Kuning)
Defender = 2 (Biru Muda)
Midfielder = 3 (Hijau Solid)
Forward/Attacker = 4 (Merah Muda/Salmon)
Penyajian di Layar:
squadList yang sudah terurut diserahkan ke PlayerAdapter.
Di dalam Adapter (onBindViewHolder), terjadi pewarnaan kondisional: Setiap pemain dicek posisinya (misal: "Centre-Back", "Attacking Midfield") dan kartu latar belakangnya diatur warnanya (misalnya #87CEEB untuk Defender).
Interaksi Klik: Saat kartu pemain diklik, Adapter memicu interface OnPlayerClickListener di SquadActivity, yang kemudian:
Mengubah warna kartu detail di bagian bawah sesuai posisi pemain yang diklik.
Mengisi TextView kartu detail tersebut dengan nama, tanggal lahir, dan posisi pemain.
Mengubah visibility kartu detail menjadi VISIBLE.

3. Alur Data Halaman Pelatih (Head Coach)
Pengiriman Data: MainActivity mengirimkan objek coachData melalui Intent.
Penanganan Data null: Karena API mengembalikan headCoach: null untuk ID 73, CoachActivity menerima data null.
Penyajian di Layar:
Aplikasi mengabaikan nilai null dari API.
Teks (nama, tanggal lahir, kebangsaan, detail kontrak) dan gambar diisi secara manual (hardcode) dengan data pelatih yang relevan (misalnya Ange Postecoglou) untuk memenuhi persyaratan tampilan tugas.

4. Alur Data Halaman Sejarah (Club History)
Teks Statis: HistoryActivity mengambil teks sejarah klub yang panjang (termasuk tag HTML untuk bold dan baris baru) dari string Kotlin.
Penyajian: Teks tersebut diolah menggunakan HtmlCompat.fromHtml() untuk menampilkan format yang benar, lalu disajikan di dalam ScrollView agar dapat digulir (scroll) oleh pengguna. Gambar header juga diisi secara statis dari R.drawable.
