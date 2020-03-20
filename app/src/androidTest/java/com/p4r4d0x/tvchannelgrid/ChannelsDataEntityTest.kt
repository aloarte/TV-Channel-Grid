package com.p4r4d0x.tvchannelgrid

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.tvchannelgrid.model.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ChannelsDataEntityTest {
    private lateinit var channelDao: ChannelDao
    private lateinit var db: ChannelsDatabase
    lateinit var channelsDataList: ArrayList<ChannelData>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {

        channelsDataList = ArrayList()

        val attachments = ArrayList<Attachments>()
        attachments.add(Attachments("", "", "attachmentName", ""))
        val extraFields = ArrayList<ExtraFields>()
        extraFields.add(ExtraFields("", "FieldName", "FieldValue"))

        channelsDataList.add(
            ChannelData(
                0,
                attachments,
                "chanel1ID",
                0,
                "Description",
                "100fps",
                "URL",
                "",
                0,
                "",
                "",
                "",
                1,
                "",
                "channelSameIdentifier1",
                "IP",
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                "Channel1Name",
                extraFields,
                "Series",
                "ChannelTest1LongName"
            )
        )
        channelsDataList.add(
            ChannelData(
                0,
                attachments,
                "chanel2ID",
                0,
                "Description",
                "100fps",
                "URL",
                "",
                0,
                "",
                "",
                "",
                0,
                "",
                "channelSameIdentifier1",
                "IP",
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                "Channel2Name",
                extraFields,
                "Series",
                "ChannelTest2LongName"
            )
        )

        channelsDataList.add(
            ChannelData(
                0,
                attachments,
                "chanel3ID",
                0,
                "Description",
                "100fps",
                "URL",
                "",
                0,
                "",
                "",
                "",
                0,
                "",
                "channelIdentifier2",
                "IP",
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                "Channel3Name",
                extraFields,
                "Series",
                "ChannelTest3LongName"
            )
        )


        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ChannelsDatabase::class.java
        ).build()
        channelDao = db.channelDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun readInList() = runBlocking {

        channelDao.insertAll(channelsDataList)

        ViewMatchers.assertThat(
            getValue(channelDao.getChannelsData()).size,
            CoreMatchers.equalTo(2)
        )


    }
}
