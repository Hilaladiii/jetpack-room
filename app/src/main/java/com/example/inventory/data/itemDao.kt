package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 Dao adalah singkatan dari Data Access Object yang berfungsi untuk mendefinisikan method yang akan digunakan dalam
 interaksi dengan tabel database.
 anotasi @Dao berfungsi untuk memberikan info bahwa ItemDao adalah Data Access Object yang merupakan interface untuk melakukan
 operasi CRUD (create, read, update, delete) pada database room. setiap interface yang menggunakan anotasi @Dao maka akan otomatis
 diimplementasikan oleh room saat aplikasi dikompilasi
 **/

@Dao
interface ItemDao{
    /**
     * fungsi insert untuk memasukkan data ke dalam data base
     * parameter onConflict.ignore berfungsi untuk mengabaikan atau menolak data baru jika terdapat
     * konflik contoh seperti ID duplikat dan tidak akan dimasukkan ke dalam database.
     * **/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    /**
     * insert(item: Item): fungsi ini adalah suspend function, sehingga dapat dipanggil di dalam coroutine untuk menjalankan operasi secara asinkron.
     * fungsi ini memasukkan satu Item ke dalam database.
     **/
    suspend fun insert(item : Item)

    /** Anotasi @Update digunakan untuk menandai fungsi update() sebagai fungsi untuk memperbarui data di dalam database. **/
    @Update
    /**
     * sama seperti insert fungsi update adalah suspense function sehingga dapat dijalakan secara asinkron dan mengupdate data pada room database
     **/
    suspend fun update(item : Item)

    /** Anotasi @Delete digunakan untuk menandai fungsi delete() sebagai fungsi untuk menghapus data di dalam database. **/
    @Delete
    suspend fun delete(item : Item)

    /**
     * anotasi @Query digunakan untuk menulis query SQL secara manual.
     * biasanya jika query kita kompleks maka disarankan menggunaakn @Query ini
     * **/
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id : Int) : Flow<Item>

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems() : Flow<List<Item>>


}