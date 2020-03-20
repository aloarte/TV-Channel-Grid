package com.p4r4d0x.tvchannelgrid.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels_table ORDER BY name")
    fun getChannelsData(): List<ChannelData>

    @Query("SELECT * FROM channels_table WHERE category IS :categoryFilter")
    fun getChannelsDataFiltered(categoryFilter: String): List<ChannelData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(channels: List<ChannelData>)
}
