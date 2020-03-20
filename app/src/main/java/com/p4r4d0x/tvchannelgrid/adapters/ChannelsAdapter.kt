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
import com.p4r4d0x.tvchannelgrid.utilities.BASE_IMG_URL
import com.p4r4d0x.tvchannelgrid.utilities.CHANNEL_LOGO_VALUE
import com.squareup.picasso.Picasso

class ChannelsAdapter(context: Context, private var channelsData: ArrayList<ChannelData>) :
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
        //Inflate the element and get the inner views
        val channelItemView = inflater.inflate(R.layout.channel_grid_item, parent, false)
        val tvChannelName = channelItemView.findViewById(R.id.tv_channel_name) as TextView
        val tvChannelType = channelItemView.findViewById(R.id.tv_channel_type) as TextView
        val ivChannelImg = channelItemView.findViewById(R.id.iv_channel_img) as ImageView

        //Get the ChannelData
        val channelData = this.channelsData[position]

        //Get the image path and set it
        val channelLogoPath: String? = getChannelLogo(channelData)
        if (channelLogoPath != null) {
            val imagePath = BASE_IMG_URL + channelLogoPath
            Picasso.get().load(imagePath).placeholder(R.mipmap.ic_launcher)
                .into(ivChannelImg)

        }

        tvChannelName.text = channelData.name
        tvChannelType.text = channelData.category

        return channelItemView
    }

    private fun getChannelLogo(channelData: ChannelData): String? {
        if (channelData.attachments != null) {
            for (attachment in channelData.attachments) {
                if (attachment.name == CHANNEL_LOGO_VALUE) {
                    return attachment.value
                }
            }
            return null
        } else return null
    }
}