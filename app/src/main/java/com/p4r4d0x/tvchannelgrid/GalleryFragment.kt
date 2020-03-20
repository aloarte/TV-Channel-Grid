package com.p4r4d0x.tvchannelgrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.p4r4d0x.tvchannelgrid.adapters.ChannelsAdapter
import com.p4r4d0x.tvchannelgrid.model.ChannelData
import com.p4r4d0x.tvchannelgrid.model.ChannelsDataViewModel
import com.p4r4d0x.tvchannelgrid.utilities.InjectorUtils
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private val channelsDataViewModel: ChannelsDataViewModel by viewModels {
        InjectorUtils.provideChannelListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
    }


    private fun subscribeUi() {
        channelsDataViewModel.allowedChannels.observe(viewLifecycleOwner) { result ->
            val adapter = context?.let {
                ChannelsAdapter(
                    it,
                    result as ArrayList<ChannelData>
                )
            }
            gv_tv_content.adapter = adapter
        }
    }
}
