package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * InventoryDatabase adalah kelas abstrak yang mewakili database Room utama dalam aplikasi ini.
 * kelas memiliki fungsi penghubung aplikasi dengan database SQLite lokal yang dikelola Room.
 * @Database berfungsi memberi tanda InventoryDatabase adalah kelas basis data Room.
 * entities = [Item::class] adalah entitas/tabel yang ada dalam database, yaitu tabel Item.
 * version = 1 versi dari database.
 * exportSchema = false tidak melakukan ekspor skema database ke dalam file JSON.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    /**
     * Fungsi abstrak itemDao() akan memberikan akses ke DAO (Data Access Object) ItemDao.
     * Fungsi ini digunakan untuk memanggil semua method yang dibuat di dalam ItemDao
     * sehingga aplikasi dapat mengakses dan memanipulasi data Item di dalam database.
     */
    abstract fun itemDao(): ItemDao

    /**
     * Companion object berfungsi sebagai singleton untuk InventoryDatabase.
     * Singleton berarti bahwa hanya satu instance dari InventoryDatabase yang dibuat
     * sehingga efisien dalam penggunaan memori (menghindari memory leak) dan mempertahankan integritas data.
     */
    companion object {
        /**
         * Instance adalah variabel yang menyimpan instance dari InventoryDatabase.
         * @Volatile akan memberikan penanda variabel tersebut akan selalu up-to-date
         */
        @Volatile
        private var Instance: InventoryDatabase? = null

        /**
         * Fungsi getDatabase() digunakan untuk mengakses instance InventoryDatabase.
         * Jika Instance belum ada, fungsi ini akan membuat instance baru secara synchronized
         * untuk memastikan hanya satu instance yang dibuat
         */
        fun getDatabase(context: Context): InventoryDatabase {
            // Jika Instance null, buat instance baru menggunakan synchronized untuk memastikan
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    InventoryDatabase::class.java,
                    "item_database"
                )
                    .build() // membuat instance database Room
                    .also { Instance = it } // menyimpan instance ke dalam variabel Instance
            }
        }
    }
}
