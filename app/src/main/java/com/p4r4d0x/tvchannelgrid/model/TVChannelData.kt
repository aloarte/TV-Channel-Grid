package com.p4r4d0x.tvchannelgrid.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TVChannelDataResponse(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("response")
    val response: List<ChannelData>?
)

data class Metadata(
    @SerializedName("request")
    val request: String = "",
    @SerializedName("fullLength")
    val fullLength: Int = 0,
    @SerializedName("timestamp")
    val timestamp: Long = 0
)

@Entity(tableName = "channels_table")
data class ChannelData(
    @SerializedName("prLevel")
    val prLevel: Int = 0,
    @SerializedName("attachments")
    val attachments: List<Attachments>?,
    @SerializedName("externalChannelId")
    val externalChannelId: String = "",
    @SerializedName("flags")
    val flags: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("bitrate")
    val bitrate: String = "",
    @SerializedName("interactiveUrl")
    val interactiveUrl: String = "",
    @SerializedName("responseElementType")
    val responseElementType: String = "",
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("simultaneousViewsLimit")
    val simultaneousViewsLimit: String = "",
    @SerializedName("affiliation")
    val affiliation: String = "",
    @SerializedName("contentDefinition")
    val contentDefinition: String = "",
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("serviceId")
    val serviceId: String = "",
    @PrimaryKey
    @SerializedName("identifier")
    val identifier: String = "",
    @SerializedName("ip")
    val ip: String = "",
    @SerializedName("prName")
    val prName: String = "",
    @SerializedName("originalNetworkId")
    val originalNetworkId: String = "",
    @SerializedName("encoding")
    val encoding: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("transportStreamId")
    val transportStreamId: String = "",
    @SerializedName("port")
    val port: Int = 0,
    @SerializedName("sourceType")
    val sourceType: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("extrafields")
    val extraFields: List<ExtraFields>?,
    @SerializedName("category")
    val category: String = "",
    @SerializedName("longName")
    val longName: String = ""
)

data class Attachments(
    @SerializedName("responseElementType")
    val responseElementType: String = "",
    @SerializedName("assetId")
    val assetId: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("assetName")
    val assetName: String = "",
    @SerializedName("value")
    val value: String = ""
)

data class ExtraFields(
    @SerializedName("responseElementType")
    val responseElementType: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("value")
    val value: String = ""
)