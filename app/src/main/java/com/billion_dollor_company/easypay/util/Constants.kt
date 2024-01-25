package com.billion_dollor_company.easypay.util

object Constants {

    const val TAG = "debugging_tag"

    object Server {
        object PspServer {
            const val BASE_URL = "http://192.168.1.7:8010/psp/"
            const val USER_INFO_URL = "userInfo"
        }
    }


    object PayeeInfo {
        const val FULL_NAME = "fullName"
        const val PHONE_NO = "phoneNo"
        const val UPI_ID = "upiID"
        const val IMAGE_ID = "imageID"
        const val ACCOUNT_NO = "accountNo"
        const val BANK_NAME = "bankName"
        const val AMOUNT = "amount"
    }

}