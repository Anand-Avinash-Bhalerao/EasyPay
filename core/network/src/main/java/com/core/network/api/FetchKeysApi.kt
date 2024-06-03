package com.core.network.api

import com.core.network.NetworkConstants
import com.core.network.models.fetchKeys.FetchKeysResInfo
import retrofit2.Response
import retrofit2.http.GET

interface FetchKeysApi {
    @GET(NetworkConstants.PspServer.FETCH_KEYS_URL)
    suspend fun getKeys(): Response<FetchKeysResInfo>
}