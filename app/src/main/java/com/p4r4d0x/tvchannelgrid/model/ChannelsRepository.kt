package com.p4r4d0x.tvchannelgrid.model

class ChannelsRepository constructor(private val channelDao: ChannelDao) {

    fun getChannelsData() = channelDao.getChannelsData()

    fun getChannelsDataFiltered(categoryFilter: String) =
        channelDao.getChannelsDataFiltered(categoryFilter)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: ChannelsRepository? = null

        fun getInstance(channelDao: ChannelDao) =

            instance ?: synchronized(this) {
                instance ?: ChannelsRepository(channelDao).also { instance = it }
            }
    }
}