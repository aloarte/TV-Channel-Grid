package com.p4r4d0x.tvchannelgrid.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.p4r4d0x.tvchannelgrid.model.ChannelListViewModelFactory
import com.p4r4d0x.tvchannelgrid.model.ChannelsDatabase
import com.p4r4d0x.tvchannelgrid.model.ChannelsRepository

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getChannelsRepository(context: Context): ChannelsRepository {
        return ChannelsRepository.getInstance(
            ChannelsDatabase.getInstance(context.applicationContext).channelDao()
        )
    }

    fun provideChannelListViewModelFactory(fragment: Fragment): ChannelListViewModelFactory {
        val repository = getChannelsRepository(fragment.requireContext())
        return ChannelListViewModelFactory(repository, fragment)
    }


}
