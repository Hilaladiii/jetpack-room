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

import android.content.Context

/**
 * AppContainer adalah interface yang memiliki fungsi sebagai kontainer aplikasi untuk
 * Dependency Injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

/**
 * AppDataContainer adalah implementasi dari [AppContainer] yang berfungsi menyediakan instance
 * dari [OfflineItemsRepository]. Kelas ini digunakan untuk melakukan Dependency Injection pada
 * `itemsRepository`
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * val ini adalah implement dari [ItemsRepository] yang secara otomatis diinisialisasi menggunakan
     * properti lazy, instance dari OfflineItemsRepository hanya dibuat ketika pertama kali diakses.
     * Pada inisialisasi itemsRepository akan menggunakan InventoryDatabase untuk mendapatkan itemDao,
     * yang menghubungkan repository dengan database Room.
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
