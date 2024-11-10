/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * OfflineItemsRepository adalah kelas yang mengimplementasikan interface ItemsRepository
 * untuk berinteraksi dengan data secara offline, menggunakan akses ke ItemDao untuk melakukan
 * operasi penyimpanan dan pengambilan data dari database lokal Room.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /**
     * Mengambil data dari semua item yang ada dalam database secara real-time.
     * Fungsi ini akan mengakses getAllItems() dari ItemDao dengan return value Flow<List<Item>>
     * berisi seluruh item dalam database.
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /**
     * Mengambil data satu item berdasarkan parameter ID.
     * Mengakses getItem() dari ItemDao yang memiliki return value Flow<Item?>
     * jika data tidak ada maka return valuenya null
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /**
     * Memasukkan item baru ke dalam database secara asinkron menggunakan fungsi insert()
     * ItemDao.
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /**
     * Menghapus item dari database secara asinkron menggunakan delete()
     * dari ItemDao.
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /**
     * Memperbarui data item yang ada di database secara asinkron menggunakan update()
     * dari ItemDao.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}