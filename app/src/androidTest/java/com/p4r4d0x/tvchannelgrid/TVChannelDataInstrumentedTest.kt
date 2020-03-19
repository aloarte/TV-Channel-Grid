package com.p4r4d0x.tvchannelgrid

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.p4r4d0x.tvchannelgrid.model.TVChannelDataResponse
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TVChannelDataInstrumentedTest {

    private lateinit var gson: Gson

    @Before
    fun setup() {
        gson = Gson()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val jsonChannel: String =
            appContext.assets.open(PLANT_DATA_FILENAME).bufferedReader().use { it.readText() }
        assertNotNull(jsonChannel)

        //Parse from json into a class
        val dtoChannelDataRead: TVChannelDataResponse =
            gson.fromJson(jsonChannel, TVChannelDataResponse::class.java)

        assertNotNull(dtoChannelDataRead)

        assertNotNull(dtoChannelDataRead.response)
        assertEquals(dtoChannelDataRead.response!!.size, 96)
        //Parse back from the class to the json value
        val jsonDtoChannelDataRead = gson.toJson(dtoChannelDataRead)
        assertNotNull(jsonDtoChannelDataRead)


    }
}
