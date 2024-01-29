package com.billion_dollor_company.easypay.util

object Constants {

    const val TAG = "debugging_tag"

    object Keys {
        const val NPCI_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzlJDxZyYybLotiDimedygowDUGf7x6suqbpULbkGk/YtI+q+6yRWPhYnNeSiBrxgR7xev7Afz7a38FvZbXyUlriPqk0trzacWEyEKbJAogTtc/uKdD4tZ/8FI7qEVpP4SCUjbHxhigQzHR7rfYbzjMMRghk+CS4f0WdJ2LuHBxaIOTYFiaLu/JOKxRbFhStUwG6bbuaBzEdyPIWzt9DnotemcTVpmkgblF1W1dQ3uPf2yRCXQf5YEfvQUxYTQ36tKj9yclyLCgn/8o02SiyBRmRQ1mvbV1k4pLibZByfRWh6/xbPEGQFyqRpQT6FI28rWPh6K9Ehn7gXtjjsRgjcwIDAQAB"
    }

    object Server {
        object PspServer {
            const val BASE_URL = "http://192.168.1.3:16000/psp/"
            const val USER_INFO_URL = "userInfo"
            const val TRANSACTION_URL = "transaction"
        }
    }

    object Values{
        const val AMOUNT = "amount"
        const val ENCRYPTED_PASSWORD = "encryptedPassword"

        const val LOADING = "loading"
        const val SUCCESS = "success"
        const val FAILED = "failed"
    }


    object PayeeInfo {
        const val FULL_NAME = "fullName"
        const val PHONE_NO = "phoneNo"
        const val UPI_ID = "upiID"
        const val IMAGE_ID = "imageID"
        const val ACCOUNT_NO = "accountNo"
        const val BANK_NAME = "bankName"
    }

    object PayerInfo {
        const val FULL_NAME = "fullName"
        const val PHONE_NO = "phoneNo"
        const val UPI_ID = "upiID"
        const val IMAGE_ID = "imageID"
        const val ACCOUNT_NO = "accountNo"
        const val BANK_NAME = "bankName"
    }

    object Transaction{
        const val SUCCESS = "success"
    }

}