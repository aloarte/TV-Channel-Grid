/*
 * Copyright 2018 Google LLC
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

package com.p4r4d0x.tvchannelgrid.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.p4r4d0x.tvchannelgrid.ChannelsDataWorker
import com.p4r4d0x.tvchannelgrid.utilities.DATABASE_NAME
import com.p4r4d0x.tvchannelgrid.utilities.DatabaseConverters


@Database(entities = [ChannelData::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class ChannelsDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    companion object {

        @Volatile
        private var instance: ChannelsDatabase? = null

        fun getInstance(context: Context): ChannelsDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        )
                            .also { instance = it }
                }
        }

        private fun buildDatabase(context: Context): ChannelsDatabase {
            return Room.databaseBuilder(context, ChannelsDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<ChannelsDataWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}
