package com.p4r4d0x.tvchannelgrid.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChannelsDataViewModel constructor(private val channelsRepository: ChannelsRepository) :
    ViewModel() {

    var allowedChannels: MutableLiveData<List<ChannelData>> = MutableLiveData()

    //Recover the list of ChannelData without filtering
    fun getChannelsData() = viewModelScope.launch(Dispatchers.IO) {
        channelsRepository.getChannelsData()
        val values = channelsRepository.getChannelsData()
        allowedChannels.postValue(values)
    }

    //Recover the list of ChannelData filtered by channelCategory param
    fun getChannelsDataFiltered(channelCategory: String) = viewModelScope.launch(Dispatchers.IO) {
        val values = channelsRepository.getChannelsDataFiltered(channelCategory)
        allowedChannels.postValue(values)
    }

}