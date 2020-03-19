package com.p4r4d0x.tvchannelgrid

import com.google.gson.Gson
import com.p4r4d0x.tvchannelgrid.TestingConstants.Companion.TEST_DTO_CHANNEL_DATA_JSON_EMPTY_RESPONSE
import com.p4r4d0x.tvchannelgrid.TestingConstants.Companion.TEST_DTO_CHANNEL_DATA_JSON_VALUE_EMPTY
import com.p4r4d0x.tvchannelgrid.TestingConstants.Companion.TEST_DTO_CHANNEL_DATA_JSON_VALUE_FEW_ELEMENTS
import com.p4r4d0x.tvchannelgrid.model.TVChannelDataResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class TVChannelDataUnitTest {


    private lateinit var gson: Gson

    @Before
    fun setup() {
        gson = Gson()
    }


    @Test
    fun jsonParse_noResponseElements() {
        //Parse from json into a class
        val dtoChannelDataRead: TVChannelDataResponse = gson.fromJson(
            TEST_DTO_CHANNEL_DATA_JSON_EMPTY_RESPONSE,
            TVChannelDataResponse::class.java
        )

        assertNotNull(dtoChannelDataRead)
        //Parse back from the class to the json value
        val jsonDtoChannelDataRead = gson.toJson(dtoChannelDataRead)
        assertEquals(TEST_DTO_CHANNEL_DATA_JSON_EMPTY_RESPONSE, jsonDtoChannelDataRead)
    }

    @Test
    fun jsonParse_fewElements() {
        //Parse from json into a class
        val dtoChannelDataRead: TVChannelDataResponse = gson.fromJson(
            TEST_DTO_CHANNEL_DATA_JSON_VALUE_FEW_ELEMENTS, TVChannelDataResponse::class.java
        )

        assertNotNull(dtoChannelDataRead)
        //Parse back from the class to the json value
        val jsonDtoChannelDataRead = gson.toJson(dtoChannelDataRead)
        assertEquals(TEST_DTO_CHANNEL_DATA_JSON_VALUE_FEW_ELEMENTS, jsonDtoChannelDataRead)
    }

    @Test
    fun jsonParse_empty() {
        //Parse from json into a class
        val dtoChannelDataRead: List<TVChannelDataResponse> =
            gson.fromJson(
                TEST_DTO_CHANNEL_DATA_JSON_VALUE_EMPTY,
                Array<TVChannelDataResponse>::class.java
            ).toList()
        assertNotNull(dtoChannelDataRead)
        //Parse back from the class to the json value
        val jsonDtoChannelDataRead = gson.toJson(dtoChannelDataRead)
        assertEquals(TEST_DTO_CHANNEL_DATA_JSON_VALUE_EMPTY, jsonDtoChannelDataRead)
    }
}
