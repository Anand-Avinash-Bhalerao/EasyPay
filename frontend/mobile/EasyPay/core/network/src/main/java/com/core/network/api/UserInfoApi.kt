package com.core.network.api

import com.core.network.models.userInfo.UserResInfo
import com.core.network.models.userInfo.UserReqInfo
import com.core.network.NetworkConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInfoApi {
    @POST(NetworkConstants.PspServer.USER_INFO_URL)
    suspend fun getUserInfo(@Body upiID: UserReqInfo) : Response<UserResInfo>
}