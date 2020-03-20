package com.p4r4d0x.tvchannelgrid.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.p4r4d0x.tvchannelgrid.R
import com.p4r4d0x.tvchannelgrid.model.ChannelData

class ChannelsAdapter(private val context: Context, var channelsData: ArrayList<ChannelData>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return channelsData.size
    }

    override fun getItem(position: Int): Any {
        return channelsData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //Inflate the element
        val channelItemView = inflater.inflate(R.layout.channel_grid_item, parent, false)
        val tvChannelName = channelItemView.findViewById(R.id.tv_channel_name) as TextView
        val ivChannelImg = channelItemView.findViewById(R.id.iv_channel_img) as ImageView


        //Get the ChannelData
        val channelData = this.channelsData[position]

//        Picasso.get().load(channelData.attachments!![0].value).placeholder(R.mipmap.ic_launcher)
//            .into(ivChannelImg)
        tvChannelName.text = channelData.name

        return channelItemView
    }
}