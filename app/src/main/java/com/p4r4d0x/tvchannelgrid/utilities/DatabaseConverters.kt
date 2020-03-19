package com.p4r4d0x.tvchannelgrid.utilities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.p4r4d0x.tvchannelgrid.model.Attachments
import com.p4r4d0x.tvchannelgrid.model.ExtraFields
import java.util.*


class DatabaseConverters {

    @TypeConverter
    fun fromStringAttachments(value: String?): List<Attachments>? {
        return Gson().fromJson(value, object : TypeToken<ArrayList<Attachments?>?>() {}.type)
    }

    @TypeConverter
    fun fromAttachments(method: List<Attachments>?): String? {
        return Gson().toJson(method)
    }

    @TypeConverter
    fun fromStringExtraFields(value: String?): List<ExtraFields>? {
        return Gson().fromJson(value, object : TypeToken<ArrayList<ExtraFields?>?>() {}.type)
    }

    @TypeConverter
    fun fromExtraFields(method: List<ExtraFields>?): String? {
        return Gson().toJson(method)
    }
}