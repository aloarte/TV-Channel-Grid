package com.p4r4d0x.tvchannelgrid.model

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.p4r4d0x.tvchannelgrid.utilities.CHANNEL_DATA_FILENAME
import kotlinx.coroutines.coroutineScope


class ChannelsDataWorker(private val context: Context, private val workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(CHANNEL_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->


                    val dtoChannelDataRead: TVChannelDataResponse = Gson().fromJson(
                        jsonReader,
                        TVChannelDataResponse::class.java
                    )

                    val channelsDatabase = ChannelsDatabase.getInstance(applicationContext)
                    channelsDatabase.channelDao().insertAll(dtoChannelDataRead.response!!)

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
