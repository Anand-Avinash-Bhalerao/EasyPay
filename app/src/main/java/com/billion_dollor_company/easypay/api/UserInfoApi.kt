package com.billion_dollor_company.easypay.api

import com.billion_dollor_company.easypay.models.UserInfo
import com.billion_dollor_company.easypay.models.UserInfoReq
import com.billion_dollor_company.easypay.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInfoApi {
    @POST(Constants.Server.PspServer.USER_INFO_URL)
    suspend fun getUserInfo(@Body upiID: UserInfoReq) : Response<UserInfo>
}