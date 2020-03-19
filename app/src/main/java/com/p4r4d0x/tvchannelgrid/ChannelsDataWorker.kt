package com.p4r4d0x.tvchannelgrid

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.p4r4d0x.tvchannelgrid.model.ChannelsDatabase
import com.p4r4d0x.tvchannelgrid.model.TVChannelDataResponse
import com.p4r4d0x.tvchannelgrid.utilities.CHANNEL_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class ChannelsDataWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(CHANNEL_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->


                    val dtoChannelDataRead: TVChannelDataResponse = Gson().fromJson(
                        jsonReader,
                        TVChannelDataResponse::class.java
                    )

                    val database = ChannelsDatabase.getInstance(applicationContext)
                    database.channelDao().insertAll(dtoChannelDataRead.response!!)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error populating database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = ChannelsDataWorker::class.java.simpleName
    }
}