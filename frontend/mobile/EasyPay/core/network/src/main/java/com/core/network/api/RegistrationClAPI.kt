package com.core.network.api

import com.core.network.NetworkConstants
import com.core.network.models.registration.RegistrationCLReqInfo
import com.core.network.models.registration.RegistrationCLResInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationClAPI {
    @POST(NetworkConstants.PspServer.REGISTRATION_URL)
    suspend fun register(@Body registerInfo: RegistrationCLReqInfo) : Response<RegistrationCLResInfo>
}