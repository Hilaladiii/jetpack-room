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

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity data class represents a single row in the database.
 * Pada kode ini akan membuat entitas atau sebuah tabel items dengan beberapa field yaitu
 * 1. id dengan tipe data int dengan nilai awalya adalah 0 sebagai primary key ditandai dengan anotasi @PrimaryKey
 * dan akan bertambah setiap ada penambahan data autoGenerate(auto increment)
 * 2. name dengan tipe data string
 * 3. price dengan tipe data double
 * 4. quantity dengan tipe data int
 *
 * anotasi @Entity yang berfungsi sebagai penanda kalau kelas tersebut akan menjadi kelas entity database room
 * didalam anotasi @Entity terdapat paramteter tableName untuk memberikan nama dari tabelnya, jika tidak diisi
 * maka nama akan sesuai dengan nama kelasnya
 */
@Entity(tableName="items")
data class Item(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
